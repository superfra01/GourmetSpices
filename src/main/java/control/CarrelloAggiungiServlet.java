package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.CarrelloDAO;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloCombinedKey;
import model.ContenenteCarrelloDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;


@WebServlet("CarrelloAggiungi")
public class CarrelloAggiungiServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7112366996429439147L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idProdotto = request.getParameter("AddProdottoId");
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		CarrelloDAO carrelli = new CarrelloDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ContenenteCarrelloDAO ContenenteCarrelli = new ContenenteCarrelloDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		
		try {
			int idCarrello = carrelli.doRetrieveByUserKey(email).getIdCarrello();
			ContenenteCarrelloCombinedKey key = new ContenenteCarrelloCombinedKey(Integer.parseInt(idProdotto),idCarrello);
			ContenenteCarrelloBean ContenenteCarrello = ContenenteCarrelli.doRetrieveByKey(key);
			if(ContenenteCarrello==null) {
				ContenenteCarrelloBean NewContenenteCarrello = new ContenenteCarrelloBean(idCarrello, Integer.parseInt(idProdotto), 1);
				ContenenteCarrelli.doSave(NewContenenteCarrello);
			}else {
				ContenenteCarrello.setQuantita(ContenenteCarrello.getQuantita()+1);
				ContenenteCarrelli.doSave(ContenenteCarrello);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
			
		
		
		
	}
		 
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);	
	}
	
}
