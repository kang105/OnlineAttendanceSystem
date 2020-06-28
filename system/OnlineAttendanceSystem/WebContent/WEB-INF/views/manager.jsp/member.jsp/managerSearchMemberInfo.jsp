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
	<center>
		<form id="Form1" name="Form1" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center"
				border="0">
				<TBODY>
					<tr>
						<td align="center" bgColor="#afd1f3"><strong>查询员工结果</strong></TD>
					</tr>
					<tr>
						<td align="right"><a
							href="<c:url value="/manager/MemberList" />">返回员工信息列表</a> <a
							href="<c:url value="/manager/member_add" />">添加员工信息</a> <a
							href="<c:url value="/manager" />">返回首页</a></td>
					</tr>
					<tr>
						<td align="center">
							<table cellspacing="0" cellpadding="1" border="1" id="DataGrid1"
								width="100%">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
									<td align="center" width="10%">id</td>
									<td align="center" width="10%">用户名</td>
									<td align="center" width="17%">电话</td>
									<td align="center" width="10%">邮箱</td>
									<td align="center" width="10%">性别</td>
									<td align="center" width="10%">部门</td>
									<td align="center" width="10%">操作</td>

								</tr>

								<tr>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${member.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${member.userName }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${member.phone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${member.email }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${member.sex }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${member.department.department }</td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><a
										href="${ pageContext.request.contextPath }/manager/FixMemberInfo?memberId=${member.id}">修改</a>|
										<a
										href="${ pageContext.request.contextPath }/manager/DeleteCurrentMember?memberId=${member.id}">删除</a>
									</td>
								</tr>

							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>