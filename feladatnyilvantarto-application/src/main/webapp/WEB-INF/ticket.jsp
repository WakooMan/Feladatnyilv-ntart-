<jsp:include page="_header.jsp"/>

<h1>${id}</h1>

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

<jsp:include page="_footer.jsp"/>