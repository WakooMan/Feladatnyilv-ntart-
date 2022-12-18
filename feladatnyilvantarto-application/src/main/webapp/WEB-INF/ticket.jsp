<jsp:include page="_header.jsp"/>

<h1>${id}</h1>

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


<jsp:include page="_footer.jsp"/>