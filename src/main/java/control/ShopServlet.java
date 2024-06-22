package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ContenenteBean;
import model.ContenenteCarrelloBean;
import model.ContenenteCombinedKey;
import model.ContenenteDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;
import model.UserDAO;

public class ShopServlet extends HttpServlet{
	private static final long serialVersionUID = -86976045570564505L;

	public ShopServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcherToShop = request.getRequestDispatcher("shop.jsp");
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ImmagineProdottoDAO ImmagineProdotti = new ImmagineProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		try {
			List<ProdottoBean> ProdottiList = (List<ProdottoBean>) Prodotti.doRetrieveAll("ID_prodotto");
			for(ProdottoBean Prodotto :ProdottiList) {
				int idProdotto = Prodotto.getIdProdotto();
				request.getSession().setAttribute("Prodotto"+Integer.toString(idProdotto), Prodotto);
				List<ImmagineProdottoBean> ImmagineProdottoBeanList = (List<ImmagineProdottoBean>) ImmagineProdotti.doRetrieveByProductKey(idProdotto);
				request.getSession().setAttribute("ImmagineProdotto"+Integer.toString(idProdotto), Prodotto);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		dispatcherToShop.forward(request, response);
	}
		 
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);	
	}
	
}
