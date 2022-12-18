<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<h1>Details of this ticket:</h1>
<h2>${ticket.name}</h2>

<h3>Time spent on this task:</h3>


<table>
    <tr>
        <th>Task name</th>
        <th>Task's description</th>
        <th>Group</th>
        <th>Priority</th>
        <th>Deadline</th>
    </tr>
    <tr>
        <td>${ticket.name}</td>
        <td>${ticket.description}</td>
        <td>${ticket.name}</td>
        <td>${ticket.priority}</td>
        <td>${ticket.deadline}</td>
    </tr>
</table>


<form action="/ticket/startaction/${ticket.id}" method="post"></form>
    <input type="submit" value="I'm working on this ticket">

    <form action="/ticket/pauseaction/${ticket.id}" method="post"></form>
    <input type="submit" value="Pause">

    <form action="/ticket/finishaction/${ticket.id}" method="post"></form>
    <input type="submit" value="I finish this ticket">


<h3>Comments</h3>
<c:forEach items="${ticket.comments}" var="comment">
    <h5>from: ${comment.userFrom.name} ${comment.date}</h5>
    <article>${comment.message}</article>
</c:forEach>
<h4>Write Comment</h4>
<form:form action="writecomment" method="post" modelAttribute="commentform">
    <form:label path="taggedUser">Tag User from your group:</form:label>
    <form:hidden path="ticketId" value="${id}"/>
    <form:select path="taggedUser">
        <form:option value="-1" label="None"/>
        <c:forEach items="${groupusers}" var="groupuser">
            <form:option value="${groupuser.id}" label="${groupuser.name}"/>
        </c:forEach>
    </form:select>
    <form:textarea path="message"/>
    <input type="submit" value="Send Comment"/>
</form:form>


<jsp:include page="_footer.jsp"/>