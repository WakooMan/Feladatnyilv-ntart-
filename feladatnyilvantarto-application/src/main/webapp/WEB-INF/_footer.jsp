<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer class="bg-dark">
    <div class="container text-white">
        <c:choose>
            <c:when test="${LocalDate.now().year == 2022}">
                &copy; 2022 Task manager/>
            </c:when>
            <c:otherwise>
                &copy; 2022-${LocalDate.now().year} Task manager
            </c:otherwise>
        </c:choose>
    </div>
</footer>

</body>
</html>