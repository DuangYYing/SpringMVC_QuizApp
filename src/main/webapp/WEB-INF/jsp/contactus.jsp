<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        h1 {
            text-align: center;
            margin-top: 40px;
        }
        table {
            width: 80%;
            margin: 40px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #333;
            color: #fff;
        }
        h2 {
            margin: 20px 0;
        }
        a {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            text-align: center;
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
                    <c:choose>
                        <c:when test="${user.admin == true}">
                            <li><a href="/usersquizresult">Users Quiz Results</a></li>
                            <li><a href="/userprofile">User Profile</a></li>
                            <li><a href="/quizmanage">Manage Quiz</a></li>
                            <li><a href="/viewfeedbacks">View Feedbacks</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/feedback">Feedback</a></li>
                            <li><a href="/contactus">Contact Us</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>
    <title>Contact Us</title>
</head>
<body>
<h1>Contact Us</h1>
<div style="display:flex; justify-content:center;">
<form action="/contactus" method="post">
    <label for="subject">Subject:</label>
    <input type="subject" id="subject" name="subject" required>
    <br><br>
    <label for="message">Message:</label>
    <textarea id="message" name="message" rows="5" required></textarea>
    <br><br>
    <input type="submit" value="Submit">
</form>
</div>
</body>
</html>