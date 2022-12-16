<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="_header.jsp"/>

<br><br>
<form action="/addGroup" method="post">
    <label for="name">Group's name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="leader">Leader's name:</label><br>
    <input type="text" id="leader" name="leader"><br>
    <input type="submit" value="Add group">
</form>

<jsp:include page="_footer.jsp"/>