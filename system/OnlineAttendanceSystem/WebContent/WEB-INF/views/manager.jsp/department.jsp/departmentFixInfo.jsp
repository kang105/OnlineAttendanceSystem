<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>修改部门信息</title>
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
			<li class="nav-item"><a class="nav-link "
				href="<c:url value="/manager/MemberList" />">查看所有员工信息</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/manager/Department" />">查看所有部门信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/attend_search_time" />">查询考勤记录</a>
			<li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
	<center>
		<h1>
			部门
			<c:out value="${department.department}" />
			的信息
		</h1>
		<a href="<c:url value="/manager/Department" />">返回部门信息</a> <br /> <br />
		<c:if test="${not empty sessionScope.error_1}">
			<h1 align="center">
				<c:out value="${sessionScope.error_1}" />
			</h1>
		</c:if>
		<c:if test="${not empty sessionScope.error_2}">
			<h1 align="center">
				<c:out value="${sessionScope.error_2}" />
			</h1>
		</c:if>
		<div>
			<sf:form method="POST" commandName="DForm">
				<div class="form-group">
					<label>部门名字</label>
					<sf:input class="form-control" style="width:200px" path="name"
						value="${department.department}" />
					<sf:errors path="department" cssClass="error" />
				</div>
				<c:if test="${ department.id != 1}">
					<div class="form-group">
						<label>上级部门</label>
						<sf:select class="form-control" style="width:200px"
							path="department" items="${sessionScope.departments}"
							itemLabel="department" itemValue="id"
							selected="${department.upperDepartment}" />
					</div>
				</c:if>
				<input class="btn btn-primary" type="submit" value="修改" />
			</sf:form>
		</div>

	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>