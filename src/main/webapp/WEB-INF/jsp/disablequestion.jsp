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

</head>
<body>
<h2 >Quiz Question Management</h2>


<!-- Disable Question Form -->
<h2>Disable Question</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Question</th>
        <th>Enabled</th>
        <th>Action</th>
    </tr>
    <c:forEach var="question" items="${allQuestions}">
        <tr>
            <td>${question.question_id}</td>
            <td>${question.description}</td>
            <td>${question.active}</td>

            <td>
                <c:choose>
                    <c:when test="${question.active}">
                        <form action="disablequestion" method="post">
                            <input type="hidden" name="questionId" value="${question.question_id}" />
                            <input type="submit" name="status" value="Disable" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="disablequestion" method="post">
                            <input type="hidden" name="questionId" value="${question.question_id}" />
                            <input type="submit" name="status" value="Activate" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</form>
</html>