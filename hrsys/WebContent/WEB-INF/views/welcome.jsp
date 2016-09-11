<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template_top.jsp" />
<h1 class="text-center"> Welcome! </h1>
<h3 class="text-center"> You are logged in as <sec:authentication property="principal.authorities"/> </h3>