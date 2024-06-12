<%@ page import="org.example.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 5/30/24
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<%
    List<Student> students = (List<Student>)request.getAttribute("students");
%>

Students | <a href="/addStudent">Add Student</a>
<% if(!students.isEmpty()) {%>
<table>
    <tr>
        <th>ID</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Age</th>
        <th>Lesson</th>
        <th>User</th>
        <th>Delete</th>
    </tr>
    <%
        for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%></td>
        <td>
            <% if(student.getPicName() != null) { %>
            <img src="/downloadImage?imageName=<%=student.getPicName()%>" width="75">
            <%
            } else {%>
            <span>No Picture</span>
            <%
                    System.out.println(student.getName());
                    System.out.println(student.getPicName());
            }%>
        </td>
        <td><%=student.getName()%></td>
        <td><%=student.getSurname()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getLesson().getName()%></td>
        <td>
            <% if(student.getUser() != null){%>
            <%=student.getUser().getName() + " " + student.getUser().getSurname()%>
            <%}%>
        </td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <%}
}%>
</table>
</body>
</html>