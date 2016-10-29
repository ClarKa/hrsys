<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
$(document).ready(function() {
    // initialize calendar
    $( "#calendar" ).datepicker({
      showButtonPanel: true,
      changeYear: true,
      changeMonth: true,
      maxDate: "+0D",
      dateFormat: "yy-mm-dd",
      onSelect: function(dateText, inst) {
            if ($( "#employeeID" ).val() != null) {
                $.ajax({
                    type: "GET",
                    url:"rest/attendance/employeeid/" + $( "#employeeID" ).val() + "/" + dateText
                }).done(function(data) {
                    $( "#attendance-table-body" ).empty();
                    if (data.error != null ) {
                        var error = $( "<td colspan='5'></td>" ).text(data.error);
                        var row = $( "<tr></tr>" ).append(error);
                        $( "#attendance-table-body" ).append(row);
                    } else {
                        var name = $( "<td></td>" ).text(data.name);
                        var date = $( "<td></td>" ).text(data.date);
                        var inTime = $( "<td></td>" ).text(data.inTime);
                        var outTime = $( "<td></td>" ).text(data.outTime);
                        var status = $( "<td></td>" ).text(data.status);
                        var comment = $( "<td></td>" ).text(data.comment);
                        var row  = $( "<tr></tr>" ).append(name, date, inTime, outTime, status, comment);
                        $( "#attendance-table-body" ).append(row);
                    }
                }).fail(function() {
                    alert("Ajax failed to fetch data");
                });
            }
      }
    });

    $("#employeeID").on("change", function() {
        $.ajax({
            type: "GET",
            url: "rest/employee/employeeid/" + $( this ).val()
        }).done(function(data) {
            initializeAttendanceCalendarForUser(data);
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });
    });


    // Record attendance
    $("#punch").click(function() {
        $.ajax({
            type: "POST",
            url: "rest/attendance/employeeid/" + userEmployeeId,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function(data) {
        	if (data.error == null) {

        	} else  {

        	}
        	console.log(data);
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });
    })
});

function initializeAttendanceEmployeeSelectList(data) {
    for (var key in data) {
        var option;
        if (data[key].employeeID == userEmployeeId) {
            option = $("<option selected></option>").text(data[key].firstname + " " + data[key].lastname + " (me) ");
            initializeAttendanceCalendarForUser(data[key]);
        } else {
            option = $("<option></option>").text(data[key].firstname + " " + data[key].lastname);
        }

        option.val(data[key].employeeID);
        $("#employeeID").append(option);
    }
}

function initializeAttendanceCalendarForUser(data) {
	$( "#calendar" ).datepicker( "option", "minDate", data.enrollmentDate);
}

</script>

<div class="container">
	<div class="alert alert-warning">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Tip!</strong> Select an employee and choose a date to see the attendance record.
	</div>

	<div class="row">
		<div class="col-sm-4">
		    <select class="form-control" name="employeeID" id="employeeID">
	            <option selected disabled>Select an employee</option>
	        </select>
	        <br>
	    </div>
	    <div class="col-sm-4"></div>
        <div class="col-sm-4 text-right">
            <button class="btn btn-default" id="punch">Punch</button>
        </div>
    </div>

    <div class="row">
    	<div class="col-sm-3">
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
    					<th>Status</th>
    					<th>Comment</th>
    				</tr>
    			</thead>
    			<tbody id="attendance-table-body">

    			</tbody>
    		</table>
    	</div>
    </div>
</div>