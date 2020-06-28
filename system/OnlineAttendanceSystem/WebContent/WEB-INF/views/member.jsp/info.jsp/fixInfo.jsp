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
		<li class="nav-item"><a class="nav-link active"
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
	<center>
		<h1>个人信息</h1>
		<c:if test="${not empty sessionScope.error_1}">
			<h2>
				<c:out value="${sessionScope.error_1}"></c:out>
			</h2>
		</c:if>
		<a href="<c:url value="/member" />">返回首页</a> <br /> <br />
		<sf:form method="POST" commandName="member">
			<div class="form-group">
				<label>名字</label>
				<sf:input class="form-control" style="width:200px" path="userName"
					value="${sessionScope.member.userName}" />
				<sf:errors path="userName" cssClass="error" />
			</div>
			<div class="form-group">
				<label>密码</label>
				<sf:password class="form-control" style="width:200px"
					path="password" value="${sessionScope.member.password}" />
				<sf:errors path="password" cssClass="error" />
			</div>
			<div class="form-group">
				<label>电话</label>
				<sf:input class="form-control" style="width:200px" path="phone"
					value="${sessionScope.member.phone}" />
				<sf:errors path="phone" cssClass="error" />
			</div>
			<div class="form-group">
				<label>电子邮箱</label>
				<sf:input class="form-control" style="width:200px" path="email"
					value="${sessionScope.member.email}" />
				<sf:errors path="email" cssClass="error" />
			</div>
			<div class="form-group">
				<label>性别</label>
				<sf:select class="form-control" style="width:200px" path="sex"
					items="${sessionScope.sex}" />
				<sf:errors path="sex" cssClass="error" />
			</div>
			<c:if
				test="${not empty sessionScope.member.department.department && not empty sessionScope.departments}">
				<div class="form-group">
					<label>部门</label>
					<sf:select class="form-control" style="width:200px"
						path="department.id" items="${sessionScope.departments}"
						itemLabel="department" itemValue="id"
						selected="${sessionScope.member.department.id}" />
				</div>
			</c:if>
			<input class="btn btn-primary" type="submit" value="修改" />
		</sf:form>
		<br /> <br />
	</center>
	<div class="footer">
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>