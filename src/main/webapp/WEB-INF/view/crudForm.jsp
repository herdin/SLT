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
	$('#list').click(function(){
		console.log('list');
		$.ajax({
			url: location.pathname + 'list',
			data: {
				id: $('#id').val()
				,de: $('#de').val()
			},
			type: "GET",
			dataType: "json",
			success: function(data) {
				console.log('success');
				console.dir(data);
			}
		});
	});
});
	</script>
</head>
<body>
<div class="input-group mb-3">
  <input type="text" id="id" class="form-control" placeholder="ID" aria-label="ID" aria-describedby="button-addon2">
  <input type="text" id="de" class="form-control" placeholder="DESCRIPTION" aria-label="DESCRIPTION" aria-describedby="button-addon2">
  <div class="input-group-append">
    <button id="list" class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
  </div>
</div>
<jsp:include page="/WEB-INF/common/commonLast.jsp"/>
</body>
</html>
