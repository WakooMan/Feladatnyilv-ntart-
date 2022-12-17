<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<h1>Details of this ticket:</h1>
<h1>${ticket.name}</h1>

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

<button>I'm working on this ticket</button>
<button>Pause</button>
<button>I finish this ticket</button>

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