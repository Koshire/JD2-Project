<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: koshire
  Date: 05.05.19
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<div>
    <form>
        <label>Role:
            <select name="role">
                <option value="">ALL</option>
                <option>STUDY</option>
                <option>TEACH</option>
                <option>ADMIN</option>
            </select>
        </label><br>
        <label>FIO:
            <input type="text" name="fio" value="${requestScope.fio}"/>
        </label><br>
        <label>BlockList:
            <input type="checkbox" name="blocklist"/>
        </label><br>

        <label for="view">Показать на странице: </label>
        <input type="text" name="view" VALUE="${requestScope.view}"><br>

        <label for="page">Page</label>
        <input type="text" name="page" VALUE="${requestScope.page}"><br>
        Из ${requestScope.pages}

        <input type="submit">

    </form>

    <%--<p>Страницы :</p>
    <c:forEach var = "i" begin = "1" end = "${requestScope.pages}">
        <a href="${pageContext.request.contextPath}/all-logins?=${course.id}"></a>
        <c:out value = "${i} "/>
    </c:forEach>--%>

    <%--<span>${requestScope.allLogins}</span>--%>
    <c:forEach items="${requestScope.allLogins}" var="login">
        <span>${login.email}, ${login.password}, ${login.role}, ${login.id}</span><br>
    </c:forEach>
</div>
</body>
</html>
