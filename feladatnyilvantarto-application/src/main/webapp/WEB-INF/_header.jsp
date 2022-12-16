<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.css">
</head>
<body>
<header>
    <nav>
        <ul>

            <sec:authorize access="isAnonymous()">
            <li><a href="/signin">Sign In</a></li>
            <li><a href="/signup">Sign Up</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            <li><a href="/dashboard">Dashboard</a></li>
                <li><a href="/tickets">Tickets</a></li>
                <li><a href="/groups">Groups</a></li>
            <li><a><form:form action="logout"><input type="submit" value="Logout"></form:form></a></li>
            </sec:authorize>
        </ul>
    </nav>
</header>