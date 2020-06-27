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
	<center>
		<form
			action="${ pageContext.request.contextPath }/manager/A-Type_search"
			method="post">
			请假类型：<input name="type" type="text" /><br /> <br /> <input
				type="submit" value="查找请假类型" />
		</form>
		<form id="Form1" name="Form1" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center"
				border="0">
				<TBODY>
					<tr>
						<td align="center" bgColor="#afd1f3"><strong>请假类型列表</strong>
						</TD>
					</tr>
					<tr>
						<td align="right"><a
							href="<c:url value="/manager/A-Type_add" />">添加请假类型</a> <a
							href="<c:url value="/manager" />">返回首页</a></td>
					</tr>
					<tr>
						<td align="center">
							<table cellspacing="0" cellpadding="1" border="1" id="DataGrid1"
								width="100%">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
									<td align="center" width="50%">请假类型
									<td align="center" width="40%">操作</td>
								</tr>
								<c:forEach items="${paginationSupport.items }" var="absenceType"
									varStatus="vs">
									<tr>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="50%">${absenceType.type }</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="40%"><a
											href="${ pageContext.request.contextPath }/manager/A-Type_modify?id=${absenceType.id}">编辑</a>|
											<a
											href="${ pageContext.request.contextPath }/manager/A-Type_delete?id=${absenceType.id}">删除</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
		 <jsp:include page="../../page.jsp">
			<jsp:param name="subTitle" value="manager/absenceTypeList" /> 
		</jsp:include>
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>