<%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 6/11/24
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%if(session.getAttribute("msg") != null){%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<%session.removeAttribute("msg");%>
<%}%>
<form action="/login" method="post">
    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="Login"><br>
</form>
</body>
</html>
