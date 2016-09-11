<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />

<div class="container">

	<sec:authorize access="hasRole('ADMIN')">
    	<jsp:include page="employee_datatable.jsp" />
    	<jsp:include page="employee_modal.jsp" />
	</sec:authorize>

</div>


