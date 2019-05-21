<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- Bootstrap css - 1/2 -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}"/>
<!--link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/-->
<!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/-->

<!-- jQuery library -->
<spring:url value="/resources/js/jquery-3.3.1.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<!--script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script-->

<!-- Bootstrap js - 2/2 -->
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<script src="${bootstrapJs}"></script>
<!--script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script-->
<!--script src="/resources/js/bootstrap.min.js"></script-->


<style>
.loginBtn {
	width : 32px;
}
</style>