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

import model.ContenenteBean;
import model.ContenenteDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;
import model.UserDAO;

@WebServlet("/inserisciElemento")
public class CreaProdottoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4173801915656104944L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/AggiungiImagine");
		
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
		try {
			
			ProdottoBean prodotto = new ProdottoBean();
			prodotto.setNome(request.getParameter("nomeprodotto"));
			prodotto.setDescrizione(request.getParameter("descrizione"));
			float prezzo = Float.parseFloat(request.getParameter("prezzo"));
			prodotto.setPrezzo(prezzo);

			//prodotto.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
			int idProdotto = Prodotti.nextId();
			Prodotti.doSave(prodotto);
			request.getSession().setAttribute("idprodottoaggiunto", idProdotto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}
		
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

}
