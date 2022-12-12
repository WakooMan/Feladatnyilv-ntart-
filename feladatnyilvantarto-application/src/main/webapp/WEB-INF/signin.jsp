<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

    <form:form method="POST" action="/signin/signinaction" modelAttribute="signinform">
        <form:label path="username">Username</form:label>
        <form:input path="username"/>
        <form:errors path="username" cssStyle="color:red"/>
        <form:label path="password">Password</form:label>
        <form:input path="password" type="password"/>
        <form:errors path="password" cssStyle="color:red"/><br>
        <form:button>Sign In</form:button>
    </form:form>

<jsp:include page="_footer.jsp"/>
