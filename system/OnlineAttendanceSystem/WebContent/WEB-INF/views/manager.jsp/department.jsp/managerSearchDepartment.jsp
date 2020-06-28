<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>部门查询结果</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
		<a href="<c:url value="/manager/Department" />">返回部门信息</a>
		<h1>查询部门结果</h1>
		<br />
		<br />
		<div>
			id =
			<c:out value="${department.id}"></c:out>
		</div>
		<div>
			department name =
			<c:out value="${department.department}"></c:out>
		</div>
		<div>
			upper department name =
			<c:out value="${department.upperDepartment.department}"></c:out>
		</div>
		<br />
		<c:if test="${ department.id != 1}">
			<a
				href="<c:url value="/manager/DeleteCurrentDepartment?departmentId=${department.id}" />">删除当前部门</a>
			<a
				href="<c:url value="/manager/FixDepartmentInfo?departmentId=${department.id}" />">修改当前部门</a>
		</c:if>
		<c:if test="${ department.id == 1}">
		此为根节点，不可删除也不可修改！
		</c:if>
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>