package org.example.studentlessonservlet.servlet;
import org.example.studentlessonservlet.manager.UserManager;
import org.example.studentlessonservlet.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User userByEmail = userManager.getUserByEmail(email);
        if(userByEmail != null && userByEmail.getPassword().equals(password)){
            req.getSession().setAttribute("user", userByEmail);
            resp.sendRedirect(req.getContextPath() + "/home");
        }else{
            req.getSession().setAttribute("msg", "invalid email or password, please register");
            resp.sendRedirect("/register");
        }
    }
}
