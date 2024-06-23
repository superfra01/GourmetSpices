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


@WebServlet("/ProdottoSpecificato")
public class ProdottoSpecificoServlet extends HttpServlet{
	private static final long serialVersionUID = -8697651045570564505L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		List<String> errors = new ArrayList<>();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("specificaProdotto.jsp");
		
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ImmagineProdottoDAO Immagini = new ImmagineProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		
		try {
		int idProdotto = Integer.parseInt(request.getParameter("id"));
		ProdottoBean prodotto;
		
			prodotto = Prodotti.doRetrieveByKey(idProdotto);
		
		request.getSession().setAttribute("ProdottoRichiesto", prodotto);
		List<ImmagineProdottoBean> immagini =(List<ImmagineProdottoBean>) Immagini.doRetrieveByProductKey(idProdotto);
		request.getSession().setAttribute("ProdottoRichiestoImmagnini", immagini);
		}catch (SQLException e) {
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

