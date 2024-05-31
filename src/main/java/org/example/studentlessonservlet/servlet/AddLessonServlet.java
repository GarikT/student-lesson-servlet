package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("lessonName");
        int duration = Integer.parseInt(req.getParameter("lessonDuration"));
        String lecturer_name = req.getParameter("lecturerName");
        double price =Double.parseDouble(req.getParameter("lessonPrice"));
        lessonManager.add(Lesson.builder()
                        .name(name)
                        .duration(duration)
                        .lecturer_name(lecturer_name)
                        .price(price)
                .build());
        resp.sendRedirect("/lessons");
    }
}
