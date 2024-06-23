package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;



@WebServlet("/uploadImage")
@MultipartConfig
public class AggiungiImmagineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the request actually contains a file upload
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Error: Form must has enctype=multipart/form-data.");
            return;
        }

        // Process the uploaded file
        Part filePart = request.getPart("file");
        if (filePart != null) {
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();

            // Verify if the file is an image
            if (isImage(fileContent)) {
                // Save the file
                File uploadsDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
                if (!uploadsDir.exists()) {
                    uploadsDir.mkdirs();
                }

                File file = new File(uploadsDir, fileName);
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

                response.getWriter().println("File uploaded successfully.");
            } else {
                response.getWriter().println("Uploaded file is not a valid image.");
            }
        } else {
            response.getWriter().println("No file uploaded.");
        }
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
