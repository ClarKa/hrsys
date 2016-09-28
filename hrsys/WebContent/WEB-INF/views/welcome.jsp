<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <jsp:include page="template_top.jsp" /> --%>
<sec:authentication property="principal.employee.firstname" var="firstname" />
<sec:authentication property="principal.employee.lastname" var="lastname" />
<sec:authentication property="principal.authorities" var="role" />

<h1 class="text-center"> Hello ${ firstname } ${ lastname }!</h1>
<h3 class="text-center">
	You are logged in as ${ role }
</h3>

<p>
    
</p>
