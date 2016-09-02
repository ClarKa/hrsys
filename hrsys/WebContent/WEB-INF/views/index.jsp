<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />
<script>
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
	      
	      <form method="post" action="<c:url value='/rest/employee' />" class="form-horizontal" role="form">
		      <div class="modal-body">
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
                    <label class="control-label col-sm-2" for="empEmail"> Email: </label>
                    <div class="col-sm-10">
                    	<input type="email" name="empEmail"  class="form-control" id="empEmail"/>
                    </div>
                </div> 
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <input type="submit" value="Save" class="btn btn-primary" />
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		      </div>
	      </form>
	    </div>
	  </div>
	</div>

</div>
