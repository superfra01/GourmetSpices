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
        
       
		
		if(password.equals(user.getPassword()) && user.getEmail()!=""){
        	request.getSession().setAttribute("utente", user);
			response.sendRedirect("HomePage.jsp");
		}else{
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
	}
		
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
        
       
		
		if(password.equals(user.getPassword()) && user.getEmail()!=""){
        	request.getSession().setAttribute("utente", user);
			response.sendRedirect("HomePage.jsp");
		}else{
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
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
