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
$(document).ready(function() {
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


    $("#training-calendar").calendar({
        enableRangeSelection: true,
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
            console.log(e.date);
            var month = $(".month")[8];
            console.log($(month).find(".day:not(.old)")[0]);
        },
        maxDate: new Date(),
        dataSource: []
        });
});

function initializeTrainingCalendarForUser(employee) {
	console.log(employee);
	
    var enrollmentDate = new Date(employee.enrollmentDate);
    var approvedColor = "#9CB703";
    var unapprovedColor = "#2C8FC9";

    $("#training-calendar").data("calendar").setMinDate(enrollmentDate);
    $('#training-calendar').data('calendar').setDataSource("[]");

    $.ajax({
            type : "GET",
            url : "rest/training/employeeid/" + employee.employeeID,
        }).done(function(data) {
            var dataSource = $("#training-calendar").data("calendar").getDataSource();

            if (data == null || data.length == 0) {
                alert("no record found!");
            }

            $.each(data, function(key, value) {
                var event = {
                        name: value.name,
                        approved: value.approved,
                        hour: value.hour,
                        color: (value.approved ? approvedColor : unapprovedColor),
                        startDate: new Date(value.date + " 00:00:00"),
                        endDate: new Date(value.date + " 00:00:00")
                    }
                dataSource.push(event);
            });

            $('#training-calendar').data('calendar').setDataSource(dataSource);
        }).fail(function(data) {
            alert("Employee entry no longer exists.");
        });
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

</div>


