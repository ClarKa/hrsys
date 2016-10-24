<jsp:include page="dashboard.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="container">
        <div style="width:300px; margin:10px auto">
            <jsp:include page="errors.jsp" />
            <form name="loginForm" method="post" action="<c:url value='/perform_login' />" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="username"> Username: </label>
                    <div class="col-sm-9">
                    	<input class="form-control" type="text" name="username" id="username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="password"> Password: </label>
                    <div class="col-sm-9">
                    	<input class="form-control" type="password" name="password" id="password"/>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-sm-offset-3 col-sm-9">
                    	<input class="btn btn-default" type="submit" name="submit" value="Login" />
                    </div>
                </div>
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </div>
    </div>
</body>
</html>