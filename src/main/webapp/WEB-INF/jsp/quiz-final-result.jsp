<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            text-align: center;
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
    <h2>Quiz Result</h2>
</head>
<body>

<table >
    <tr>
        <td >Quiz Name:</td>
        <td >${quiz.name}</td>
    </tr>
    <tr>
        <td >User First Name:</td>
        <td >${quizUser.firstname}</td>
    </tr>
    <tr>
        <td >User Last Name:</td>
        <td >${quizUser.lastname}</td>
    </tr>
    <tr>
        <td >Starting Time:</td>
        <td >${quiz.time_start}</td>
    </tr>
    <tr>
        <td >End Time:</td>
        <td >${quiz.time_end}</td>
    </tr>
</table>


<table>
    <thead>
    <tr>
        <th>Question Content</th>
        <th>Options</th>
        <th>User Selected Option</th>
        <th>Correct Option</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${questionsOfQuiz}" var="question"  varStatus="loop">
        <tr>
            <td>${question.description}</td>
            <td>
                <c:forEach var="i" begin="1" end="5">
                    ${i}.${question.choices.get(i-1).choice_description}
                </c:forEach>
            </td>
            <td>${selectedChoices.get(loop.index).choice_description}</td>
            <td>${correctChoices.get(loop.index).choice_description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${score gt 3}">
    <h2>You passed this quiz!</h2>
</c:if>
<c:if test="${score le 3}">
    <h2>You failed this quiz!</h2>
</c:if>
<a href="/homepage">Take Another Quiz</a>
</body>
</html>
