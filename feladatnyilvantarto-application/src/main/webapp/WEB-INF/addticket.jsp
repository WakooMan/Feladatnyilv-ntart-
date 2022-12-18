<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<h1>Creating a new ticket</h1>
<br><br>
<form action="/addticket/action" method="post">
    <label for="name">Ticket's name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>
    <label for="groupId">Select a group:</label>
    <select name="groupId" id="groupId">
        <c:forEach items="${groups}" var="group">
            <option value="${group.id}">${group.groupName}</option>
        </c:forEach>
    </select>
    <label for="priority">Priority:</label>
    <select name="priority" id="priority">
        <option value="LOW">Low</option>
        <option value="MEDIUM">Medium</option>
        <option value="HIGH">High</option>
    </select>
    <label for="deadline">Deadline:</label>
    <input type="datetime-local" id="deadline" name="deadline">
    <input type="submit" value="Add ticket">
</form>

<jsp:include page="_footer.jsp"/>