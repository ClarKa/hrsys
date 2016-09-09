<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />
<script>
function toggleFailAlert() {
	$(".bg-danger").show();
    $(".bg-success").hide();
}

function toggleSuccessAlert() {
	$(".bg-danger").hide();
    $(".bg-success").show()
}

$(document).ready(function() {

	// add employee modal setup
    $(".bg-danger").hide();
    $(".bg-success").hide();

	$("#add_employee_modal_trigger").click(function() {
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
	});

    $( "#add-employee-form" ).submit(function( event ) {
      event.preventDefault();

      var $form = $( this ),
        term = $form.find( "input[name='Save']" ).val(),
        url = $form.attr( "action" );

      $.ajax({
    	   type: "POST",
    	   url: url,
    	   data: $form.serialize()
      }).done(function(data) {
    	   var error = data.error;
    	   if (error == null) {
    		   toggleSuccessAlert();
    		   $("#employee-datatable").DataTable().ajax.reload();
    	   } else {
    		   toggleFailAlert();
    	   }
    	   
    	   console.log(data);
      }).fail(function(data) {
    	  toggleFailAlert();
      });
    });

    // datepicker setup
    $( "#birth" ).datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: "+0D"
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
</script>
<div class="container">

	<sec:authorize access="hasRole('ADMIN')">
    	<jsp:include page="employee_info.jsp" />
	</sec:authorize>


	<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="addEmployeeModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="addEmployeeModalLabel">Add Employee</h4>
	      </div>

	      <form id="add-employee-form" action="<c:url value='/rest/employee' />" class="form-horizontal" role="form">

		      <div class="modal-body">
		        <div class="form-group">
		          <p class="bg-success text-center" style="padding: 5px"> Add employee successfully. </p>
		          <p class="bg-danger text-center" style="padding: 5px"> Add employee failed. Please check your input. </p>
		        </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="firstname"> Name: </label>
                    <div class="col-sm-4">
                        <input type="text" name="firstname" class="form-control" id="firstname" placeholder="Firstname" required/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" name="lastname" class="form-control"  id="lastname" placeholder="Lastname" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email"> Email: </label>
                    <div class="col-sm-8">
                    	<input type="email" name="email"  class="form-control" id="email" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="departmentID"> Department: </label>
                    <div class="col-sm-8">
                        <select class="form-control" name="departmentID" id="departmentID">
                        </select>
                    </div>
                </div>

                <div class="form-group">
                	<label class="control-label col-sm-2" for="gender"> Gender: </label>
                	<div class="col-sm-8">
	                	<select class="form-control" name="gender" id="gender">
	                       <option value="Male"> Male </option>
	                       <option value="Female"> Female </option>
						</select>
					</div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="birth"> Birthday: </label>
                    <div class="col-sm-8">
                        <input class="form-control" name="birth" id="birth" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="ssn"> SSN: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="ssn" placeholder="555-55-5555" maxlength="11" id="ssn" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="marriage"> Marrital Status: </label>
                    <div class="col-sm-8">
                        <select class="form-control" name="marriage" id="marriage">
                           <option value="Married"> Married </option>
                           <option value="Single"> Single </option>
                           <option value="Divorced"> Divorced </option>
                           <option value="Widowed"> Widowed </option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nationality"> Nationality: </label>
                    <div class="col-sm-8">
                        <select class="form-control bfh-countries" data-country="US" name="nationality" id="nationality"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="eductation"> Education: </label>
                    <div class="col-sm-8">
                        <select class="form-control" name="education" id="education">
                           <option value="Doctoral"> Doctoral </option>
                           <option value="Master"> Master </option>
                           <option value="Bachelor"> Bachelor </option>
                           <option value="High School"> High School </option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="enrollmentDate"> Enrollment Date: </label>
                    <div class="col-sm-8">
                        <input class="form-control" name="enrollmentDate" id="enrollmentDate" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="position"> Position: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="position" id="position" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phone"> Phone: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="tel" name="phone" id="phone" required>
                    </div>
                </div>
                
		      </div>

		      <div class="modal-footer">
	            <input type="submit" value="Save" class="btn btn-primary" />
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		      </div>
	      </form>
	    </div>
	  </div>
	</div>

</div>
