<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 08.02.2024
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<img src="${pageContext.request.contextPath}/images/cv.jpg" alt="User image">

    <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
        <label for="name">Name:
            <input type="text" name="name" id="name">
        </label><br>
        <label for="birthday">Birthday:
            <input type="date" name="birthday" id="birthday" required>
        </label><br>
        <label for="imageId">Image:
            <input type="file" name="image" id="imageId" required>
        </label><br>
        <label for="email">Email:
            <input type="text" name="email" id="email">
        </label><br>
        <label for="password">Password:
            <input type="password" name="password" id="password">
        </label><br>
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select><br>
        <c:forEach var="sex" items="${requestScope.sexes}">
            <input type="radio" name="sex" value="${sex}"> ${sex}
            <br>
        </c:forEach>
        <button type="submit">Send</button>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: crimson">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span>
                </c:forEach>
            </div>
        </c:if>
    </form>

</body>
</html>
