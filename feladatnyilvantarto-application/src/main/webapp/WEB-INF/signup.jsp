<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<form:form method="POST" action="/signup/signupaction" modelAttribute="userform">
    <form:label path="username">Username</form:label><br>
    <form:input path="username"/><br>
    <form:errors path="username" cssStyle="color:red"/><br>
    <form:label path="password">Password</form:label><br>
    <form:input path="password"/><br>
    <form:errors path="password" cssStyle="color:red"/><br>
    <form:button>Sign Up</form:button>
</form:form>
</body>
</html>