package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.CarrelloBean;
import model.CarrelloDAO;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloCombinedKey;
import model.ContenenteCarrelloDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;

@WebServlet("/order")
public class OrdinaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137981767037505776L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcherToOrdine = request.getRequestDispatcher("ordine.jsp");
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		List<String> errors = new ArrayList<>();
		
		if(email == null || email.trim().isEmpty()) {
			errors.add("richiesta invalida");
		}
		
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	RequestDispatcher dispatcherToHome = request.getRequestDispatcher("HomePage.jsp");
        	dispatcherToHome.forward(request, response);
        	return;
        }
		CarrelloDAO carrelli = new CarrelloDAO((DataSource)getServletContext().getAttribute("DataSource"));
		ContenenteCarrelloDAO ContenenteCarrelli = new ContenenteCarrelloDAO((DataSource)getServletContext().getAttribute("DataSource"));
		OrdineDAO ordini = new OrdineDAO((DataSource)getServletContext().getAttribute("DataSource"));
		ProdottoDAO prodotti = new ProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			CarrelloBean carrello = carrelli.doRetrieveByUserKey(email);
			int idCarrello = carrello.getIdCarrello();
			ContenenteCarrelloCombinedKey key = new ContenenteCarrelloCombinedKey();
			key.setIdCarrello(idCarrello);
			List<ContenenteCarrelloBean> ContenenteCarrelloBeanList = (List<ContenenteCarrelloBean>) ContenenteCarrelli.doRetrieveByCarrelloKey(idCarrello);
			OrdineBean ordine = new OrdineBean();
			ordine.setData(Date.valueOf(LocalDate.now()));
			ordine.setEmail(email);
			ordine.setIndirizzo(request.getParameter("indirizzoSpedizione"));
			ordine.setnCartaIban(request.getParameter("cardNumber"));
			
			
			
			float spesa=0;
			for(ContenenteCarrelloBean ContenenteCarrello : ContenenteCarrelloBeanList) {
				int idProdotto = ContenenteCarrello.getIdProdotto();
				ProdottoBean prodotto = prodotti.doRetrieveByKey(idProdotto);
				spesa += prodotto.getPrezzo()*ContenenteCarrello.getQuantita();
				
			}
			
			ordine.setSpesa(spesa);
			System.out.println(ordini.nextId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        		
        		
        		
        		
        		
        dispatcherToOrdine.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
