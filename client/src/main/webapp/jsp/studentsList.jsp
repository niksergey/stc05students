<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список студентов
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/sidebar.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
    </jsp:attribute>

    <jsp:body>
        <t:lksidebar/>
        <c:set var="controllerMsg" value="${requestScope.controllerMsg}"/>
        <c:if test="${controllerMsg != null}">
            <div class="alert alert-warning alert-dismissible fade in" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <strong>Внимание!</strong>
                    ${controllerMsg}
            </div>
        </c:if>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="page-header">Студенты</h2>
            <form action="${path}/students/add">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </form>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>ФИО</th>
                        <th>Возраст</th>
                        <th>Группа</th>
                        <th>Редактировать</th>
                        <th>Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.students}" var="student">
                        <tr>
                            <td><c:out value="${student.getId()}"/></td>
                            <td><c:out value="${student.getName()}"/></td>
                            <td><c:out value="${student.getAge()}"/></td>
                            <td><c:out value="${student.getGroupId()}"/></td>
                            <td>
                                <form action="${path}/students/edit">
                                    <input type="hidden" name="id" value="${student.getId()}"/>
                                    <button type="submit" class="btn btn-warning">Редактировать</button>
                                </form>
                            </td>
                            <td>
                                <form action="${path}/students/delete" method="post">
                                    <input type="hidden" name="id" value="${student.getId()}"/>
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
