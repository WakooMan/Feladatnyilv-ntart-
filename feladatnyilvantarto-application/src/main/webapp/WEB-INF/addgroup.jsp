<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<br><br>
<form action="/addgroup/action" method="post">
    <label for="name">Group's name:</label><br>
    <input type="text" id="name" name="name"><br>
    <select multiple="true" path="??">
        <option items="${users}" itemValuse="username" itemLabel="username"></option>
    </select>
    <input type="submit" value="Add group">
    <script>
        <ul>
            <li>Selected Users:</li>
        </ul>
    </script>
</form>

<jsp:include page="_footer.jsp"/>