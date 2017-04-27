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
        <form class="form-signin" action="${context}/" method="post">
            <h2 class="form-signin-heading">Вход</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="text" id="inputEmail" class="form-control" placeholder="E-mail" name="login" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Запомнить меня
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>
    </jsp:body>
</t:genericpage>