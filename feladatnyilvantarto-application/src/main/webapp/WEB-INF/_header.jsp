<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="hu">
<head>
    <meta charset="US-ASCII">

    <link rel="stylesheet" href="https://cdn.simplecss.org/simple.css">
    <link rel="stylesheet" type="text/css" href="../css/general.css">
    <title>Taskmanager</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <sec:authorize access="isAnonymous()">
            <li><a class="navb" href="/signin">Sign In</a></li>
            <li><a class="navb" href="/signup">Sign Up</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            <li><a class="navb" href="/dashboard">Dashboard</a></li>
                <li><a class="navb" href="/tickets">Tickets</a></li>
                <li><a class="navb" href="/groups">Groups</a></li>
                <li style="color:white">User: ${fullname}</li>
            <li><a class="navb"><form:form action="/logout"><input id="logout" type="submit" value="Logout"></form:form></a></li>
            </sec:authorize>
        </ul>
    </nav>
</header>