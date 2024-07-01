package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import model.ImmagineProdottoBean;
import model.ImmagineProdottoDAO;
import model.UserBean; 

@WebServlet("/AggiungiImagine")
@MultipartConfig(maxFileSize = 16177215)
public class AggiungiImmagineServlet extends HttpServlet {
    private static final long serialVersionUID = 172482982748742874L;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dell'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("utente");
        
        // Verifica se l'utente è un amministratore
        if (user == null || !user.getTipoUtente().equals("ADMIN")) {
            response.getWriter().println("Access Denied: You do not have permission to perform this action.");
            return;
        }

        RequestDispatcher dispatcherToAdminPage = request.getRequestDispatcher("adminPage.jsp");

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Error: Form must have enctype=multipart/form-data.");
            return;
        }

        Part filePart = request.getPart("immagine");
        String fileName = getFileName(filePart);
        
        if (filePart != null && fileName != null && !fileName.isEmpty()) {
            // Percorso per la cartella temporanea (usata dal server durante l'esecuzione)
            String tempPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "prodotti";
            
            // Percorso per la cartella effettiva del progetto
            String projectPath = System.getProperty("user.dir") + File.separator + "WebContent" + File.separator + "images" + File.separator + "prodotti";
            
            // Assicurati che entrambe le cartelle esistano
            new File(tempPath).mkdirs();
            new File(projectPath).mkdirs();

            File tempFile = new File(tempPath, fileName);
            File projectFile = new File(projectPath, fileName);
            
            try (InputStream fileContent = filePart.getInputStream()) {
                // Salva nella cartella temporanea
                Files.copy(fileContent, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                // Salva nella cartella del progetto
                Files.copy(Paths.get(tempFile.getPath()), projectFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            ImmagineProdottoDAO ImmagineProdotti = new ImmagineProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
            
            ImmagineProdottoBean immagine = new ImmagineProdottoBean();
            immagine.setIdProdotto((int)request.getSession().getAttribute("idprodottoaggiunto"));
            immagine.setImmagine(fileName);
            
            try {
                ImmagineProdotti.doSave(immagine);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        } else {
            response.getWriter().println("Error: No file uploaded or invalid file name.");
            return;
        }

        dispatcherToAdminPage.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);    
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}