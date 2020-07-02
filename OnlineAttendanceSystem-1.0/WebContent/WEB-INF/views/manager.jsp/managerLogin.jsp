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
	
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	<div class="header">
		<%@ include file="../Header_ins.jsp"%>
	</div>
	<center>
		<h1>管理员登陆</h1>
			<c:if test="${not empty sessionScope.error_1}">
			<h2 align="center">
				<c:out value="${sessionScope.error_1}" />
			</h2>
		</c:if>
	<form method="post">
		<div class="form-group">
		<label for="name">用户名：<input type="text" class="form-control" id="name" name="userName"
			   placeholder="请输入用户名"> </label>
		</div>
		<div class="form-group">
		<label for="password">密码：<input type="password" class="form-control" id="password" name="password"
			   placeholder="密码"> </label>
			   
		</div>
	 	<div style="text-align:center">
		<button type="submit" class="btn btn-primary">
		确定
		</button>
		</div>
	
	</form> 
		<a href="<c:url value="/home" />">返回首页</a>
	</center>
	<div>
		<br /><jsp:include page="../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>
