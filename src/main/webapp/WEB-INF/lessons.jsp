<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 5/30/24
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
</head>
<body>
<%
    List<Lesson> lessons = (List<Lesson>)request.getAttribute("lessons");
%>
Lessons | <a href="addLesson">Add Lesson</a>
<%
    if(!lessons.isEmpty()){%>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Lecturer</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <%for (Lesson lesson : lessons) { %>
    <tr>
        <td><%=lesson.getId()%></td>
        <td><%=lesson.getName()%></td>
        <td><%=lesson.getDuration()%></td>
        <td><%=lesson.getLecturer_name()%></td>
        <td><%=lesson.getPrice()%></td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a></td>
    </tr>
    <%}
    }
    %>

</table>
</body>
</html>