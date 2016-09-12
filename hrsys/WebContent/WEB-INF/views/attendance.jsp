<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />

<script type="text/javascript">
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

</script>

<div class="container">

    <sec:authorize access="hasRole('ADMIN')">
       <label class="control-label" for="employeeID"> Select an Employee: </label>
       <div class="">
            <select class="form-control" name="employeeID" id="employeeID">
            </select>
       </div>
    </sec:authorize>

</div>
