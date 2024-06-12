package org.example.studentlessonservlet.servlet;


import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.manager.StudentManager;
import org.example.studentlessonservlet.manager.UserManager;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.User;
import org.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class AddStudentServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();
    private UserManager userManager = new UserManager();
    private final String UPLOAD_DIRECTORY = "/Users/garegintonoyan/IdeaProjects/student-lesson-servlet/uploadDirectory";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("studentName");
        String surname = req.getParameter("studentSurname");
        String email = req.getParameter("studentEmail");

        if(!studentManager.studentExists(email)) {
            int age = Integer.parseInt(req.getParameter("studentAge"));
            int lessonId = Integer.parseInt(req.getParameter("lessonId"));
            User user = (User) req.getSession().getAttribute("user");

            //getting a picture
            Part picture = req.getPart("picture");
            String pictureName = null;

            if (picture != null && picture.getSize() > 0) {
                pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
                picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
            }

            studentManager.add(Student.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .picName(pictureName)
                    .lesson(lessonManager.getLessonById(lessonId))
                    .user(user)
                    .build());
            resp.sendRedirect("/students");
        }else{
            req.getSession().setAttribute("msg", "Student already exists");
            List<Lesson> lessons = lessonManager.getLessons();
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);

        }
    }
}
