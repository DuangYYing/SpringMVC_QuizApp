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
    <title>Quiz Question Management</title>
</head>
<body>
<h2>Quiz Question Management</h2>

<li><a href="/addquestion">Add Question</a></li>
<li><a href="/updatequestion">Update Question</a></li>
<li><a href="/disablequestion">Disable Question</a></li>
<%--<form action="/addquestion" method="get">--%>
<%--    <input type="submit" value="Add Question">--%>
<%--</form>--%>

<%--<form action="/updatequestion" method="get">--%>
<%--    <input type="submit" value="Update Question">--%>
<%--</form>--%>

<%--<form action="/disablequestion" method="get">--%>
<%--    <input type="submit" value="Disable Question">--%>
<%--</form>--%>

<%--<!-- Add Question Form -->--%>
<%--&lt;%&ndash;<h3>Add Question</h3>&ndash;%&gt;--%>
<%--<form action="addQuestion" method="post">--%>
<%--    <label for="question">Question Description:</label>--%>
<%--    <input type="text" id="question" name="question_description" required>--%>
<%--    <c:forEach var="i" begin="1" end="5">--%>
<%--        <label for="choice${i}">Option ${i}:</label>--%>
<%--        <input type="text" id="choice${i}" name="choice${i}_description" required>--%>
<%--    </c:forEach>--%>

<%--    <label for="category">Category:</label>--%>
<%--    <select id="category" name="categories">--%>
<%--        <c:forEach var="categories" items="${categories}" >--%>
<%--            <option value=${categories.category_id}>${categories.name}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>

<%--    <label for="correctAnswer">Correct Answer:</label>--%>
<%--    <select id="correctAnswer" name="correctAnswer_idx">--%>
<%--        <c:forEach var="i" begin="1" end="5">--%>
<%--            <option value=${i}>Option ${i}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>

<%--    <input type="submit" value="Add Question">--%>
<%--</form>--%>

<%--    <!-- Update Question Form -->--%>
<%--<h3>Update Question</h3>--%>
<%--<form action="updateQuestion" method="post">--%>
<%--&lt;%&ndash;    <label for="question.question_Id">Question ID:</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="text" id="questionId2" name="questionId" required>&ndash;%&gt;--%>
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
<%--        <input type="submit" value="Update Question" />--%>
<%--    </tr>--%>
<%--    </c:forEach>--%>
<%--</form>--%>

<%--<!-- Disable Question Form -->--%>
<%--<h3>Disable Question</h3>--%>
<%--<table>--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>Question</th>--%>
<%--        <th>Enabled</th>--%>
<%--        <th>Action</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="question" items="${allQuestions}">--%>
<%--        <tr>--%>
<%--            <td>${question.question_id}</td>--%>
<%--            <td>${question.description}</td>--%>
<%--            <td>${question.active}</td>--%>

<%--            <td>--%>
<%--                <c:if test="${question.active}">--%>
<%--                    <form action="disableQuestion" method="post">--%>
<%--                        <input type="hidden" name="questionId" value="${question.question_id}" />--%>
<%--                        <input type="submit" value="Disable"/>--%>
<%--                    </form>--%>
<%--                </c:if>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
</body>
</form>
</html>