<%--
  Created by IntelliJ IDEA.
  User: garegintonoyan
  Date: 5/30/24
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
Add Lesson<br>
<form method="post" action="/addLesson">
    Lesson Name: <input type="text" name="lessonName"><br>
    Lesson Duration: <input type="text" name="lessonDuration"><br>
    Lecturer Name: <input type="text" name="lecturerName"><br>
    Lesson Price: <input type="text" name="lessonPrice"><br>
    <input type="submit" value="add">
</form>
</body>
</html>
