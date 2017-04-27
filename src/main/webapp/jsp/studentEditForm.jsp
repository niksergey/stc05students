<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:addeditform>
        <jsp:attribute name="dataform">
            <c:set var="context" value="${pageContext.request.contextPath}" />

            <h2 class="page-header">Студент</h2>

            <c:set var="student" value="${requestScope.student}" ></c:set>

            <div class="container">
                <form class="form-signup" action="${context}/students/edit" method="post">
                    <h2 class="form-signup-heading">Редактирование</h2>
                    <input type="text" class="form-control" name="name" value="${student.getName()}"
                           placeholder="Имя" required autofocus>
                    <input type="number" class="form-control" name="age" value="${student.getAge()}"
                           placeholder="Возраст" required>
                    <input type="number" class="form-control" name="groupId" value="${student.getGroupId()}"
                           placeholder="Группа" required>
                    <input type="hidden" name="id" value="${student.getId()}">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>
                </form>
            </div>
    </jsp:attribute>
</t:addeditform>
