package control;

import java.io.IOException;
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

import model.CarrelloBean;
import model.CarrelloDAO;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloCombinedKey;
import model.ContenenteCarrelloDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;


@WebServlet("/UpdateQuantity")
public class UpdateQuantityServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6672800554224385658L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		
		
		List<String> errors = new ArrayList<>();
		
		RequestDispatcher dispatcherToCart = request.getRequestDispatcher("carrello");

		
		if(email == null || email.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!");
		}
		
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToCart.forward(request, response);
        	return;
        }
        
        email = email.trim();
        
        try{
        	CarrelloDAO carrelli = new CarrelloDAO((DataSource)getServletContext().getAttribute("DataSource"));
        	
        	CarrelloBean carrello = carrelli.doRetrieveByUserKey(email);
        	
        	int idCarrello = carrello.getIdCarrello();
        	
        	ContenenteCarrelloDAO ContenenteCarrelli = new ContenenteCarrelloDAO((DataSource)getServletContext().getAttribute("DataSource"));
        	
        	String idProdotto = request.getParameter("updateProductId");
        	ContenenteCarrelloCombinedKey key = new ContenenteCarrelloCombinedKey(Integer.parseInt(idProdotto), idCarrello);
        	ContenenteCarrelloBean ContenenteCarrello = ContenenteCarrelli.doRetrieveByKey(key);
        	int quantita = Integer.parseInt(request.getParameter("quantity"));
        	ContenenteCarrello.setQuantita(quantita);
        	ContenenteCarrelli.doSave(ContenenteCarrello);
        	
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		dispatcherToCart.forward(request, response);
		
		
	}
		
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
	
}
