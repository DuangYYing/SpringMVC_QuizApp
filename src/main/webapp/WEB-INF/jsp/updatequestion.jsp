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



    <!-- Update Question Form -->
<h2>Update Question</h2>
<form action="updatequestion" method="post">
<%--    <label for="question.question_Id">Question ID:</label>--%>
<%--    <input type="text" id="questionId2" name="questionId" required>--%>

<%--    <c:forEach var="question" items="${allQuestions}" varStatus="loop">--%>
<%--    <tr>--%>
<%--        <input type="hidden" name="questionId" value="${question.question_id}" />--%>
<%--        <td><p>Question: <input type="text" name="questionText" value="${question.description}" /></p> </td>--%>
<%--        <td>--%>
<%--            <c:forEach var="i" begin="1" end="5">--%>
<%--                <p>Choice ${i}:--%>
<%--                    <input type="hidden" name="choiceId${i}" value="${question.choices.get(i-1).choice_id}" />--%>
<%--                    <input type="text" name="choice${i}" value="${question.choices.get(i-1).choice_description}" />--%>
<%--                    IsCorrect:${question.choices.get(i-1).correct}--%>
<%--                        <input type="text" name="choiceCorrect${i}" value="${question.choices.get(i-1).correct}" />--%>
<%--                </p>--%>
<%--        </td>--%>
<%--        </c:forEach>--%>
<%--    </tr>--%>
<%--        <input type="submit" value="Update Question" style="display: block; margin: 0 auto; text-align: center;" />--%>
<%--    </c:forEach>--%>

    <table>
        <c:forEach var="question" items="${allQuestions}" varStatus="loop">
        <tr>
            <input type="hidden" name="questionId" value="${question.question_id}" />
            <td><p>${loop.index+1}.Question: <input type="text" name="questionText" value="${question.description}" /></p> </td>
            <td>
                <c:forEach var="i" begin="1" end="5">
                <p>Choice ${i}:
                    <input type="hidden" name="choiceId${i}" value="${question.choices.get(i-1).choice_id}" />
                    <input type="text" name="choice${i}" value="${question.choices.get(i-1).choice_description}" />
                    IsCorrect:${question.choices.get(i-1).correct}
                    <input type="text" name="choiceCorrect${i}" value="${question.choices.get(i-1).correct}" />
                </p>
                </c:forEach>
            </td>
            <td><input type="submit" value="Update Question" /></td>
        </tr>

        </c:forEach>
    </table>


</form>

<!-- Disable Question Form -->

</body>
</form>
</html>