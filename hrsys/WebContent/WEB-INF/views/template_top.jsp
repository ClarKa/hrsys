<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/jquery/jquery-ui-1.12.1.custom/jquery-ui.css" rel="stylesheet">
<link href="resources/css/bootstrap-formhelpers.min.css" rel="stylesheet" media="screen">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Employee Management</title>
</head>
<body>
<script src="resources/jquery/jquery-3.1.1.min.js"></script>
<script src="resources/jquery/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-formhelpers.min.js"></script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

	<nav class="navbar navbar-default nav-fixed-top">
		<div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="<c:url value='/' />">HR</a>
    		</div>

    		<div class="collapse navbar-collapse">
    		<sec:authorize access="hasRole('ADMIN')">
    			<ul class="nav navbar-nav">
      				<li class="dropdown">
		          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Information <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			           	<li><a href="<c:url value='/employee_info' />">All Employee</a></li>
			            <li role="separator" class="divider"></li>
			            <!-- <li><a href="#" data-toggle="modal" data-target="#employee-modal">Add Employee</a></li> -->
			          </ul>
		        	</li>
      				<li><a href="<c:url value='/attendance' />" id="nav-admin-attendance">Attendance</a></li>
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