<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

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
    <title>Quiz Homepage</title>
</head>
<body>
<h1>Quiz Categories</h1>
<ul>
    <c:forEach var="category" items="${categories}">
        <li><a href="quiz/${category.category_id}">${category.name}</a></li>
    </c:forEach>
</ul>


<h1>Quizzes Taken</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Date Taken</th>
        <th>Result</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quiz" items="${quizs}">
        <tr>
            <td>${quiz.name}</td>
            <td>${quiz.time_end}</td>
            <td>
                <a href="quiz-final-result/${quiz.quiz_id}">View Result</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>