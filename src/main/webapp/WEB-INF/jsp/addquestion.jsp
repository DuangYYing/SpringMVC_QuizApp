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
<h2>Add Question</h2>

<!-- Add Question Form -->
<%--<h3>Add Question</h3>--%>
<form action="addquestion" method="post">
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
    <table>
        <tr>
            <td><label for="question">Question Description:</label></td>
            <td><input type="text" id="question" name="question_description" required></td>
        </tr>
        <c:forEach var="i" begin="1" end="5">
            <tr>
                <td><label for="choice${i}">Option ${i}:</label></td>
                <td><input type="text" id="choice${i}" name="choice${i}_description" required></td>
            </tr>
        </c:forEach>
        <tr>
            <td><label for="category">Category:</label></td>
            <td>
                <select id="category" name="categories">
                    <c:forEach var="categories" items="${categories}" >
                        <option value=${categories.category_id}>${categories.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="correctAnswer">Correct Answer:</label></td>
            <td>
                <select id="correctAnswer" name="correctAnswer_idx">
                    <c:forEach var="i" begin="1" end="5">
                        <option value=${i}>Option ${i}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add Question" style="display: block; margin: 0 auto; text-align: center;">
</form>


</body>
</form>
</html>