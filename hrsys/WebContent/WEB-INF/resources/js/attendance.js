$(document).ready(function() {
	$.ajax({
        type: "GET",
        url:"rest/employee"
    }).done(function(data) {
        for (var key in data) {
            var option = $("<option></option>").text(data[key].firstname + " " + data[key].lastname);
            option.val(data[key].employeeID);

            $("#employeeID").append(option);
        }
    }).fail(function() {
        alert("Ajax failed to fetch data");
    });
	
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
				    	var comment = $( "<td></td>" ).text(data.comment);
				    	var row  = $( "<tr></tr>" ).append(name, date, inTime, outTime, comment);
				    	$( "#attendance-table-body" ).append(row);
				    }
				}).fail(function() {
				    alert("Ajax failed to fetch data");
				});
	        }
      }
	});
	
	/* $( '*[data-year="2016"]*[data-month="8"] > a' ).eq(8).css("background-color", "red"); */
	
	$("#employeeID").on("change", function() {
		$.ajax({
	        type: "GET",
	        url:"rest/employee/employeeid/" + $( this ).val()
	    }).done(function(data) {
	    	console.log(data);
	        $( "#calendar" ).datepicker( "option", "minDate", data.enrollmentDate);
	    }).fail(function() {
	        alert("Ajax failed to fetch data");
	    });
	});
});
