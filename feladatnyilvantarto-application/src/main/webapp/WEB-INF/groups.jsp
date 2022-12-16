<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>
<h1>Groups</h1>

<h2>${username}'s groups:</h2>

<a href="/addgroup">Add new group</a>

<c:forEach items="${groupList}" var="group">
    <table>
        <tr>
            <th>Workers</th>
            <th>Ticket's number</th>
        </tr>

        <div id ="content">
            <c:forEach items="${group.workers}" var="worker">
            <tr>
                <td>${worker.name}</td>
                <td>${worker.assignedTickets.size()}</td>
            </tr>
            </c:forEach>
        </div>
    </table>
    <div>
        <a href="/tickets">Tickets</a>
        <a href="/modifygroup">Modify this group</a>
        <form:form action="/removegroup/action/${group.id}">
            <button type="submit">Remove this group</button>
        </form:form>
    </div>
</c:forEach>




<jsp:include page="_footer.jsp"/>