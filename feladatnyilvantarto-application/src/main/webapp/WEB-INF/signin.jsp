<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>

     <c:if test="${param.error != null}">
         <span style="color: red">
             Invalid username or password.
         </span>
     </c:if>
    <c:if test="${param.logout != null}">
             <span style="color: green">
                 You have been logged out.
             </span>
    </c:if>
    <form:form id="command" method="POST" action="signin">
        <label>Username <input type="text" name="username"></label>
        <label>Password <input type="password" name="password"></label>
        <input type="submit" value="Sign In"/>
    </form:form>

<jsp:include page="_footer.jsp"/>
