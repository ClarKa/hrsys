<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<sec:authorize access="hasRole('ADMIN')">
		<div class="panel panel-default">
			<div class="panel-heading">Employee Information</div>
			<div class="panel-body">
				<div>
					<button class="btn btn-default" data-toggle="modal" data-target="#employee-modal" data-purpose="add">Add Employee</button>
					<button class="btn btn-default">Add User account</button>
				</div>
				<hr>
				<jsp:include page="employee_datatable.jsp" />
				<jsp:include page="employee_modal.jsp" />
			</div>
		</div>
	</sec:authorize>

	<sec:authorize access="hasRole('USER')">
		<jsp:include page="user_employee_info.jsp" />
	</sec:authorize>
</div>
