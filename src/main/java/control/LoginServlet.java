package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ContenenteBean;
import model.ContenenteCarrelloDAO;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = -8697651045570564505L;

	public LoginServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		List<String> errors = new ArrayList<>();
		
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");

		
		if(email == null || email.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!");
		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
        
        email = email.trim();
        password = hashPassword(password);
       
		
        UserDAO account = new UserDAO((DataSource) getServletContext().getAttribute("DataSource"));
        UserBean user = null;
		try {
			user = account.doRetrieveByKey(email);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		OrdineDAO Ordini = new OrdineDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ContenenteDAO Contenenti = new ContenenteDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ProdottoDAO Prodotti = new ProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		ImmagineProdottoDAO Immagini = new ImmagineProdottoDAO((DataSource) getServletContext().getAttribute("DataSource"));
		if(password.equals(user.getPassword()) && user.getEmail()!=""){
        	request.getSession().setAttribute("utente", user);
        	List<OrdineBean> OrdiniList;
			try {
				
				OrdiniList = (List<OrdineBean>) Ordini.doRetrieveByUserKey(email);
				request.getSession().setAttribute("ordini", OrdiniList);
				for(OrdineBean Ordine: OrdiniList) {
					int idOrdine = Ordine.getIdOrdine();
					
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
        	
			response.sendRedirect("user.jsp");
		}else{
			errors.add("Username o password errata!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
		
	}
		
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	// Metodo per creare l'hash della password con il salt
	public static String hashPassword(String password) {
    	final byte[] salt = "salatino".getBytes();
    	
    	
        try {
            // Creazione dell'istanza del MessageDigest con SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // Aggiunge il salt
            md.update(salt);
            
            // Hash della password
            byte[] hashedPassword = md.digest(password.getBytes());
            
            // Combina il salt e l'hash della password
            byte[] hashWithSalt = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, hashWithSalt, 0, salt.length);
            System.arraycopy(hashedPassword, 0, hashWithSalt, salt.length, hashedPassword.length);
            
            // Converte il risultato in una stringa Base64
            return Base64.getEncoder().encodeToString(hashWithSalt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore durante la creazione dell'hash: " + e.getMessage());
        }
    }
}
