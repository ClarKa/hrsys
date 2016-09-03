<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />
<script>
$(document).ready(function() {
 	$(".bg-danger").hide();
    $(".bg-success").hide()

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
    		   $(".bg-danger").hide();
    		   $(".bg-success").show();
    	   } else {
    		   $(".bg-danger").show();
    		   $(".bg-success").hide()
    	   }
      });
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
		          <p class="bg-success text-center"> Add employee successfully. </p>
		          <p class="bg-danger text-center"> Add employee failed. </p>
		        </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="firstname"> Firstname: </label>
                    <div class="col-sm-10">
                        <input type="text" name="firstname" class="form-control" id="firstname"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="lastname"> Lastname: </label>
                    <div class="col-sm-10">
                    	<input type="text" name="lastname" class="form-control"  id="lastname"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email"> Email: </label>
                    <div class="col-sm-10">
                    	<input type="email" name="email"  class="form-control" id="email"/>
                    </div>
                </div>
                <div class="form-group">
                	<label class="control-label col-sm-2" for="departmentID"> Department: </label>
                	<div class="col-sm-10">
	                	<select class="form-control" name="departmentID" id="departmentID">
						</select>
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
