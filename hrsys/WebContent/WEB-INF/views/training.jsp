<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.event-tooltip-content:not (:last-child ) {
	border-bottom: 1px solid #ddd;
	padding-bottom: 5px;
	margin-bottom: 5px;
}

.event-tooltip-content .event-approved {
	font-size: 18px;
}

.event-tooltip-content .event-hour {
	font-size: 12px;
}

#training-calendar {
	overflow-y: hidden !important;
}

#training-records-collapse p {
    padding: 15px;
}
</style>
<link href="<c:url value='/resources/css/bootstrap-year-calendar.min.css' />" rel="stylesheet">
<script src="<c:url value='/resources/js/bootstrap-year-calendar.min.js' />"></script>
<script src="<c:url value='/resources/js/training.js' />"></script>

<sec:authorize access="hasRole('ADMIN')">
<!-- Admin Action -->
<script src="<c:url value='/resources/js/training_admin.js' />"></script>
<!-- End Admin Action -->
</sec:authorize>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">My Training Time</div>
		<div class="panel-body">
			<p>
				<select class="form-control" name="employeeID" id="training-employee-list">
					<option selected disabled>Select an employee</option>
				</select>
			</p>
			<p>
				<a class="btn btn-default" role="button" data-toggle="collapse" href="#training-records-collapse" aria-expanded="false"
					aria-controls="training-records-collapse"> Show Statistics </a>
				<sec:authorize access="hasRole('ADMIN')">
				<button class="btn btn-success" id="training-approve-all-button">Approve All Record</button>
				</sec:authorize>
			</p>

			<div class="collapse" id="training-records-collapse">
				<div class="well">

				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">Calendar</div>
		<div class="panel-body">
            <p>
                <a class="btn btn-default" role="button" data-toggle="collapse" href="#training-calendar-filter-collapse" aria-expanded="false"
                    aria-controls="training-calendar-filter-collapse"> Records Filters </a>
            <p>
			<div class="collapse" id="training-calendar-filter-collapse">
                <div class="well">
    				<div class="btn-group">
                        <button type="button" class="btn btn-info" id="none-filter" data-approved="">Show All Record</button>
    					<button type="button" class="btn btn-success" id="approved-filter" data-approved="true">Show Approved Record</button>
    					<button type="button" class="btn btn-primary" id="unapproved-filter" data-approved="false">Show Unapproved Record</button>
    				</div>
                </div>
			</div>
			<br>
			<div id="training-calendar" class="calendar"></div>
		</div>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="training-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="label"></span>
					</h4>
				</div>

				<form class="form-horizontal" role="form" id="training-modal-form">
					<div class="modal-body">
						<div class="form-group">
							<label class="control-label col-sm-4" for="date"> Date: </label>
							<div class="col-sm-6">
								<input type="text" name="date" class="form-control" required readonly />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="hour"> Hour: </label>
							<div class="col-sm-6">
								<input type="text" name="hour" class="form-control" required />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>