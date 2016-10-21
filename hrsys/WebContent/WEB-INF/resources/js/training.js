var trSelectedEmployee;
var trFilterDays;

$(document).ready(function() {
    $("#training-employee-list").on("change", function() {
        $.ajax({
            type: "GET",
            url: employeeInfoUrl + getOneEmployeeUrl + $( this ).val()
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
        mouseOnDay: function(e) {
        	content = "";
        	if (e.events.length > 0) {
	        	content += '<div class="event-tooltip-content">'
	                + '<div class="event-approved" style="color:' + e.events[0].color + '">' + (e.events[0].approved ? "Approved" : "Unapproved" ) + '</div>'
	                + '<div class="event-hour">' + e.events[0].hour + ' hours </div>'
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
            if (trFilterDays && e.events[0] == null) {
                return;
            }
        	var event = e.events[0];
            $("#training-modal input[name='date']").datepicker( "setDate", e.date );
            $("#training-modal input[name='date']").datepicker( "option", {minDate: e.date, maxDate: e.date} );
            $("#training-modal input[name='hour']").val( event ? event.hour : '');


           	if (event) {
               $("#training-modal .label").text(event.approved ? "Approved" : "Unapproved");
               $("#training-modal .label").attr("class", event.approved ? "label label-success" : "label label-primary" );
               $("#training-modal input[name='hour']").prop("readonly", event.approved ? true : false);
               $("#training-modal input[type='submit']").prop("disabled", event.approved ? true : false);
               $("#training-approve-one-button").prop("disabled", event.approved ? true : false);
            } else {
               $("#training-modal .label").text("No record");
               $("#training-modal .label").attr("class", "label label-warning" );
               $("#training-modal input[name='hour']").prop("readonly", false);
               $("#training-modal input[type='submit']").prop("disabled", false);
               $("#training-approve-one-button").prop("disabled", true);
            }

            if (trSelectedEmployee.employeeID != userEmployeeId) {
            	$("#training-modal input[name='hour']").prop("readonly", true);
                $("#training-modal input[type='submit']").prop("disabled", true);
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

        $.ajax({
             type: "POST",
             url: trainingUrl + getOneEmployeeUrl + trSelectedEmployee.employeeID + "/" + $("#training-modal input[name='date']").val(),
             data: $form.serialize(),
             beforeSend: function(xhr) {
                 xhr.setRequestHeader(header, token);
             }
        }).done(function(data) {
             if (data.error == null) {
                 $('#training-modal').modal('hide');
                 initializeTrainingCalendarForUser(trSelectedEmployee);
             } else {
                 alert(data.error);
             }
        }).fail(function(data) {
            alert("Add training time record failed.");
        });
      });

    // handle calendar filter
    $("#training-calendar-filter-collapse .btn-group").click(function(e) {
        var approved = $(e.target).data("approved");

        if (approved === "") {
            trFilterDays = false;
        } else {
            trFilterDays = true;
        }

        $('#training-calendar').data('calendar').setDataSource("[]");
        $.ajax({
                type : "GET",
                url : trainingUrl + getOneEmployeeUrl + trSelectedEmployee.employeeID,
                data:  {"approved": approved}
            }).done(function(data) {
                addTrainingRecord(data);
            }).fail(function(data) {
                alert("Get record failed.");
            });
    });

    $("#training-records-collapse").on("show.bs.collapse", function() {
        updateTrainingStat();
    });
});

function updateTrainingStat() {
    $.ajax({
        type: "GET",
        url: trainingUrl + getOneEmployeeUrl + trSelectedEmployee.employeeID
    }).done(function(data) {
        var stat = {totalDays:0, totalHours:0, approvedDays:0, approvedHours:0, unapprovedDays:0, unapprovedHours:0};

        $.each(data, function(key, value) {
            stat.totalDays++;
            stat.totalHours = stat.totalHours + value.hour;

            if (value.approved) {
                stat.approvedDays++;
                stat.approvedHours = stat.approvedHours + value.hour;
            } else {
                stat.unapprovedDays++;
                stat.unapprovedHours = stat.unapprovedHours + value.hour;
            }
        });

        $("#training-records-collapse .well").empty();
        var total = $("<p class='bg-info'></p>").text(stat.totalDays + " days and " + stat.totalHours + " hours have been recorded.");
        var approved = $("<p class='bg-success'></p>").text(stat.approvedDays + " days and " + stat.approvedHours + " hours have been approved.");
        var unapproved = $("<p class='bg-primary'></p>").text(stat.unapprovedDays + " days and " + stat.unapprovedHours + " hours still wait to be approved.");
        $("#training-records-collapse .well").append(total, approved, unapproved);

    }).fail(function(data) {
        alert("get record for " + trSelectedEmployee.firstname + " " + trSelectedEmployee.lastname + " failed");
    });
}

function initializeTrainingCalendarForUser(employee) {
	trSelectedEmployee = employee;
    var enrollmentDate = new Date(employee.enrollmentDate);

    updateTrainingStat();

    $("#training-calendar").data("calendar").setMinDate(enrollmentDate);
    $('#training-calendar').data('calendar').setDataSource("[]");

    $.ajax({
            type : "GET",
            url : trainingUrl + getOneEmployeeUrl + employee.employeeID,
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