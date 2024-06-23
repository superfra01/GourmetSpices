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

import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.ProdottoBean;
import model.ProdottoDAO;

@WebServlet("/ModificaProdotto")
public class ModificaProdottoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7631554330668275487L;




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("specificaProdotto.jsp");
		
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ImmagineProdottoDAO Immagini = new ImmagineProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		
		try {
		int idProdotto = Integer.parseInt(request.getParameter("id_modifica"));
		int valido = Integer.parseInt(request.getParameter("valido"));
		int prezzo = Integer.parseInt(request.getParameter("prezzo_modifica"));
		String Desc = request.getParameter("descrizione_modifica");
		String Nome = request.getParameter("nome_modifica");
		ProdottoBean prodotto;
		prodotto = Prodotti.doRetrieveByKey(idProdotto);
		prodotto.setPrezzo(prezzo);
		prodotto.setNome(Nome);
		prodotto.setValidoProdotto(valido);
		prodotto.setDescrizione(Desc);
		Prodotti.doSave(prodotto);
		request.getSession().setAttribute("ProdottoRichiesto", prodotto);
		List<ImmagineProdottoBean> ImmaginiProdoto = (List<ImmagineProdottoBean>) Immagini.doRetrieveByProductKey(idProdotto);
		request.getSession().setAttribute("ProdottoRichiestoImmagini", ImmaginiProdoto);
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
