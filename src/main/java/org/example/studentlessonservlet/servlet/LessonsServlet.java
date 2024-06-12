package org.example.studentlessonservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonsServlet extends HttpServlet {
    LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            List<Lesson> lessons = lessonManager.getLessons(user.getId());
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
