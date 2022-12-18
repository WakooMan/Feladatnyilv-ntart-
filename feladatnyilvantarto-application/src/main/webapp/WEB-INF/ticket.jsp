<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>
<style>#removeassignee, #addassignee { display: none; width: 300px; height: 200px; background: #7bbfa2; position: absolute }</style><div>

<h1>Details of this ticket:</h1>
<h2>${ticket.name}</h2>

<h3>Assignees:</h3>
<ul>

    <c:forEach var="entry" items="${timespent}">
        <li>
        ${entry.key}
        <c:forEach var="info" items="${entry.value}">
        ${info}
        </c:forEach>
        </li>
    </c:forEach>
</ul>


<table>
    <tr>
        <th>Task name</th>
        <th>Added</th>
        <th>Task's description</th>
        <th>Group</th>
        <th>Priority</th>
        <th>Deadline</th>
    </tr>
    <tr>
        <td>${ticket.name}</td>
        <td>${ticket.date}</td>
        <td>${ticket.description}</td>
        <td>${ticket.group.groupName}</td>
        <td>${ticket.priority}</td>
        <td>${ticket.deadline}</td>
    </tr>
</table>

<c:if test="${UserHasNoCurrent && userIsAssignee}">
<form action="/ticket/startaction/${ticket.id}" method="post">
    <input type="submit" value="I'm working on this ticket">
</form>
</c:if>
<c:if test="${userHasCurrentThis}">
    <form action="/ticket/pauseaction/${ticket.id}" method="post">
    <input type="submit" value="Pause">
    </form>
</c:if>
<c:if test="${userIsAssigner || userIsGroupLeader || userIsAssignee}">
    <form action="/ticket/finishaction/${ticket.id}" method="post">
    <input type="submit" value="I finish this ticket">
    </form>
</c:if>
<c:if test="${ticketFinished && (userIsAssigner || userIsGroupLeader)}">
<form action="/ticket/restart/${ticket.id}" method="post">
    <input type="submit" value="Restart">
</form>
</c:if>
<c:if test="${!unassignedEmpty && (userIsAssigner || userIsGroupLeader)}">
<button onclick="showAdd()">Add assignee</button>
<div id="addassignee">
    <form action="/addassignee/action/${ticket.id}" method="post">
        <label for="group">Select user to add:</label><select name="name" id="group">
                <c:forEach items="${groupMembersNotAssignees}" var="user">
                <option value="${user.username}">${user.name}</option>
            </c:forEach>
            </select>
        <input type="submit" value="Add assignee">
        <input type="button" onclick="hideAdd()" value="Cancel">
    </form>
</div>
    </c:if>
    <c:if test="${userIsAssignee}">
    <form action="/removeassignee/action/${ticket.id}" method="post">
        <input type="hidden" name="name" value="${username}">
        <input type="submit" value="Unassign self">
    </form>
    </c:if>
    <c:if test="${!userIsAssignee}">
    <form action="/addassignee/action/${ticket.id}" method="post">
        <input type="hidden" name="name" value="${username}">
        <input type="submit" value="Assign self to ticket">
    </form>
    </c:if>
    <c:if test="${!assigneesEmpty && (userIsAssigner || userIsGroupLeader)}">
    <button onclick="showRm()">Remove assignee</button>
    <div id="removeassignee">
        <form action="/removeassignee/action/${ticket.id}" method="post">
            <label for="remove">Select user to add:</label><select name="name" id="remove">
            <c:forEach items="${assignees}" var="user">
                <option value="${user.username}">${user.name}</option>
            </c:forEach>
        </select>
            <input type="submit" value="Remove selected assignee">
            <input type="button" onclick="hideRm()" value="Cancel">
        </form>
    </div>
    </c:if>


<h3>Comments</h3>
<c:forEach items="${ticket.comments}" var="comment">
    <h5>from: ${comment.userFrom.name} ${comment.date}<c:if test="${comment.taggedUser!=null}">
    @${comment.taggedUser.name}</h5>
</c:if>

    <article>${comment.message}</article>
</c:forEach>
<h4>Write Comment</h4>
<form:form action="writecomment" method="post" modelAttribute="commentform">
    <form:label path="taggedUser">Tag User from your group:</form:label>
    <form:hidden path="ticketId" value="${id}"/>
    <form:select path="taggedUser">
        <form:option value="0" label="None"/>
        <c:forEach items="${groupusers}" var="groupuser">
            <form:option value="${groupuser.id}" label="${groupuser.name}"/>
        </c:forEach>
    </form:select>
    <form:textarea path="message"/>
    <input type="submit" value="Send Comment"/>
</form:form>
<c:if test="${userIsAssigner || userIsGroupLeader}">
    <form action="/ticket/deleteaction/${ticket.id}" method="post">
        <input type="submit" value="Delete this ticket" class="btn">
    </form>
    <a href="/ticket/modifyticket/${ticket.id}"><button>Modify ticket</button></a>
    </c:if>

<script>
    function showAdd(){
        document.getElementById("addassignee").style.display = "block";
    }
    function hideAdd(){
        document.getElementById("addassignee").style.display = "none";
    }
    function showRm(){
        document.getElementById("removeassignee").style.display = "block";
    }
    function hideRm(){
        document.getElementById("removeassignee").style.display = "none";
    }
</script>


<jsp:include page="_footer.jsp"/>