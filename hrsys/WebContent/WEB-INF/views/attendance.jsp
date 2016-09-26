<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <jsp:include page="template_top.jsp" /> --%>

<script src="<c:url value='/resources/js/attendance.js' />"></script>

<div class="container">
	<div class="alert alert-warning">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Tip!</strong> Select an employee and choose a date to see the attendance record.
	</div>
	
	<div class="col-sm-3">
		<select class="form-control" name="employeeID" id="employeeID">
			<option selected disabled>Select an employee</option>
		</select> 
		<br>
		<div id="calendar"></div>
	</div>

	<div class="col-sm-9">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Employee</th>
					<th>Date</th>
					<th>In Time</th>
					<th>Out Time</th>
					<th>Comment</th>
				</tr>
			</thead>
			<tbody id="attendance-table-body">

			</tbody>
		</table>
	</div>
</div>

