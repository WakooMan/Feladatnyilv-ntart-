<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>

<h1>Groups</h1>

<c:forEach items="${groups}" var="group">
<table>
    <tr>
        <th>Workers</th>
        <th>Ticket's number</th>
    </tr>

<c:forEach items="${workers}" var="worker">
    <tr>
        <td>${worker.name}</td>
        <td>${worker.ticketnumber}</td>
    </tr>
</c:forEach>
</table>
</c:forEach>




<jsp:include page="_footer.jsp"/>