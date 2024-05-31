package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.manager.StudentManager;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();

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
        int age = Integer.parseInt(req.getParameter("studentAge"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));

        studentManager.add(Student.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonManager.getLessonById(lessonId))
                .build());
        resp.sendRedirect("/students");
    }
}
