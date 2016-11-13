<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value='/resources/js/employee-modal.js' />"></script>
<div class="container">
    <jsp:include page="user_employee_info.jsp" />
    <jsp:include page="employee_modal.jsp" />
</div>
