<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>\

<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('USER')" var="isUser" />
<sec:authentication property="principal.employeeID" var="employeeId"/>

<style>
.approved-training {
	box-shadow: rgb(156, 183, 3) 0px -4px 0px 0px inset;
}

.event-tooltip-content:not (:last-child ) {
	border-bottom: 1px solid #ddd;
	padding-bottom: 5px;
	margin-bottom: 5px;
}

.event-tooltip-content .event-title {
	font-size: 18px;
}

.event-tooltip-content .event-hour {
	font-size: 12px;
}
</style>
<link href="<c:url value='/resources/css/bootstrap-year-calendar.min.css' />" rel="stylesheet">
<script src="<c:url value='/resources/js/bootstrap-year-calendar.min.js' />"></script>
<script>
var selectedEmployee;

$(document).ready(function() {
	// employee select list setup
    if ("${isAdmin}" == "true") {
        $.ajax({
            type: "GET",
            url: "rest/employee"
        }).done(function(data) {
            for (var key in data) {
                var option;
                if (data[key].employeeID == "${employeeId}") {
                    option = $("<option selected></option>").text(data[key].firstname + " " + data[key].lastname + " (me) ");
                    initializeTrainingCalendarForUser(data[key]);
                } else {
                    option = $("<option></option>").text(data[key].firstname + " " + data[key].lastname);
                }

                option.val(data[key].employeeID);
                $("#training-employee-list").append(option);
            }
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });
    } else {
        $.ajax({
            type: "GET",
            url: "rest/employee/employeeid/" + "${employeeId}"
        }).done(function(data) {
            var option = $("<option selected></option>").text(data.firstname + " " + data.lastname + " (me) ");
            option.val(data.employeeID);
            $("#training-employee-list").append(option);

            initializeTrainingCalendarForUser(data);
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });
    }

    $("#training-employee-list").on("change", function() {
        $.ajax({
            type: "GET",
            url: "rest/employee/employeeid/" + $( this ).val()
        }).done(function(data) {
            initializeTrainingCalendarForUser(data);
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });
    });
    
    //training modal setup
    $("#training-modal input[name='date']").datepicker({dateFormat: "yy-mm-dd"});
    
    // calendar setup
    $("#training-calendar").calendar({
        enableContextMenu: true,
        mouseOnDay: function(e) {
        	content = "";
        	if (e.events.length > 0) {
	        	content += '<div class="event-tooltip-content">'
	                + '<div class="event-approved" style="color:' + e.events[0].color + '">' + (e.events[0].approved ? "Approved" : "Unapproved" ) + '</div>'
	                + '<div class="event-hour" style="color:' + e.events[0].color + '">' + e.events[0].hour + ' hours </div>'
	                + '</div>';

	        	$(e.element).popover({
	                trigger: 'manual',
	                container: 'body',
	                html:true,
	                content: content
	            });

	            $(e.element).popover('show');
        	}
        },
        mouseOutDay: function(e) {
            if(e.events.length > 0) {
                $(e.element).popover('hide');
            }
        },
        clickDay: function(e) {
        	var event = e.events[0];
            $("#training-modal input[name='date']").datepicker( "setDate", e.date );
            $("#training-modal input[name='date']").datepicker( "option", {minDate: e.date, maxDate: e.date} );
            $("#training-modal input[name='hour']").val( event ? event.hour : '');
            
            if (event) {
                $("#training-modal .label").text(event.approved ? "Approved" : "Unapproved");
                $("#training-modal .label").attr("class", event.approved ? "label label-success" : "label label-primary" );
                $("#training-modal input[name='hour']").prop("readonly", event.approved ? true : false);
            } else {
            	$("#training-modal .label").text("No record");
                $("#training-modal .label").attr("class", "label label-warning" );
                $("#training-modal input[name='hour']").prop("readonly", false);
            }
            
            $("#training-modal").modal();
        },
        maxDate: new Date(),
        dataSource: []
        });

    // handle add training record form
    $( "#training-modal-form" ).submit(function( event ) {
        event.preventDefault();

        var $form = $( this );

        // include CSRF token.
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
             type: "POST",
             url: "rest/training/employeeid/" + selectedEmployee.employeeID,
             data: $form.serialize(),
             beforeSend: function(xhr) {
                 xhr.setRequestHeader(header, token);
             }
        }).done(function(data) {
             if (data.error == null) {
                 $('#training-modal').modal('hide');
                 addTrainingRecord([data]);
             } else {
                 alert(data.error);
             }
        }).fail(function(data) {
            alert(data);
        });
      });
});

function initializeTrainingCalendarForUser(employee) {
	selectedEmployee = employee;
    var enrollmentDate = new Date(employee.enrollmentDate);

    $("#training-calendar").data("calendar").setMinDate(enrollmentDate);
    $('#training-calendar').data('calendar').setDataSource("[]");

    $.ajax({
            type : "GET",
            url : "rest/training/employeeid/" + employee.employeeID,
        }).done(function(data) {
            addTrainingRecord(data);
        }).fail(function(data) {
            alert("Employee entry no longer exists.");
        });
}

function addTrainingRecord(data) {
    var dataSource = $("#training-calendar").data("calendar").getDataSource();
    var approvedColor = "#9CB703";
    var unapprovedColor = "#2C8FC9";

    if (data == null || data.length == 0) {
        alert("no record found!");
    }

    $.each(data, function(key, value) {
        var event = {
                approved: value.approved,
                hour: value.hour,
                color: (value.approved ? approvedColor : unapprovedColor),
                startDate: new Date(value.date + " 00:00:00"),
                endDate: new Date(value.date + " 00:00:00")
            }
        dataSource.push(event);
    });

    $('#training-calendar').data('calendar').setDataSource(dataSource);
}
</script>
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">My Training Time</div>
		<div class="panel-body">
			<select class="form-control" name="employeeID" id="training-employee-list">
				<option selected disabled>Select an employee</option>
			</select>
		</div>
		<hr>
		<div id="training-calendar"></div>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="training-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"> <span class="label"></span> </h4>
				</div>

				<form class="form-horizontal" role="form" id="training-modal-form">
					<div class="modal-body">
						<div class="form-group">
							<label class="control-label col-sm-4" for="date"> Date: </label>
							<div class="col-sm-6">
								<input type="text" name="date" class="form-control" required readonly/>
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


