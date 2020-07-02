<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假类型列表</title>
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
			<li class="nav-item"><a class="nav-link active"
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
	<center>
		<form
			action="${ pageContext.request.contextPath }/manager/A-Type_search"
			method="post">
			<a href="<c:url value="/manager/absenceTypeList" />">返回请假类型列表</a>
			</td> 请假类型：<input name="type" type="text" /><br /> <br /> <input
				class="btn btn-primary" type="submit" value="查找请假类型"> </br> </br>
			<a class="btn btn-primary"
				href="<c:url value="/manager/A-Type_add" />" role="button">添加请假类型</a>

		</form>
		<br /> <br />
		<form id="Form1" name="Form1" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center"
				border="0">
				<TBODY>
					<tr>
						<td align="center" bgColor="#afd1f3"><strong>请假类型列表</strong>
						</TD>
					</tr>
				</TBODY>
			</table>
			<table class="table table-striped">
			<caption></caption>	
			<thead>
				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
					<td align="center" width="50%">请假类型
					<td align="center" width="40%">操作</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="50%">${searchAbsenceType.type }</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="40%"><a
						href="${ pageContext.request.contextPath }/manager/A-Type_modify?id=${searchAbsenceType.id}">编辑</a>|
						<a
						href="${ pageContext.request.contextPath }/manager/A-Type_delete?id=${searchAbsenceType.id}">删除</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>