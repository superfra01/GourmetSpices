package control;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		password = hashPassword(password);
		
		
		
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
