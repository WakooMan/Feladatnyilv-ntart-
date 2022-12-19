<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>

<div class="main2">    <h1>Groups</h1>

<h3>${username}'s groups:</h3>
<button onclick="showCreate()">Create new group</button>
<div id="creategroup">
    <form action="/addgroup/action" method="post">
        <label for="name">Group's name:</label><br>
        <input type="text" id="name" name="name"><br>
        <input type="submit" value="Create new group">
        <input type="button" onclick="hideCreate()" value="Cancel">
    </form>
</div>
<table>

    <c:choose>
        <c:when test="${userHasGroupsLed || userHasGroups}">
            <c:if test="${userHasGroupsLed}">
                <tr>
                    <td>
                        <h1>Groups Led By You</h1>
                        <c:forEach items="${groupListLed}" var="group">
                            <div class="groupname"><h2>${group.groupName}</h2></div>
                            <form action="/modifygroup/${group.id}/action" method="post">
                                New name:
                                <input type="text"  name="name">
                                <input type="submit" value="Modify group name">
                            </form>
                            <table>
                                <tr>
                                    <th>Members</th>
                                    <th>Number of tickets taken</th>
                                </tr>


                                <c:forEach items="${group.workers}" var="worker">
                                    <tr>
                                        <td>${worker.name} (${worker.username})</td>
                                        <td>${worker.assignedTickets.size()}</td>
                                        <td>
                                            <form:form action="/removemember/action/${group.id}/${worker.id}">
                                                <button type="submit">Remove member</button>
                                            </form:form>

                                        </td>




                                    </tr>
                                </c:forEach>

                            </table>
                            <div>
                                <a href="/tickets">Tickets</a>


                                <form action="/${group.id}/addmember/action" method="post">
                                    <label class="l2" for="name">Username:</label>
                                    <input type="text" name="name"><br>
                                    <input type="submit" value="Add new member">
                                </form>

                                            <form:form action="/removegroup/action/${group.id}">
                                                <button class="leave" type="submit">Delete group</button>
                                            </form:form>

                            </div>
                        </c:forEach>
                    </td>
                </tr>
            </c:if>
            <c:if test="${userHasGroups}">
                <tr>
                <td>

                <h1>Groups You're A Member Of</h1>


                <c:forEach items="${groupListMember}" var="group">
                    <div class="groupname"><h2>${group.groupName}</h2></div>
                    <h3>Leader: ${group.leader.name}</h3>
                    <table>
                        <tr>
                            <th>Members</th>
                            <th>Number of tickets taken</th>

                        </tr>


                        <c:forEach items="${group.workers}" var="worker">
                            <tr>
                                <td>${worker.name} (${worker.username})</td>
                                <td>${worker.assignedTickets.size()}</td>

                            </tr>
                        </c:forEach>

                    </table>
                    <div>
                        <a href="/tickets/filter?group=${group.id}&status=active&assignee=all">Active tickets</a>
                        <a href="/tickets/filter?group=${group.id}&status=active&assignee=unassigned">Unassigned tickets</a>
                        <form:form action="/leavegroup/action/${group.id}">
                            <button class="leave" type="submit">Leave group</button>
                        </form:form>

                    </div>

                </c:forEach>
            </c:if>
            </td>
            </tr>
        </c:when>
        <c:otherwise>
            <br><br><br><br><br><br><br><br>
            <p>You don't have any groups yet!
                Try creating one, or ask the leader of your team to add you!</p>
        </c:otherwise>
    </c:choose>


</table>
</div>
<script>
    function showCreate(){
        document.getElementById("creategroup").style.display = "block";
    }
    function hideCreate(){
        document.getElementById("creategroup").style.display = "none";
    }
</script>


<jsp:include page="_footer.jsp"/>