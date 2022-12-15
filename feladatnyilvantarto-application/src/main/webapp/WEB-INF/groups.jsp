<jsp:include page="_header.jsp"/>

<h1>Groups</h1>

<h2>${username}'s groups:</h2>

<div id ="content">

</div>

<script>
    fetch("/api/groups")
    .then(res => res.json())
    .then((groups) => {
        console.log(groups);
    let result = "";
    groups.forEach(function(group, index) {
        result += "<h2>Leader: " + group.leader + "</h2>" +
            "<table border = 1>" +
            "<tr><td>Worker</td><td>Ticket's number</td></tr>";
        group.users.forEach(function (worker, index) {
            result += "<tr>" +
                "<td>" + worker.name + "</td>" +
                "<td>" + worker.count + "</td>";
        });
        result += "</table>";
    });
    document.getElementById("content").innerHTML = result;
    });
</script>



<jsp:include page="_footer.jsp"/>