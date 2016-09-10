<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css" rel="stylesheet">
<link href="resources/css/bootstrap-formhelpers.min.css" rel="stylesheet" media="screen">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Employee Management</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="resources/js/bootstrap-formhelpers.min.js"></script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

	<nav class="navbar navbar-default nav-fixed-top">
		<div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="#">HR</a>
    		</div>

    		<div class="collapse navbar-collapse">
    		<sec:authorize access="hasRole('ADMIN')">
    			<ul class="nav navbar-nav">
      				<li class="dropdown">
		          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Information <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			           	<li><a href="#">All Employee</a></li>
			            <li role="separator" class="divider"></li>
			            <!-- <li><a href="#" data-toggle="modal" data-target="#employee-modal">Add Employee</a></li> -->
			          </ul>
		        	</li>
      				<li><a href="#" id="nav-admin-attendance">Attendance</a></li>
      				<li><a href="#" id="nav-admin-training">Training</a>
      				<li><a href="#" id="nav-admin-record">Record</a></li>
      				<li><a href="#" data-toggle="modal" data-target="#employee-modal" data-purpose="add">Add Employee</a></li>
      			</ul>
    		</sec:authorize>
    		<sec:authorize access="hasRole('USER')">
    			<ul class="nav navbar-nav">
      				<li><a href="#" id="nav-user-info">Personal Information</a></li>
      				<li><a href="#" id="nav-user-bank">Bank Information</a></li>
      			</ul>
    		</sec:authorize>
    		<sec:authorize access="isAuthenticated()">
    			<form action="<c:url value='/perform_logout' />" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>

	    		<ul class="nav navbar-nav navbar-right">
	    			<li><a href="<c:url value='/' />"><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username" /> </a>
	      			<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Log Out </a></li>
			    </ul>
		    </sec:authorize>
		    </div>
    	</div>
    </nav>