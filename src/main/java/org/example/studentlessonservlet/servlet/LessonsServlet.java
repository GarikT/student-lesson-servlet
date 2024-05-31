package org.example.studentlessonservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonsServlet extends HttpServlet {
    LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
    }
}
