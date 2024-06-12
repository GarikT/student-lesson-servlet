<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 5/30/24
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<%if(session.getAttribute("msg") != null){%>
<span style="color: red"><%=session.getAttribute("msg")%></span><br>
<%session.removeAttribute("msg");%>
<%}%>
<% List<Lesson> lessons = (List<Lesson>)request.getAttribute("lessons"); %>
Add Student<br>
<form method="post" action="/addStudent" enctype="multipart/form-data">
    Student Name: <input type="text" name="studentName"><br>
    Student Surname: <input type="text" name="studentSurname"><br>
    Student Email: <input type="text" name="studentEmail"><br>
    Student Age: <input type="text" name="studentAge"><br>
    Lesson: <select name="lessonId">
    <%
        for (Lesson lesson : lessons) {%>
    <option value="<%=lesson.getId()%>"><%=lesson.getName()%></option>
    <%}%>
</select><br>
    <input type="file" name="picture">
    <input type="submit" value="add">
</form>
</body>
</html>