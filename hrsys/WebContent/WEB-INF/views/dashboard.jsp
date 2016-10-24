<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/jquery/jquery-ui-1.12.1.custom/jquery-ui.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-formhelpers.min.css' />" rel="stylesheet" media="screen">
<link href="<c:url value='/resources/css/dashboard.css' />" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Employee Management</title>
</head>
<body>
	<script src="<c:url value='/resources/jquery/jquery-3.1.1.min.js' />"></script>
	<script src="<c:url value='/resources/jquery/jquery-ui-1.12.1.custom/jquery-ui.js' />"></script>
	<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap-formhelpers.min.js' />"></script>
	<sec:authorize access="isAuthenticated()">
        <sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
        <sec:authorize access="hasRole('USER')" var="isUser" />
        <sec:authentication property="principal.employeeID" var="employeeId" />
        <script>
            // include CSRF token.
            token = $("meta[name='_csrf']").attr("content");
            header = $("meta[name='_csrf_header']").attr("content");

            var employeeInfoUrl = "<c:url value='${urls.employeeInfoUrl}' />";
            var departmentUrl = "<c:url value='${urls.departmentUrl}' />";
            var attendanceUrl = "<c:url value='${urls.attendanceUrl}' />";
            var trainingUrl = "<c:url value='${urls.trainingUrl}' />";
            var bankInfoUrl = "<c:url value='${urls.bankInfoUrl}' />";
            var paychecksUrl = "<c:url value='${urls.paychecksUrl}' />";
            var getOneEmployeeUrl = "${urls.oneEmployeeUrl}" + "/";
            var isAdmin = "${isAdmin}";
            var userEmployeeId = "${employeeId}";

            $(document).ready(function() {
                if (isAdmin == "true") {
                    $.ajax({
                        type: "GET",
                        url: employeeInfoUrl
                    }).done(function(data) {
                        initializeTrainingEmployeeSelectList(data);
                        initializeAttendanceEmployeeSelectList(data);
                    }).fail(function() {
                        alert("Ajax failed to fetch data");
                    });
                } else {
                    $.ajax({
                        type: "GET",
                        url: employeeInfoUrl + getOneEmployeeUrl + userEmployeeId
                    }).done(function(data) {
                        initializeTrainingCalendarForUser(data);
                        initializeAttendanceEmployeeSelectList([data]);
                    }).fail(function() {
                        alert("Ajax failed to fetch data");
                    });
                }
            });

            function formSubmit() {
                $("#logoutForm").submit();
            }
        </script>
    </sec:authorize>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/' />">HR</a>
		</div>

		<sec:authorize access="isAuthenticated()">
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasRole('ADMIN')">
						<li class="active"><a data-toggle="tab" href="#welcome"> Welcome </a></li>
						<li><a data-toggle="tab" href="#employee-info"> Employee Info </a></li>
						<li><a data-toggle="tab" href="#attendance"> Attendance </a></li>
						<li><a data-toggle="tab" href="#training"> Training </a></li>
						<li><a data-toggle="tab" href="#banking"> Banking </a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('USER')">
						<li class="active"><a data-toggle="tab" href="#welcome"> Welcome </a></li>
						<li><a data-toggle="tab" href="#employee-info"> Personal Info </a></li>
						<li><a data-toggle="tab" href="#attendance"> Attendance </a></li>
						<li><a data-toggle="tab" href="#training"> Training </a></li>
						<li><a data-toggle="tab" href="#banking"> Bank Info </a></li>
					</sec:authorize>
				</ul>
				<form action="<c:url value='/perform_logout' />" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/' />">
							<span class="glyphicon glyphicon-user"></span>
							<sec:authentication property="principal.username" />
						</a>
					<li><a href="javascript:formSubmit()">
							<span class="glyphicon glyphicon-log-out"></span> Log Out
						</a></li>
				</ul>
			</div>
		</sec:authorize>
	</div>
	</nav>

	<sec:authorize access="isAuthenticated()">
		<div class="tab-content">
			<div id="welcome" class="tab-pane fade in active">
				<jsp:include page="welcome.jsp" />
			</div>

			<div id="employee-info" class="tab-pane fade">
				<jsp:include page="employee_info.jsp" />
			</div>

			<div id="attendance" class="tab-pane fade">
				<jsp:include page="attendance.jsp" />
			</div>

			<div id="training" class="tab-pane fade">
				<jsp:include page="training.jsp" />
			</div>

			<div id="banking" class="tab-pane fade">
				<jsp:include page="banking.jsp" />
			</div>
		</div>
	</sec:authorize>
</body>
	