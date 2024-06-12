package org.example.studentlessonservlet.filter;

import org.example.studentlessonservlet.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/addLesson", "/addStudent",
        "/deleteLesson", "/deleteStudent", "/downloadImage", "/home",
        "/lessons", "/students", "/logout",
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect("/");
        }
    }
}
