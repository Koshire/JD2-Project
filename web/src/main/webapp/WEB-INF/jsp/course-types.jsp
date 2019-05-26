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
    <c:forEach items="${requestScope.allTypes}" var="type">
        <span>${type.name}</span><br>
    </c:forEach>
</div>
</body>
</html>
