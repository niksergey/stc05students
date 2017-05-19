<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Войти
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <link href="<c:url value="/resources/css/signin.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="context" value="${pageContext.request.contextPath}" />

        <form class="form-signin" name='loginForm'
              action="${pageContext.request.contextPath}/login" method='POST'>
            <h2 class="form-signin-heading">Вход</h2>
            <label for="username" class="sr-only">Email address</label>
            <input type="text" id="username" class="form-control" placeholder="E-mail" name="username" required autofocus>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" class="form-control" placeholder="Пароль" name="password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Запомнить меня
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
    </jsp:body>
</t:genericpage>