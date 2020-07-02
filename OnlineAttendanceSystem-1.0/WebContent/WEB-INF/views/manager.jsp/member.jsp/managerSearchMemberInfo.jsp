<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>查询员工结果</title>
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
	<form method="POST" name="selectMember">
			<a href="<c:url value="/manager" />">返回首页</a> <br/>下面两项至少输入一项来查找员工 <br />
			<br /> 输入员工ID<input type="text" name="id" /><br /> <br /> 输入员工名<input
				type="text" name="userName" /><br /> <br /> <input
				class="btn btn-primary" type="submit" value="搜索员工"> </br> </br> <a
				class="btn btn-primary" href="<c:url value="/manager/member_add" />"
				role="button">添加员工信息</a>
		</form>
		<br>
		<form id="Form1" name="Form1" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center"
				border="0">
				<TBODY>
					<tr>
						<td align="center" bgColor="#afd1f3"><strong>员工信息列表</strong></TD>
					</tr>

				</TBODY>
			</table>
			<table class="table table-striped">
				<caption></caption>
				<thead>
					<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
						<td align="center" width="10%">id</td>
						<td align="center" width="10%">用户名</td>
						<td align="center" width="17%">电话</td>
						<td align="center" width="10%">邮箱</td>
						<td align="center" width="10%">性别</td>
						<td align="center" width="10%">部门</td>
						<td align="center" width="10%">操作</td>
					</tr>
				</thead>
				<tbody>
				
						<tr>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${member.id }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${member.userName }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">${member.phone }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${member.email }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${member.sex }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${member.department.department }</td>

							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%"><a
								href="${ pageContext.request.contextPath }/manager/FixMemberInfo?memberId=${member.id}">修改</a>|
								<a
								href="${ pageContext.request.contextPath }/manager/DeleteCurrentMember?memberId=${member.id}">删除</a>

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