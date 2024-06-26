package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;

import model.CarrelloBean;
import model.CarrelloDAO;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloCombinedKey;
import model.ContenenteCarrelloDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;



@WebServlet("/UpdateQuantity")
public class UpdateQuantityServlet extends HttpServlet {

    private static final long serialVersionUID = 6672800554224385658L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean utente = (UserBean) request.getSession().getAttribute("utente");
        String email = utente != null ? utente.getEmail() : null;
        List<String> errors = new ArrayList<>();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        if (email == null || email.trim().isEmpty()) {
            errors.add("Il campo username non può essere vuoto!");
            jsonResponse.put("success", false);
            jsonResponse.put("errors", errors);
            out.print(jsonResponse.toString());
            return;
        }

        email = email.trim();

        try {
            CarrelloDAO carrelli = new CarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));
            CarrelloBean carrello = carrelli.doRetrieveByUserKey(email);
            int idCarrello = carrello.getIdCarrello();

            ContenenteCarrelloDAO contenenteCarrelli = new ContenenteCarrelloDAO((DataSource) getServletContext().getAttribute("DataSource"));
            String idProdotto = request.getParameter("updateProductId");
            ContenenteCarrelloCombinedKey key = new ContenenteCarrelloCombinedKey(Integer.parseInt(idProdotto), idCarrello);
            ContenenteCarrelloBean contenenteCarrello = contenenteCarrelli.doRetrieveByKey(key);
            int quantita = Integer.parseInt(request.getParameter("quantity"));

            if (quantita >= 0) {
                if (quantita != 0) {
                    contenenteCarrello.setQuantita(quantita);
                    contenenteCarrelli.doSave(contenenteCarrello);
                } else {
                    contenenteCarrelli.doDelete(key);
                }
            }

            // Calculate the new total cost
            List<ContenenteCarrelloBean> cartItems = (List<ContenenteCarrelloBean>) contenenteCarrelli.doRetrieveByCarrelloKey(idCarrello);
            float totalCost = 0;
            ProdottoDAO prodottoDAO = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
            for (ContenenteCarrelloBean item : cartItems) {
                ProdottoBean product = prodottoDAO.doRetrieveByKey(item.getIdProdotto());
                totalCost += item.getQuantita() * product.getPrezzo();
            }

            jsonResponse.put("success", true);

        } catch (SQLException e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "An error occurred while updating the quantity.");
        }

        out.print(jsonResponse.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
