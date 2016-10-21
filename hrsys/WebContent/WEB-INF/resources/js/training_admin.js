$(document).ready(function() {
    // Approve all training record.
	$("#training-approve-all-button").click(function() {
		 $.ajax({
             type: "POST",
             url: trainingUrl,
             data: {"employeeId": trSelectedEmployee.employeeID},
             beforeSend: function(xhr) {
                 xhr.setRequestHeader(header, token);
             }
        }).done(function(data) {
            alert(data.length + " records have been approved.");
        	initializeTrainingCalendarForUser(trSelectedEmployee);
        }).fail(function(data) {
            alert("approve all training record for " + trSelectedEmployee.firstname + " " + trSelectedEmployee.lastname + " failed");
        });
    });

    // Approve one record
    var approveButton = $("<button type='button' class='btn btn-success pull-left' data-dismiss='modal' id='training-approve-one-button'> Approve </button>");
    $("#training-modal .modal-footer").append(approveButton);

    $("#training-approve-one-button").click(function() {
         $.ajax({
             type: "POST",
             url: trainingUrl,
             data: {"employeeId": trSelectedEmployee.employeeID, "date": $("#training-modal input[name='date']").val()},
             beforeSend: function(xhr) {
                 xhr.setRequestHeader(header, token);
             }
        }).done(function(data) {
        	alert(data.length + " records have been approved.");
            initializeTrainingCalendarForUser(trSelectedEmployee);
        }).fail(function(data) {
            alert("approve all training record for " + trSelectedEmployee.firstname + " " + trSelectedEmployee.lastname + " failed");
        });
    });
});

// employee select list setup
function initializeTrainingEmployeeSelectList(data) {
    for (var key in data) {
        var option;
        if (data[key].employeeID == userEmployeeId) {
            option = $("<option selected></option>").text(data[key].firstname + " " + data[key].lastname + " (me) ");
            initializeTrainingCalendarForUser(data[key]);
        } else {
            option = $("<option></option>").text(data[key].firstname + " " + data[key].lastname);
        }

        option.val(data[key].employeeID);
        $("#training-employee-list").append(option);
    }
}