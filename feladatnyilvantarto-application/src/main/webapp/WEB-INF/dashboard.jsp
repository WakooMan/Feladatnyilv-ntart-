<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>
<main>
<article>
    <h3>TaskHandler</h3>
    <p>Task in progress:
        <c:choose>
        <c:when test="${currentticket != null}"><a href="${currentticket.url}"><button>${currentticket.name}</button></a>
    <form action="/ticket/pauseaction/${currentticket.id}" method="post">
        <input type="submit" value="Stop working">
    </form>
        </c:when>
        <c:otherwise>There is no current ticket.</c:otherwise>
        </c:choose></p>
    <p>Today's working time: ${timeworkedtoday}
    <br>Time spent working in the last 7 days: ${timeworkedlastweek}
        <br>Time spent working in the past month: ${timeworkedlastmonth}</p>
</article>
    <div>
        <aside>
            <h4>Notifications</h4>
            <div id="notifications">

            </div>
        </aside>
        <section>
            <h4>Tickets Assigned To You</h4>
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
                    result += (isleader)?((ticket.started)?"igen":"nem"):"<a href='/ticket/" + ticket.id + "'>View</a>";
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