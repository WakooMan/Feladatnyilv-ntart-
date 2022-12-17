<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>
<main>
<article>
    <h3>TaskHandler</h3>
    <p>Task in progress:
        <c:choose>
        <c:when test="${currentticket != null}"><a href="${currentticket.url}"><button>${currentticket.name}</button></a></c:when>
        <c:otherwise>There is no current ticket.</c:otherwise>
        </c:choose></p>
    <p>Today's working time: <a><button>0</button></a></p>
</article>
    <section>
    <aside>
        <h4>Notifications</h4>
        <p>This is an aside for notifications.</p>
    </aside>

    <h4>Assigned Tickets To You</h4>
    <h5>Tickets in progress:</h5>
    <div id="started">

    </div>
    <h5>Tickets not started yet:</h5>
    <div id="waiting">

    </div>
    <h5>Tickets with deadline on this week: </h5>
    <div id="weekend">

    </div>
    <h4>Assigned Tickets By You</h4>
    <h5>Tickets created by you: </h5>
    <div id="created">

    </div>
</section>
</main>
<script>
    function callapi(apiurl,func,isleader,domobject)
    {
        fetch(apiurl)
            .then(res => res.json())
            .then((obj) => {
                domobject.innerHTML = func(obj,isleader);
            });
    }

    function tableresult(tickets,isleader)
    {
        let result = "";
        if(tickets.length > 0) {
            result +="<table>" +
                "<tr>" +
                    "<th>Priority</th>" +
                    "<th>Task</th>" +
                    "<th>DeadLine</th>" +
                    "<th>";
                    result += (isleader)?"Elkezdett?":"";
                    result += "</th>" +
                    "</tr>";
            tickets.forEach(function (ticket,index)
            {
                result += "<tr>" +
                    "<td>" + ticket.priority + "</td>" +
                    "<td>" + ticket.description + "</td>" +
                    "<td>" + ticket.deadline + "</td>" +
                    "<td>";
                    result += (isleader)?((ticket.started)?"igen":"nem"):"<a>Start the ticket</a>";
                    result += "</td>" +
                    "</tr>";
            });
            result += "</table>";
        }
        else
        {
            result += "<p>There is no such ticket.</p>";
        }
        return result;
    }

    callapi("/api/tickets/started/yes",tableresult,false,document.getElementById("started"));
    callapi("/api/tickets/started/no",tableresult,false,document.getElementById("waiting"));
    callapi("/api/tickets/weekend",tableresult,false,document.getElementById("weekend"));
    callapi("/api/tickets/created",tableresult,true,document.getElementById("created"));
</script>
<jsp:include page="_footer.jsp"/>