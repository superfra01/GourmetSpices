package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean; 

@WebServlet("/inserisciElemento")
@MultipartConfig(maxFileSize = 16177215)
public class CreaProdottoServlet extends HttpServlet{

    private static final long serialVersionUID = -4173801915656104944L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Recupero dell'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("utente");
        
        // Verifica se l'utente è un amministratore
        if (user == null || !user.getTipoUtente().equals("ADMIN")) {
            response.getWriter().println("Access Denied: You do not have permission to perform this action.");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiImagine");
        
        ProdottoDAO Prodotti = new ProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
        try {
            ProdottoBean prodotto = new ProdottoBean();
            prodotto.setNome(request.getParameter("nomeprodotto"));
            prodotto.setDescrizione(request.getParameter("descrizione"));
            float prezzo = Float.parseFloat(request.getParameter("prezzo"));
            prodotto.setPrezzo(prezzo);

            int idProdotto = Prodotti.nextId();
            Prodotti.doSave(prodotto);
            request.getSession().setAttribute("idprodottoaggiunto", idProdotto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
}