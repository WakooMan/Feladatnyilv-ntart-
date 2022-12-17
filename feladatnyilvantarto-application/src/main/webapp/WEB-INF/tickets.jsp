<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>

<h1>Tickets</h1>

<h2>${username}'s tickets:</h2>


<form action="/addticket">
    <input type="submit" value="Add new ticket" />
</form>

<c:forEach items="${ticketListByGroup}" var="ticket">
    <table>
        <tr>
            <th>Priority</th>
            <th>Task</th>
            <th>Deadline</th>
            <th>Group</th>
            <th>Is it started?</th>
        </tr>

        <div id ="content">
            <c:forEach items="${ticket}" var="worker">
                <tr>
                    <td>${worker.name}</td>
                    <td>${worker.assignedTickets.size()}</td>
                </tr>
            </c:forEach>
        </div>
    </table>
    <div>
        <a href="/modifyticket">Modify ticket</a>
    </div>
</c:forEach>

<jsp:include page="_footer.jsp"/>