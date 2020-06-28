<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理端</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../Header_ins.jsp"%>
	</div>
	<center>
		<h1>
			欢迎来到管理员主页

			<c:if test="${not empty sessionScope.manager }">
    	，<c:out value="${manager.username}" />
				<br />
			</c:if>

		</h1>
		<c:if test="${empty sessionScope.manager }">
			<a href="<c:url value="/manager/test" />">获取test session</a> ||
    </c:if>
		<ul class="nav nav-tabs justify-content-center">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_uncheck" />">未审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_check" />">已审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceTypeList" />">查看所有请假类型</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/MemberList" />">查看所有员工信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/Department" />">查看所有部门信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/attend_search_time" />">查询考勤记录</a>
			<li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
		<img src="<%=path%>/image/index.jpg" height="200">
	</center>
	<div>
		<br /><jsp:include page="../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>