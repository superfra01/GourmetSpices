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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.UserBean;
import model.UserDAO;

public class SingInServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8237023202749419060L;
	
	public SingInServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username = request.getParameter("username");
		
		
		
		
		List<String> errors = new ArrayList<>();
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("signIn.jsp");
		
    	
    	
		if(email == null || email.trim().isEmpty()) {
			errors.add("Il campo email non può essere vuoto!");
		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if(nome == null || nome.trim().isEmpty()) {
        	errors.add("Il campo nome non può essere vuoto!");
		}
        if(cognome == null || cognome.trim().isEmpty()) {
        	errors.add("Il campo cognome non può essere vuoto!");
		}
        if(username == null || username.trim().isEmpty()) {
        	errors.add("Il campo username non può essere vuoto!");
        }
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
        
        email = email.trim();
        password = hashPassword(password.trim());
        nome = nome.trim();
        cognome = cognome.trim();
        username = username.trim();
        
        UserDAO account = new UserDAO((DataSource) getServletContext().getAttribute("DataSource"));
        UserBean user = new UserBean();
        
		try {
			user.setNome(nome);
			user.setCognome(cognome);
			user.setEmail(email);
			user.setPassword(hashPassword(password));
			user.setUsername(username);
			user.setTipoUtente("user");
			
			account.doSave(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		response.sendRedirect("homepage.jsp");
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
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
