<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
	<ul class="nav nav-tabs justify-content-center">
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/attend" />">我要打卡</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/info" />">个人信息</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/attendance" />">我的考勤</a></li>
		<li class="nav-item"><a class="nav-link active"
			href="<c:url value="/member/takeOff" />">申请休假</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/absence" />">我的申请</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home" />">退出登录</a></li>
	</ul>
	<center>
		<h1>请假单</h1>
		<a href="<c:url value="/member" />">返回首页</a> <br />
		<br />
			<c:if test="${not empty sessionScope.error_1}">
			<h2 align="center">
				<c:out value="${sessionScope.error_1}" />
			</h2>
		</c:if>		
		<sf:form method="POST" commandName="absence">
			<div class="form-group">
				<label>类型</label>
				<sf:select class="form-control" style="width:250px;"
					path="absenceType.id" items="${sessionScope.absenceType}"
					itemLabel="Type" itemValue="id" />
			</div>
			<div class="form-group">
				<label>日期</label>
				<sf:select class="form-control" style="width:200px"
					path="absenceTime" items="${sessionScope.ableDate}"
					selected="${sessionScope.Date}" />
				<sf:errors path="absenceTime" cssClass="error" />
				<br />
				<br />
			</div>
			<div class="form-group">
				<label>描述</label>
				<sf:textarea class="form-control" style="width:300px;height:150px;"
					path="decription" />
				<sf:errors path="decription" cssClass="error" />
				<br />
				<br />
			</div>
			<button class="btn btn-primary" type="submit">提交</button>
		</sf:form>
		<br />
		<br />
	</center>
	<div class="footer">
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>