<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 19.04.17
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<% String message = (String) request.getAttribute("value"); %>
 <h1>
     <%=message%>
 </h1>

<c:forEach items="${requestScope.list}" var="student">
    <ol type="I" start="3">
        <li>
            <td><c:out value="${student.getId()}"></c:out></td>
            <td><c:out value="${student.getName()}"></c:out></td>
            <td><c:out value="${student.getAge()}"></c:out></td>
            <td><c:out value="${student.getGroupId()}"></c:out></td>
            <form method="post" action="/students/listStudents">
                <input type="submit" value="deleteStudent"/>
                <input type="hidden" name="deleteStudent" value="${student.getId()}"/>
            </form>
        </li>
    </ol>
</c:forEach>

<form method="post" action="/students/listStudents">
    <input type="text" name="name"/>
    <input type="text" name="age"/>
    <input type="text" name="groupId"/>
    <input type="submit" value="addStudent"/>
</form>


</body>
</html>
