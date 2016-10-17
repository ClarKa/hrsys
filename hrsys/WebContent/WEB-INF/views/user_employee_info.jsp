<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal.employeeID" var="employeeId" />
<sec:authentication property="principal.authorities[0]" var="role" />

<script>
	$(document).ready(function() {
		$.ajax({
			type : "GET",
			url : "rest/employee/employeeid/" + userEmployeeId,
		}).done(function(data) {
			$.each(data, function(key, value) {
				$("#myinfo-" + key).text(value);
			});
		}).fail(function(data) {
			alert("Employee entry no longer exists.");
		});
	});
</script>

<div class="panel panel-default">
	<div class="panel-heading">My Information</div>
	<div class="panel-body">
		<table id="user-employee-table" class="table table-hover">
			<tr>
				<th>Firstname</th>
				<td id="myinfo-firstname"></td>
				<th>Lastname</th>
				<td id="myinfo-lastname"></td>
			</tr>
			<tr>
				<th>Email</th>
				<td id="myinfo-email"></td>
				<th>Department</th>
				<td id="myinfo-departmentName"></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td id="myinfo-gender"></td>
				<th>Birthday</th>
				<td id="myinfo-birth"></td>
			</tr>
			<tr>
				<th>SSN</th>
				<td id="myinfo-ssn"></td>
				<th>Marriage</th>
				<td id="myinfo-marriage"></td>
			</tr>
			<tr>
				<th>Nationality</th>
				<td id="myinfo-nationality"></td>
				<th>Education</th>
				<td id="myinfo-education"></td>
			</tr>
			<tr>
				<th>Enrollment Date</th>
				<td id="myinfo-enrollmentDate"></td>
				<th>Position</th>
				<td id="myinfo-position"></td>
			</tr>
			<tr>
				<th>Phone Number</th>
				<td id="myinfo-phone"></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>Account Username</th>
				<td id="myinfo-username"></td>
				<th>Account Authority</th>
				<td id="myinfo-role"></td>
			</tr>

			<tbody>
			</tbody>
		</table>
	</div>

</div>