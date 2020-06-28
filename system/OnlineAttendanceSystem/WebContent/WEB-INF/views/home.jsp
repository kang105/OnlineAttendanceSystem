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
		<%@ include file="Header_ins.jsp"%>
	</div>
	<center>
		<h1>欢迎使用网上考勤系统</h1>
	</center>
	<ul class="nav nav-tabs justify-content-center">
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home/managerLogin" />">管理员登录</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home/managerRegister" />">管理员注册</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home/memberLogin" />">员工登录</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home/memberRegister" />">员工注册</a></li>
	</ul>
	<center>
		<img src="<%=path%>/image/index.jpg" height="200">
	</center>
	<div>
		<br /><jsp:include page="Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>
