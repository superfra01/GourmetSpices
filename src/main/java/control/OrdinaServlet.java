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

@WebServlet("/order")
public class OrdinaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137981767037505776L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcherToOrdine = request.getRequestDispatcher("/carrello");
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
		ContenenteDAO contenenti = new ContenenteDAO((DataSource)getServletContext().getAttribute("DataSource"));
		MetodoDiPagamentoDAO MetodiDiPagamento = new MetodoDiPagamentoDAO((DataSource)getServletContext().getAttribute("DataSource"));
		
		
		try {
			MetodoDiPagamentoBean MetodoDiPagamento = new MetodoDiPagamentoBean();
			MetodoDiPagamento.setCVV(request.getParameter("cvv"));
			MetodoDiPagamento.setEmail(email);
			MetodoDiPagamento.setNCarta(request.getParameter("cardNumber"));
			LocalDate localDate = LocalDate.parse(request.getParameter("expiryDate"));
			MetodoDiPagamento.setData(Date.valueOf(localDate));
			CarrelloBean carrello = carrelli.doRetrieveByUserKey(email);
			MetodiDiPagamento.doSave(MetodoDiPagamento);
			int idCarrello = carrello.getIdCarrello();
			ContenenteCarrelloCombinedKey key = new ContenenteCarrelloCombinedKey();
			key.setIdCarrello(idCarrello);
			List<ContenenteCarrelloBean> ContenenteCarrelloBeanList = (List<ContenenteCarrelloBean>) ContenenteCarrelli.doRetrieveByCarrelloKey(idCarrello);
			OrdineBean ordine = new OrdineBean();
			ordine.setData(Date.valueOf(LocalDate.now()));
			ordine.setEmail(email);
			ordine.setIndirizzo(request.getParameter("indirizzoSpedizione"));
			ordine.setnCartaIban(request.getParameter("cardNumber"));
			
			int idOrdine = ordini.nextId();
			ordini.doSave(ordine);
			
			ordine.setIdOrdine(idOrdine);
			float spesa=0;
			for(ContenenteCarrelloBean ContenenteCarrello : ContenenteCarrelloBeanList) {
				
				int idProdotto = ContenenteCarrello.getIdProdotto();
				ProdottoBean prodotto = prodotti.doRetrieveByKey(idProdotto);
				spesa += prodotto.getPrezzo()*ContenenteCarrello.getQuantita();
				ContenenteBean Contenente = new ContenenteBean(idProdotto, idOrdine, prodotto.getPrezzo(), ContenenteCarrello.getQuantita());
				contenenti.doSave(Contenente);
				ContenenteCarrelloCombinedKey key1 = new ContenenteCarrelloCombinedKey(idProdotto, idCarrello);
				ContenenteCarrelli.doDelete(key1);
			}
			
			ordine.setSpesa(spesa);
			ordini.doSave(ordine);
			
			

			OrdineDAO Ordini = new OrdineDAO((DataSource) getServletContext().getAttribute("DataSource"));
			ContenenteDAO Contenenti = new ContenenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
			ProdottoDAO Prodotti = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
			ImmagineProdottoDAO Immagini = new ImmagineProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
			List<OrdineBean> OrdiniList = (List<OrdineBean>) request.getSession().getAttribute("ordini");
			if(OrdiniList==null)
				OrdiniList = new ArrayList<OrdineBean>();
			OrdiniList.add(ordine);
			
			request.getSession().setAttribute("ordini", OrdiniList);
			
			for(OrdineBean Ordine: OrdiniList) {
				idOrdine = Ordine.getIdOrdine();
				
				List<ContenenteBean> ContenenteBeanList = (List<ContenenteBean>) Contenenti.doRetrieveByOrderKey(idOrdine);
				request.getSession().setAttribute("contenente"+Integer.toString(idOrdine), ContenenteBeanList);
				for(ContenenteBean Contenente: ContenenteBeanList) {
					int idProdotto = Contenente.getIdProdotto();
					ProdottoBean Prodotto = Prodotti.doRetrieveByKey(idProdotto);
					request.getSession().setAttribute("prodotto"+Integer.toString(idProdotto), Prodotto);
					List<ImmagineProdottoBean> ImmaginiProdotto = (List<ImmagineProdottoBean>) Immagini.doRetrieveByProductKey(idProdotto);
					request.getSession().setAttribute("immaginiprodotto"+Integer.toString(idProdotto), ImmaginiProdotto);
				}
				
			}
			
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
