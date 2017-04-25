<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список студентов
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="${context}/students/css/signin.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <h1>
            ${requestScope.infoText}
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
    </jsp:body>
</t:genericpage>
