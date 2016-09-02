<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="error" items="${msg}">
    <h4 align="center" style="color:red"> ${error} </h4>
</c:forEach>