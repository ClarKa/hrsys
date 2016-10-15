<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value='/resources/js/employee-modal.js' />"></script>

    <div class="modal fade" id="employee-modal" tabindex="-1" role="dialog" aria-labelledby="employee-modal-label">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="employee-modal-label">Add Employee</h4>
          </div>

          <form id="employee-modal-form" action="<c:url value='/rest/employee' />" class="form-horizontal" role="form">
              <div class="modal-body">
                <div class="form-group">
                  <div class="alert alert-danger">
                      <strong>Warning!</strong> Fail to add employee.
                  </div>
                  <div class="alert alert-success">
                      <strong>Success!</strong> Add employee successfully.
                  </div>
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
                        <input class="form-control" type="text" name="birth" id="birth" required readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="ssn"> SSN: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="ssn" placeholder="555-55-5555" maxlength="11" id="ssn">
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
                        <input class="form-control" type="text" name="enrollmentDate" id="enrollmentDate" required readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="position"> Position: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="position" id="position">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phone"> Phone: </label>
                    <div class="col-sm-8">
                        <input class="form-control" type="tel" maxlength="11" name="phone" id="phone">
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