<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Users Quiz Result</title>
</head>
<table>
    <tr>
        <th>Taken Date</th>
        <th>Category</th>
        <th>User Full Name</th>
        <th>No. of Questions</th>
        <th>Score</th>
        <th>Detailed Result Link</th>
    </tr>
    <c:forEach items="${quizResults}" var="quizResult">
        <tr>
            <td>${quizResult.takenDate}</td>
            <td>${quizResult.category}</td>
            <td>${quizResult.userFullName}</td>
            <td>${quizResult.numOfQuestions}</td>
            <td>${quizResult.score}</td>
            <td><a href="/quiz-final-result/${quizResult.quiz_id}">result link</a></td>
        </tr>
    </c:forEach>
</table>

<!-- Optional filter and sort by category/user full name -->
<form>
    <label for="categoryFilter">Filter by Category:</label>
    <select id="categoryFilter" name="categoryFilter">
        <option value="">All</option>
        <c:forEach items="${allCategories}" var="category">
            <option value="${category.category_id}" ${categoryFilter == category.name ? 'selected' : ''}>${category.name}</option>
        </c:forEach>
    </select>

    <label for="userFilter">Filter by User Id:</label>
    <select id="userFilter" name="userFilter">
        <option value="">All</option>
        <c:forEach items="${allUserIds}" var="userId">
            <option value="${userId}" ${userFilter == userId ? 'selected' : ''}>${userId}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Filter"/>
</form>

<!-- Optional pagination -->
<c:if test="${quizResultsPage != null}">
    <c:forEach begin="${quizResultsPage.firstResult}" end="${quizResultsPage.lastResult}" var="quizResult">
        <tr>
        <td>${quizResult.takenDate}</td>
        <td>${quizResult.category}</td>
        <td><a href="/quiz-final-result/${quizResult.quiz_id}">${quizResult.userFullName}</a></td>
        <td>${quizResult.numOfQuestions}</td>
        <td>${quizResult.score}</td>
        </tr>
    </c:forEach>
</c:if>