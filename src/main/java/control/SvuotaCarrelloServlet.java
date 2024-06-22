package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.CarrelloBean;
import model.CarrelloDAO;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;

public class SvuotaCarrelloServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6672800554224385658L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		
		
		List<String> errors = new ArrayList<>();
		
		RequestDispatcher dispatcherToCart = request.getRequestDispatcher("cart.jsp");

		
		
		
		
		dispatcherToCart.forward(request, response);
		
		
	}
		
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
	
}
