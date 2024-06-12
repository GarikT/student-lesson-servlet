<%@ page import="org.example.studentlessonservlet.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<% if(session.getAttribute("user") != null){
    User user = (User)session.getAttribute("user");
%>
Welcome <%=user.getName() + " " + user.getSurname()%><br>   <a href="/logout">Logout</a><br>
<%}%>
<a href="/lessons">View All Lessons</a>
<a href="/students">View All Students</a>
</form>
</body>
</html>