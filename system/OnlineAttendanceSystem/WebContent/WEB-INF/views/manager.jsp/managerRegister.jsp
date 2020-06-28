<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网上管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../Header_ins.jsp"%>
	</div>
	<center>
		<h1>管理员注册</h1>
		<c:if test="${not empty sessionScope.error_1}">
			<h2 align="center">
				<c:out value="${sessionScope.error_1}" />
			</h2>
			</c:if>
		<sf:form method="POST" commandName="manager">
			<sf:errors path="*" cssClass="error" />
			<br />
			<br />
    
          用户名:<sf:input path="username" />
			<sf:errors path="username" cssClass="error" />
			<br />
			<br />
	密码:<sf:password path="password" />
			<sf:errors path="password" cssClass="error" />
			<br />
			<br />

			<input type="submit" value="注册" />
		</sf:form>

		<a href="<c:url value="/home" />">返回首页</a>
	</center>
	<div>
		<br /><jsp:include page="../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>
