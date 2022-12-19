<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>
<div class="main2">

<h1>Details of this ticket:</h1>
<h2 class="ticketname">${ticket.name}</h2>

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

    <table class="ticketdetail">
        <tr>
            <th>Task name</th>
            <th>Assigner</th>
            <th>Added</th>
            <th>Group</th>
            <th>Priority</th>
            <th>Deadline</th>

        </tr>
        <tr>
            <td>${ticket.name}</td>
            <th>${ticket.assigner.name}</th>
            <td>${ticket.date}</td>

            <td>${ticket.group.groupName}</td>
            <td>${ticket.priority}</td>
            <td>${ticket.deadline}</td>
        </tr>
    </table>
    <h5>Description:</h5>
    ${ticket.description}


    <table class="invi"><tr>
        <c:if test="${UserHasNoCurrent && userIsAssignee && !ticketFinished}">
            <td>
                <form action="/ticket/startaction/${ticket.id}" method="post">
                    <input type="submit" value="Start">
                </form>
            </td>
        </c:if>
        <c:if test="${userHasCurrentThis && !ticketFinished}">
            <td>
                <form action="/ticket/pauseaction/${ticket.id}" method="post">
                    <input type="submit" value="Pause">
                </form>
            </td>
        </c:if>
        <c:if test="${(userIsAssigner || userIsGroupLeader || userIsAssignee) && !ticketFinished}">
            <td>
                <form action="/ticket/finishaction/${ticket.id}" method="post">
                    <input type="submit" value="Finish">
                </form>
            </td>
        </c:if>
        <c:if test="${ticketFinished && (userIsAssigner || userIsGroupLeader)}">
            <td>
                <form action="/ticket/restart/${ticket.id}" method="post">
                    <input type="submit" value="Restart">
                </form>
            </td>
        </c:if>
        <c:if test="${!unassignedEmpty && (userIsAssigner || userIsGroupLeader) && !ticketFinished}">
            <td>
                <button class="butty" onclick="showAdd()">Add assignee</button>
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
            </td>
        </c:if>
        <c:if test="${userIsAssignee && !ticketFinished}">
            <td>
                <form action="/removeassignee/action/${ticket.id}" method="post">
                    <input type="hidden" name="name" value="${username}">
                    <input type="submit" value="Unassign self">
                </form>
            </td>
        </c:if>
        <c:if test="${!userIsAssignee && !ticketFinished}">
            <td>
                <form action="/addassignee/action/${ticket.id}" method="post">
                    <input type="hidden" name="name" value="${username}">
                    <input type="submit" value="Assign self to ticket">
                </form>
            </td>
        </c:if>
        <c:if test="${!assigneesEmpty && (userIsAssigner || userIsGroupLeader ) && !ticketFinished}">
            <td>
                <button class="butty" onclick="showRm()">Remove assignee</button>
                <div id="removeassignee">
                    <form action="/removeassignee/action/${ticket.id}" method="post">
                        <label for="remove">Select user to remove:</label><select name="name" id="remove">
                        <c:forEach items="${assignees}" var="user">
                            <option value="${user.username}">${user.name}</option>
                        </c:forEach>
                    </select>
                        <input type="submit" value="Remove">
                        <input type="button" class="bt" onclick="hideRm()" value="Cancel">
                    </form>
                </div>

        </c:if>
            </tr></table>
<div class="comment">
<h3>Comments</h3>
<c:forEach items="${ticket.comments}" var="comment">
    <h5>from: ${comment.userFrom.name} ${comment.date}<c:if test="${comment.taggedUser!=null}">
    @${comment.taggedUser.name}</h5>
</c:if>

    <article>${comment.message}</article>
</c:forEach>
<h4>Write Comment</h4>
<form:form action="writecomment" method="post" modelAttribute="commentform">
    <form:label class="l2" path="taggedUser">Tag User from your group:</form:label>
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

</div>



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