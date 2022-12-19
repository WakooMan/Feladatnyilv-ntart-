<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>
<main>
<article class="taskhand">
    <h2>TaskHandler</h2>
    <h4>Task in progress:</h4>
        <table>
    <tr class="taskh2">

        <td class="taskh">
        <c:choose>
        <c:when test="${currentticket != null}"><a href="${currentticket.url}"><button class="Task">${currentticket.name}</button></a>
            </td> <td class="taskh"><form action="/ticket/pauseaction/${currentticket.id}" method="post">
        <input class="stop" type="submit" value="Stop working">
    </form></td>
        </c:when>
        <c:otherwise><h6>There is no current ticket.</h6></c:otherwise>
        </c:choose>
                </tr>
</table>
    <p>Today's working time: <span class="timedb"> ${timeworkedtoday}</span>
    <br>Time spent working in the last 7 days: <span class="timedb">${timeworkedlastweek}</span>
        <br>Time spent working in the past month: <span class="timedb">${timeworkedlastmonth}</span></p>
</article>
    <div class="main">
        <aside>
            <h4>Notifications</h4>
            <div id="notifications">

            </div>
        </aside>
        <section>
            <h3>Tickets Assigned To You</h3>
            <h5>Tickets in progress:</h5>
            <div id="started">

            </div>
            <h5>Tickets not started yet:</h5>
            <div id="waiting">

            </div>
            <h5>Due this week: </h5>
            <div id="weekend">

            </div>
            <h5>Tickets created by you: </h5>
            <div id="created">

            </div>
        </section>
    </div>
</main>
<script>
    function callapi(apiurl,func,object,domobject)
    {
        fetch(apiurl)
            .then(res => res.json())
            .then((obj) => {
                domobject.innerHTML = func(obj,object);
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
                    result += (isleader)?"Started?":"";
                    result += "</th>" +
                    "</tr>";
            tickets.forEach(function (ticket,index)
            {
                result += "<tr>" +
                    "<td>" + ticket.priority + "</td>" +
                    "<td>" + ticket.description + "</td>" +
                    "<td>" + ticket.deadline + "</td>" +
                    "<td>";
                    result += (isleader)?((ticket.started)?"yes":"no"):"<a href='/ticket/" + ticket.id + "'>View</a>";
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

    function notificationresult(notifications,object)
    {
        let result = "";
        if(notifications.length>0)
        {
            notifications.forEach(function(notification,index)
            {
                result += "<article>";
                result += "<form action='/dashboard/removenotification/" + notification.id +"' method='post'><label for='not' >TYPE: " + notification.type + " <input id='not' type='submit' value='X'/></label></form>";
                result += "<p>" + notification.message + "</p>";
                result += "<p>" + notification.time + "</p>"
                result += "</article>";
            });
        }
        return result;
    }

    callapi("/api/tickets/started/yes",tableresult,false,document.getElementById("started"));
    callapi("/api/tickets/started/no",tableresult,false,document.getElementById("waiting"));
    callapi("/api/tickets/weekend",tableresult,false,document.getElementById("weekend"));
    callapi("/api/tickets/created",tableresult,true,document.getElementById("created"));
    callapi("/api/notifications",notificationresult,null,document.getElementById("notifications"));
</script>
<jsp:include page="_footer.jsp"/>