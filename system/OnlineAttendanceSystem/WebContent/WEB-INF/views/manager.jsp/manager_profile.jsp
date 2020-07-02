<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上考勤系统</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../Header_ins.jsp"%>
	</div>
	<center>
		<h1>注册成功！欢迎使用网上考勤系统</h1>
		<h2>管理员信息：</h2>

		<h3>
			用户名:
			<c:out value="${manager.username}" />
			<br />
			<br />
		</h3>

		<a href="<c:url value="/home" />">返回首页</a>
	</center>
	<div>
		<br /><jsp:include page="../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>
