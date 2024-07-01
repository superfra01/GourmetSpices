package control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Arrays;

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

    private static final byte[] JPEG_MAGIC_NUMBER = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};
    private static final byte[] PNG_MAGIC_NUMBER = {(byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47, (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A};
    private static final byte[] GIF_MAGIC_NUMBER = {(byte) 0x47, (byte) 0x49, (byte) 0x46};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero dell'utente dalla sessione
        UserBean user = (UserBean) request.getSession().getAttribute("utente");

        // Verifica se l'utente � un amministratore
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
            try (BufferedInputStream fileContent = new BufferedInputStream(filePart.getInputStream())) {
                // Verify the magic number of the file
                if (!isValidImage(fileContent)) {
                    response.getWriter().println("Error: Uploaded file is not a valid image.");
                    return;
                }

                // Reset the input stream after reading the magic number
                fileContent.reset();

                // Percorso per la cartella temporanea (usata dal server durante l'esecuzione)
                String tempPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "prodotti";

                
                // Assicurati che entrambe la cartella esista
                new File(tempPath).mkdirs();
               

                File tempFile = new File(tempPath, fileName);
               

                // Save the file in the temporary and project folders
                Files.copy(fileContent, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                

                ImmagineProdottoDAO immagineProdotti = new ImmagineProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));

                ImmagineProdottoBean immagine = new ImmagineProdottoBean();
                immagine.setIdProdotto((int) request.getSession().getAttribute("idprodottoaggiunto"));
                immagine.setImmagine(fileName);

                try {
                    immagineProdotti.doSave(immagine);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
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

    private boolean isValidImage(InputStream input) throws IOException {
        byte[] header = new byte[8];
        input.mark(header.length);
        int bytesRead = input.read(header);
        input.reset();

        if (bytesRead < JPEG_MAGIC_NUMBER.length) {
            return false;
        }

        if (Arrays.equals(Arrays.copyOf(header, JPEG_MAGIC_NUMBER.length), JPEG_MAGIC_NUMBER) ||
            Arrays.equals(Arrays.copyOf(header, PNG_MAGIC_NUMBER.length), PNG_MAGIC_NUMBER) ||
            Arrays.equals(Arrays.copyOf(header, GIF_MAGIC_NUMBER.length), GIF_MAGIC_NUMBER)) {
            return true;
        }

        return false;
    }
}