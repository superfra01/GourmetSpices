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
import model.ProdottoDAO;
import model.UserBean;
import model.UserDAO;

public class ProdottiServlet extends HttpServlet{
	private static final long serialVersionUID = -86976045570564505L;

	public ProdottiServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		
		OrdineDAO Ordini = new OrdineDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ContenenteDAO Contenenti = new ContenenteDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		
		ImmagineProdottoDAO ImmaginiProdotti = new ImmagineProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			List<OrdineBean> list=((List<OrdineBean>) Ordini.doRetrieveByUserKey(email));
			request.getSession().setAttribute("ordini", list);
			for(OrdineBean ordine: list) {
				List<ContenenteBean> ContenenteList = (List<ContenenteBean>) Contenenti.doRetrieveByOrderKey(ordine.getIdOrdine());
				request.getSession().setAttribute(Integer.toString(ordine.getIdOrdine()), ContenenteList);
				for(ContenenteBean Contenente: ContenenteList) {
					request.getSession().setAttribute(Integer.toString(Contenente.getIdProdotto()), Prodotti.doRetrieveByKey(Contenente.getIdProdotto()));
					List<ImmagineProdottoBean> Immagini = (List<ImmagineProdottoBean>)ImmaginiProdotti.doRetrieveByProductKey(Contenente.getIdProdotto());
					request.getSession().setAttribute("images"+Integer.toString(Contenente.getIdProdotto()), Immagini);//
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcherToUser = request.getRequestDispatcher("user.jsp");
		
		
		dispatcherToUser.forward(request, response);
	}
		 
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		String email = utente.getEmail();
		
		OrdineDAO Ordini = new OrdineDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ContenenteDAO Contenenti = new ContenenteDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
		
		ImmagineProdottoDAO ImmaginiProdotti = new ImmagineProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			List<OrdineBean> list=((List<OrdineBean>) Ordini.doRetrieveByUserKey(email));
			request.getSession().setAttribute("ordini", list);
			for(OrdineBean ordine: list) {
				List<ContenenteBean> ContenenteList = (List<ContenenteBean>) Contenenti.doRetrieveByOrderKey(ordine.getIdOrdine());
				request.getSession().setAttribute(Integer.toString(ordine.getIdOrdine()), ContenenteList);
				for(ContenenteBean Contenente: ContenenteList) {
					request.getSession().setAttribute(Integer.toString(Contenente.getIdProdotto()), Prodotti.doRetrieveByKey(Contenente.getIdProdotto()));
					List<ImmagineProdottoBean> Immagini = (List<ImmagineProdottoBean>)ImmaginiProdotti.doRetrieveByProductKey(Contenente.getIdProdotto());
					request.getSession().setAttribute("images"+Integer.toString(Contenente.getIdProdotto()), Immagini);//
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcherToUser = request.getRequestDispatcher("user.jsp");
		
		
		dispatcherToUser.forward(request, response);
		
	}
	
}
