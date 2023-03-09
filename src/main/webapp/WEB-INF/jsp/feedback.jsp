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
    <title>Feedback</title>
    <style>
        .rating {
            display: flex;
            justify-content: center;
        }

        .rating input[type="radio"] {
            display: none;
        }

        .rating label {
            color: #ddd;
            font-size: 36px;
            cursor: pointer;
        }

        .rating label:before {
            content: '\2605';
        }

        .rating input[type="radio"]:checked ~ label {
            color: #ff0;
        }
    </style>
</head>
<body>
<h1>Feedback</h1>
<form action="/feedback" method="post">
    <div class="rating">
        <input type="radio" id="star5" name="rating" value="5" required />
        <label for="star5"></label>
        <input type="radio" id="star4" name="rating" value="4" />
        <label for="star4"></label>
        <input type="radio" id="star3" name="rating" value="3" />
        <label for="star3"></label>
        <input type="radio" id="star2" name="rating" value="2" />
        <label for="star2"></label>
        <input type="radio" id="star1" name="rating" value="1" />
        <label for="star1"></label>
    </div>
    <br />
    <div style="display:flex; justify-content:center;">
    <textarea name="message" rows="5" cols="50" placeholder="Enter your feedback here" required></textarea>
    <br />
    <input type="submit" value="Submit Feedback" />
    </div>
</form>
</body>
</html>