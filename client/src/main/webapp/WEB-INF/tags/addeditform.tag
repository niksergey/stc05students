<%@tag description="Form add edit template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/main/webapp/WEB-INF/tags" %>
<%@attribute name="dataform" fragment="true" %>

<t:genericpage>
    <jsp:attribute name="title">
      Cтудент
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/sidebar.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/signup.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/signin/" method="post">
                <input type="hidden" name="currentSession" value="delete"/>
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
            <c:set var="context" value="${pageContext.request.contextPath}" />

            <jsp:invoke fragment="dataform"/>

        </div>
    </jsp:body>
</t:genericpage>
