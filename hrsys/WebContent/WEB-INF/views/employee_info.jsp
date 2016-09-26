<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <jsp:include page="template_top.jsp" /> --%>



<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<div>
			<button class="btn btn-default" data-toggle="modal"
				data-target="#employee-modal" data-purpose="add">Add
				Employee</button>
			<button class="btn btn-default">Add User account</button>
		</div>
		<hr>
		<jsp:include page="employee_datatable.jsp" />
		<jsp:include page="employee_modal.jsp" />
	</sec:authorize>
</div>
