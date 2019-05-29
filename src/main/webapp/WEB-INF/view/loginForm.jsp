<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>OAuth2.0 TEST PAGE</title>
	<jsp:include page="/WEB-INF/common/commonFirst.jsp"/>
	<script type="text/javascript">
$(document).ready(function(){
	pageInit();
	function pageInit() {
		$('#loginError').hide();
		var loginError = "${loginError}";
		if(loginError != null && loginError != undefined && loginError != "") {
			$('#loginError').html(loginError);
			$('#loginError').show();
		}
	}
});
	</script>
</head>
<body>
	<ul class="list-group-flush">
		<li class="list-group-item">
			<div id="loginError" class="alert alert-danger" role="alert"></div>
		</li>
		<li class="list-group-item">
			<!--c:url var="loginProcess" value="j_spring_security_check"/-->
			<!-- j_spring_security_check -->
			<form action="login" method="post" accept-charset="UTF-8">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="form-group">
					<label for="id">ID</label>
					<input type="text" id="id" name="id" class="form-control" placeholder="Enter ID">
					<small id="idHelp" class="form-text text-muted">We'll never share your ID with anyone else.</small>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" id="password" name="password" class="form-control" placeholder="Password">
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1">
					<label class="form-check-label" for="exampleCheck1">Check me out</label>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</li>
	</ul>

</body>
</html>
