<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
		<jsp:include page="/WEB-INF/common/common.jsp"/>
	</head> 
	<body>
		LOGIN OK
		<c:url var="messageUrl" value="/showMessage.html" />
		<a href="${messageUrl}">Click to enter</a>
		<sec:authorize access="isAnonymous()">
		ANONYMOUS!!
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		AUTHENTICATED!!
		</sec:authorize>
	</body>
</html>
