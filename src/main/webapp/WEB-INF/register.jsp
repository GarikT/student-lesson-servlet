<%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 6/11/24
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%if(session.getAttribute("msg") != null){%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<%session.removeAttribute("msg");%>
<%}%>
<form action="/register" method="post">
    name: <input type="text" name="name" placeholder="your name"><br>
    surname: <input type="text" name="surname" placeholder="your surname"><br>
    email: <input type="text" name="email" placeholder="your email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="Register"><br>
</form>
</body>
</html>
