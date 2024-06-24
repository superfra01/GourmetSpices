package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import model.ContenenteBean;
import model.ContenenteCarrelloBean;
import model.ContenenteCarrelloCombinedKey;
import model.ContenenteCarrelloDAO;
import model.ContenenteDAO;
import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.MetodoDiPagamentoBean;
import model.MetodoDiPagamentoDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.UserBean;

@WebServlet("/adminOrders")
public class OrdiniServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137981767037505776L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcherToOrdini = request.getRequestDispatcher("ordiniAdmin.jsp");
		UserBean user = (UserBean) request.getSession().getAttribute("utente");
		if(user!=null && user.getTipoUtente().equals("ADMIN")) {
			OrdineDAO ordini = new OrdineDAO((DataSource)getServletContext().getAttribute("DataSource"));
			ProdottoDAO prodotti = new ProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
			ImmagineProdottoDAO immagini = new ImmagineProdottoDAO((DataSource)getServletContext().getAttribute("DataSource"));
			MetodoDiPagamentoDAO MetodiDiPagamento = new MetodoDiPagamentoDAO((DataSource)getServletContext().getAttribute("DataSource"));
			ContenenteDAO Contenenti = new ContenenteDAO((DataSource)getServletContext().getAttribute("DataSource"));
			try {
				List<OrdineBean> OrdiniList = (List<OrdineBean>) ordini.doRetrieveAll("data");
				request.getSession().setAttribute("adminOrdini", OrdiniList);
				for(OrdineBean Ordine : OrdiniList) {
					int idOrdine = Ordine.getIdOrdine();
					String NCarta = Ordine.getNCarta();
					MetodoDiPagamentoBean MetodoDiPagamento = MetodiDiPagamento.doRetrieveByKey(NCarta);
					request.getSession().setAttribute("adminMetodoDiPagamento"+idOrdine, MetodiDiPagamento);
					List<ContenenteBean> ContenenteList = (List<ContenenteBean>) Contenenti.doRetrieveByOrderKey(idOrdine);
					if(ContenenteList==null)
						System.out.println("diomerda");
					request.getSession().setAttribute("adminContenenteList"+idOrdine, ContenenteList);
					for(ContenenteBean Contenente: ContenenteList) {
						int idProdotto = Contenente.getIdProdotto();
						ProdottoBean Prodotto = prodotti.doRetrieveByKey(idProdotto);
						
						request.getSession().setAttribute("adminProdotto"+idProdotto, Prodotto);
						
						List<ImmagineProdottoBean> ImmagineProdottoList = (List<ImmagineProdottoBean>) immagini.doRetrieveByProductKey(idProdotto);
						request.getSession().setAttribute("adminProdottoImmagini"+idProdotto, ImmagineProdottoList);
						
						
					}
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
        		
			dispatcherToOrdini.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
