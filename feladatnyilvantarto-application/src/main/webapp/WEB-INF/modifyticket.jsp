<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>
<div class="main2">
<h1>Modify a ticket</h1>
<br><br>
<form:form action="/ticket/modifyticket" method="post" modelAttribute="modifyticketform">
    <form:hidden path="id" value="${id}"/>
    <form:label path="name">Ticket's name:</form:label><br>
    <form:input type="text" path="name"/><br>
    <form:errors path="name" cssStyle="color: red"/>
    <form:label path="description">Description:</form:label><br>
    <form:input type="text" path="description"/><br>
    <form:errors path="description" cssStyle="color: red"/>
    <form:label path="priority">Priority:</form:label>
    <form:select path="priority">
        <form:option value="LOW" label="Low"/>
        <form:option value="MEDIUM" label="Medium"/>
        <form:option value="HIGH" label="High"/>
    </form:select>
    <form:label path="deadline">Deadline:</form:label><br>
    <form:input type="datetime-local" path="deadline"/><br>
    <form:errors path="deadline" cssStyle="color: red"/>
    <form:button>Modify</form:button>
</form:form>
<a href="/ticket/${id}"><button>Cancel</button></a>
</div>
<jsp:include page="_footer.jsp"/>