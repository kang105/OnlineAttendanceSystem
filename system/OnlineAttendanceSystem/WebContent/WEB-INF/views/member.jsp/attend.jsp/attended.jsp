<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户端</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
		<ul class="nav nav-tabs justify-content-center">
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/member/attend" />">我要打卡</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/member/info" />">个人信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/member/attendance" />">我的考勤</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/member/takeOff" />">申请休假</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/member/absence" />">我的申请</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
		<h1>
			您今天已经打过卡了
			<c:if test="${not empty sessionScope.member }">
    	，<c:out value="${member.userName}" />
				<br />
			</c:if>
		</h1>
		<a href="<c:url value="/member" />">返回首页</a>
	</center>
	<div class="footer">
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>