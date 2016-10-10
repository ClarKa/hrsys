<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal.employeeID" var="employeeId" />
<sec:authentication property="principal.authorities[0]" var="role" />

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">My Training Time</div>
		<div class="panel-body"></div>
	</div>
</div>