package org.example.studentlessonservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/downloadImage")
public class DownloadImageServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "/Users/garegintonoyan/IdeaProjects/student-lesson-servlet/uploadDirectory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("imageName");
        File imageFile = new File(UPLOAD_DIRECTORY, imageName);
        if(imageFile.exists()) {
            try(FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                resp.setContentType("image/jpeg");
                resp.setContentLength( (int)imageFile.length());
                try(OutputStream outputStream = resp.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while( (bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
