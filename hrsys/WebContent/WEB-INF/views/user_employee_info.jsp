<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script>
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

function loadEmployeeInfoSection(data) {
    $("#request-alert-wrapper").empty();
    $("#request-table-wrapper").empty();
    $("#request-collapse").collapse('hide');

	$.each(data, function(key, value) {
        if (key == "temp" && value != null) {
            renderRequest(value, data);
        }
		$("#myinfo-" + key).text(value);
	});
}

function renderRequest(temp, old) {
    var changes = "";
    $.each(temp, function(key, value) {
        if (value != null && old[key] != value) {
            changes = changes + "<tr><th>" + key.capitalize() + "</th>" + "<td> " + old[key] + " &#8594; " + value + " </td>" + "</tr>";
        }
    });

    var alertStr = "<div class='alert alert-info' role='alert'>"
            	 +   "<a data-toggle='collapse' href='#request-collapse' aria-expanded='false' aria-controls='collapseExample' class='alert-link'> Pending Request</a> for updating personal information"
            	 + "</div>"
    var tableStr = "<table id='request-table' class='table'>"
              	 +     changes
              	 + "</table>"



    var $alert = $.parseHTML(alertStr);
    var $table =  $.parseHTML(tableStr);
    $("#request-alert-wrapper").html($alert);
    $("#request-table-wrapper").html($table);
}

$(document).ready(function() {
	$getUser.then(function(data) {
		loadEmployeeInfoSection(data);
	});

    // submit modal form
    $( "#employee-modal-form" ).submit(function( event ) {
        event.preventDefault();

        var $form = $( this );

        $.ajax({
            type: "POST",
            url: "rest/temp/" + selectedEmployee.employeeID,
            data: $(".changed-input", $form).serialize(),
            beforeSend: function(xhr) {
               xhr.setRequestHeader(header, token);
            }
        }).done(function(data) {
            if (data.error == null) {
                $('#employee-modal').modal('hide');
                renderRequest(data, selectedEmployee);
            } else {
                alert(data.error);
            }
        }).fail(function(data) {
            alert("Ajax failed to fetch data");
        });
    });

    // Cancel request
    $( "#cancel-request-btn").click(function() {
    	$.ajax({
            type: "DELETE",
            url: "rest/temp/" + selectedEmployee.employeeID,
            beforeSend: function(xhr) {
               xhr.setRequestHeader(header, token);
           }
        }).done(function(data) {
            if (data.error == null) {
                $("#request-alert-wrapper").empty();
    			$("#request-table-wrapper").empty();
    			$("#request-collapse").collapse('hide');

                $side = $("#sideUl a.active");
                $("span", $side).remove();
            } else {
                alert(data.error);
            }
        }).fail(function(data) {
            alert("Send request failed.");
        });
    });
});
</script>

<sec:authorize access="hasRole('ADMIN')">
<script>
$(document).ready(function() {
    // Approve request
    $( "#approve-request-btn").click(function() {
    	$.ajax({
            type: "PUT",
            url: "rest/temp/" + selectedEmployee.employeeID,
            beforeSend: function(xhr) {
               xhr.setRequestHeader(header, token);
           }
        }).done(function(data) {
            if (data.error == null) {
                $("#request-alert-wrapper").empty();
    			$("#request-table-wrapper").empty();
    			$("#request-collapse").collapse('hide');

                $side = $("#sideUl a.active");

                //TODO: Update all;
                $("span", $side).remove();
            } else {
                alert(data.error);
            }
        }).fail(function(data) {
            alert("Send request failed.");
        });
    });
});
</script>
</sec:authorize>

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-6">My Information</div>
			<sec:authorize access="hasRole('ROLE')">
			<div class="col-xs-6 panel-heading-icon">
				<a data-toggle="modal" data-target="#employee-modal">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				</a>
			</div>
			</sec:authorize>
		</div>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
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
			</table>
		</div>

		<div id="request-alert-wrapper"></div>
        <div class="collapse" id="request-collapse">
        	<div class="well">
				<div id="request-table-wrapper" classs="table-responsive"></div>
       		 	<button class='btn btn-danger' id='cancel-request-btn'> Cancel Request </button>
       		 	<sec:authorize access="hasRole('ADMIN')">
       		 	<button class='btn btn-success' id='approve-request-btn'> Approve Request </button>
       		 	</sec:authorize>
       		</div>
        </div>
	</div>

</div>