<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>
<div class="main2">
<h1>Creating a new ticket</h1>
<form:form action="/addticket/action" method="post" modelAttribute="addticketform">
    <form:label class="l2" path="name">Ticket's name:</form:label><br>
    <form:input type="text" path="name"/><br>
    <form:errors path="name" cssStyle="color: red"/>
    <form:label class="l2" path="description">Description:</form:label><br>
    <form:input type="text" path="description"/><br>
    <form:errors path="description" cssStyle="color: red"/>
    <form:label class="l2" path="groupId">Select a group:</form:label>
    <form:select path="groupId">
        <c:forEach items="${groups}" var="group">
            <form:option value="${group.id}" label="${group.groupName}"/>
        </c:forEach>
    </form:select>
    <form:label class="l2"  path="priority">Priority:</form:label>
    <form:select path="priority">
        <form:option value="LOW" label="Low"/>
        <form:option value="MEDIUM" label="Medium"/>
        <form:option value="HIGH" label="High"/>
    </form:select>
    <form:label class="l2" path="deadline">Deadline:</form:label>
    <form:input type="datetime-local" path="deadline"/><br>
    <form:errors path="deadline" cssStyle="color: red"/><br>
    <form:button>Add ticket"</form:button>
</form:form>

<jsp:include page="_footer.jsp"/>