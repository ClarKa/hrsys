function toggleFailAlert() {
    $(".bg-danger").show();
    $(".bg-success").hide();
}

function toggleSuccessAlert() {
    $(".bg-danger").hide();
    $(".bg-success").show()
}

$(document).ready(function() {
	var purpose;
	var url;
    var employeeId;

    // employee modal setup
    $(".bg-danger").hide();
    $(".bg-success").hide();

    $("#employee-modal").on("show.bs.modal", function(e) {
        var button = $(e.relatedTarget);
        purpose = button.data('purpose');
        url = "rest/employee";

    	// populate department list
        $.ajax({
            type: "GET",
            url:"rest/department"
        }).done(function(data) {
            for (var key in data) {
                var option = $("<option></option>").text(data[key].departmentName);
                option.val(data[key].departmentID);

                $("#departmentID").append(option);
            }
        }).fail(function() {
            alert("Ajax failed to fetch data");
        });

        // populate employee data if editing
        if (purpose == "edit") {
        	employeeId = button.data('employeeid');
        	url = url + "/employeeid/" + employeeId;

        	$.ajax({
        		type: "GET",
        		url: url,
        	}).done(function(data) {
        		if(data.employeeid == null || data.emloyeeid == '') {
        			alert("Employee entry no long exists");
        			$("#employee-datatable").DataTable().ajax.reload();
        		}
        		
        		$.each(data, function(key, value){
                    $("#"+key).val(value);
                });
        	}).fail(function(data) {
        		alert("Employee entry no longer exists.");
        	});
        }

    });

    // clear modal after closing
    $("#employee-modal").on("hidden.bs.modal", function(e){
        $(".bg-danger").hide();
        $(".bg-success").hide();

        $("#employee-modal").find("form")[0].reset();
    });

    // submit add employee action
    $( "#employee-modal-form" ).submit(function( event ) {
      event.preventDefault();

      var $form = $( this ),
      	term = $form.find( "input[name='Save']" ).val();

      // include CSRF token.
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");

      $.ajax({
           type: "POST",
           url: url,
           data: $form.serialize(),
           beforeSend: function(xhr) {
        	   xhr.setRequestHeader(header, token);
           }
      }).done(function(data) {
           var error = data.error;
           if (error == null) {
               toggleSuccessAlert();
               $("#employee-datatable").DataTable().ajax.reload();
               $('#employee-modal').modal('hide');
           } else {
               toggleFailAlert();
           }
      }).fail(function(data) {
          toggleFailAlert();
          console.log(data);
      });
    });

    // datepicker setup
    $( "#birth" ).datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "+0D",
      });
    $( "#birth" ).datepicker("option", "dateFormat", "yy-mm-dd" );

    $( "#enrollmentDate" ).datepicker({
        changeMonth: true,
        changeYear: true
      });
    $( "#enrollmentDate" ).datepicker("option", "dateFormat", "yy-mm-dd" );

    // format ssn input
    $('#ssn').keyup(function() {
        var val = this.value.replace(/\D/g, '');
        var newVal = '';
        if(val.length > 3) {
           this.value = val;
        }
        if((val.length > 3) && (val.length < 6)) {
           newVal += val.substr(0, 3) + '-';
           val = val.substr(3);
        }
        if (val.length > 5) {
           newVal += val.substr(0, 3) + '-';
           newVal += val.substr(3, 2) + '-';
           val = val.substr(5);
         }
         newVal += val;
         this.value = newVal;
      });
});

