<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>
<div class="main2">
<form:form method="POST" action="/signup/signupaction" modelAttribute="signupform">
    <form:label class="l2" path="username">Username</form:label>
    <form:input path="username"/>
    <c:if test="${not empty errormessage}">
        <span style="color: red">${errormessage}</span>
    </c:if>
    <form:errors path="username" cssStyle="color:red"/>
    <form:label class="l2" path="password">Password</form:label>
    <form:input path="password" type="password"/>
    <form:errors path="password" cssStyle="color:red"/>
    <form:label class="l2" path="name">Name</form:label>
    <form:input path="name"/>
    <form:errors path="name" cssStyle="color:red"/><br>
    <form:button>Sign Up</form:button>
</form:form>
</div>
<jsp:include page="_footer.jsp"/>