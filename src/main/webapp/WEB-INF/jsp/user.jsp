<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        #global-nav {
            background-color: #333;
            padding: 10px;
        }
        #global-nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: space-between;
        }
        #global-nav li {
            margin: 0 10px;
        }
        #global-nav a {
            color: #fff;
            text-decoration: none;
        }
    </style>
    <nav id="global-nav">
        <ul>
            <li><a href="/homepage">Home</a></li>
            <c:choose>
                <c:when test="${empty user}">
                    <li><a href="/login">Login</a></li>
                    <li><a href="/register">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/logout">Logout</a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="/feedback">Feedback</a></li>
            <li><a href="/contactus">Contact Us</a></li>
        </ul>
    </nav>
    <title>User</title>
</head>

<body>
<form method="post" action="user">
    <label>
        id:
        <input type="text" name="id">
    </label>
    <label>
        username:
        <input type="text" name="username">
    </label>
    <label>
        password:
        <input type="text" name="password">
    </label>
    <button type="submit">submit</button>
</form>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.phone}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>
