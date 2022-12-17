<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<h1>Modify a group</h1>
<br><br>
<form action="/modifyTicket" method="post">
    <label for="name">Ticket's name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>
    <label for="group" id="group" name="group">Select a group:</label>
    <select multiple ="true" path="???">
        <option items="${groups}" itemValue="groupName" itemLabel="groupName"></option>
    </select>
    <label for="assigner" id="assigner">Select a worker:</label>
    <select multiple="true" path="??">
        <option items="${users}" itemValuse="username" itemLabel="username"></option>
    </select>
    <label for="priority" id="priority">Priority:</label>
    <select multiple="true" path="??">
        <option items="${priority}"itemValuse="priority" itemLabel="priority"></option>
    </select>
    <label for="ticketdeadline">Deadline:</label>
    <input type="datetime-local" id="ticketdeadline" name="ticketdeadline">
    <input type="submit" value="Modify">
</form>

<jsp:include page="_footer.jsp"/>