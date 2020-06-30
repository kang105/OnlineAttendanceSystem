<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="ClientTag" prefix="client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Label</title>

</head>
<body>
	<div class="col align-self-start">
		<h1 align="center">
			<img src="<%=path%>/image/logo.jpg"><em><strong>网上考勤系统</strong></em>
		</h1>
		<c:if test="${not empty sessionScope.headerInfo}">
			<h1 align="center">
				<c:out value="${sessionScope.headerInfo}" />
			</h1>
		</c:if>
		<br>
	</div>
</body>
</html>