package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

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





@WebServlet("/AggiungiImagine")
public class AggiungiImmagineServlet extends HttpServlet {
    private static final long serialVersionUID = 172482982748742874L;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcherToAdminPage = request.getRequestDispatcher("adminPage.jsp");

    	String directory = request.getContextPath()+"/images/prodotti";	
        // Check if the request actually contains a file upload
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Error: Form must has enctype=multipart/form-data.");
            return;
        }

        // Process the uploaded file
        Part filePart = request.getPart("immagine");
        String fileName = getFileName(filePart);
        if (filePart != null) {
            
            InputStream fileContent = filePart.getInputStream();

            // Verify if the file is an image
            if (isImage(fileContent)) {
                // Save the file
                File uploadsDir = new File(getServletContext().getRealPath("") + File.separator + directory);
                if (!uploadsDir.exists()) {
                    uploadsDir.mkdirs();
                }

                File file = new File(uploadsDir, fileName);
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

                
            } 
        }
        ImmagineProdottoDAO ImmagineProdotti = new ImmagineProdottoDAO((DataSource) this.getServletContext().getAttribute("DataSource"));
        
        ImmagineProdottoBean immagine = new ImmagineProdottoBean();
        immagine.setIdProdotto((int)request.getSession().getAttribute("idprodottoaggiunto"));
        immagine.setImmagine(fileName);
        try {
			ImmagineProdotti.doSave(immagine);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dispatcherToAdminPage.forward(request, response);
        
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private boolean isImage(InputStream fileContent) throws IOException {
        byte[] magicNumber = new byte[8];
        fileContent.read(magicNumber);
        fileContent.reset();

        return isJPEG(magicNumber) || isPNG(magicNumber) || isGIF(magicNumber);
    }

    private boolean isJPEG(byte[] magicNumber) {
        return magicNumber[0] == (byte) 0xFF && magicNumber[1] == (byte) 0xD8 && magicNumber[2] == (byte) 0xFF;
    }

    private boolean isPNG(byte[] magicNumber) {
        return magicNumber[0] == (byte) 0x89 && magicNumber[1] == (byte) 0x50 && magicNumber[2] == (byte) 0x4E &&
               magicNumber[3] == (byte) 0x47 && magicNumber[4] == (byte) 0x0D && magicNumber[5] == (byte) 0x0A &&
               magicNumber[6] == (byte) 0x1A && magicNumber[7] == (byte) 0x0A;
    }

    private boolean isGIF(byte[] magicNumber) {
        return magicNumber[0] == (byte) 0x47 && magicNumber[1] == (byte) 0x49 && magicNumber[2] == (byte) 0x46;
    }
}
