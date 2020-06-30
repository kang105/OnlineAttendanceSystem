<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工添加</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<ul class="nav nav-tabs justify-content-center">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_uncheck" />">未审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_check" />">已审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link "
				href="<c:url value="/manager/absenceTypeList" />">查看所有请假类型</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/manager/MemberList" />">查看所有员工信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/Department" />">查看所有部门信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/attend_search_time" />">查询考勤记录</a>
			<li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
	<center>
		<h1>员工添加</h1>
		<c:if test="${not empty sessionScope.error_1}">
			<h2>
				<c:out value="${sessionScope.error_1}"></c:out>
			</h2>
		</c:if>
		<sf:form method="POST" commandName="member">
			<sf:errors path="*" cssClass="error" />
			<br />
			<br />
			<a href="<c:url value="/manager/MemberList" />">返回员工信息列表</a>
			<br />
			<br />

			<label for="name">用户名： <input type="text"
				class="form-control" id="name" name="userName" placeholder="请输入用户名">
			</label>

			<br />
			<label for="password">密码：<input type="password"
				class="form-control" id="password" name="password"
				placeholder="请输入密码">
			</label>
			<br />

			<label for="password">电话：<input type="phone"
				class="form-control" id="password" name="phone" placeholder="请输入电话">
			</label>
			<br />
			<label for="email">邮箱：<input type="email"
				class="form-control" id="password" name="email" placeholder="请输入邮箱">
			</label>
			<br />
			<br />
	性别     <sf:select path="sex" items="${sessionScope.sex}" />
			<br />
			<br />
			<button type="submit" class="btn btn-primary">注册</button>
		</sf:form>

	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>