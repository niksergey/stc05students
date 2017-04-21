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
    <meta charset="utf-8">
    <title>List</title>
</head>
<body>
<% String message = (String) request.getAttribute("value"); %>
 <h1>
     <%=message%>
 </h1>

<c:forEach items="${requestScope.list}" var="student">
    <ol>
        <li>
            <td><c:out value="${student.getId()}"></c:out></td>
            <td><c:out value="${student.getName()}"></c:out></td>
            <td><c:out value="${student.getAge()}"></c:out></td>
            <td><c:out value="${student.getGroupId()}"></c:out></td>
            <form action="/students/manage" method="post">
                <input type="submit" value="deleteStudent"/>
                <input type="hidden" name="studentId" value="${student.getId()}"/>
                <input type="hidden" name="requestType" value="delete"/>
            </form>
        </li>
    </ol>
</c:forEach>

<form action="/students/manage" method="post">
    <input type="text" name="name"/>
    <input type="text" name="age"/>
    <input type="text" name="groupId"/>
    <input type="hidden" name="requestType" value="add"/>
    <input type="submit" value="addStudent"/>
</form>


</body>
</html>
