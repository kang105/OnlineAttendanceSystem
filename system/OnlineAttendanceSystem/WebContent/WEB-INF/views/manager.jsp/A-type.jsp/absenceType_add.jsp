<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%-- <%@ page session="false" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改请假类型</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
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
		<c:choose>
			<c:when test="${not empty modifyAbsenceType }">
				<h1>修改请假类型</h1>
				<tr>
					<td><a href="<c:url value="/manager/absenceTypeList" />">返回请假类型列表</a>
					</td>
				</tr>
				<sf:form method="POST" commandName="absenceType">
					<sf:errors path="*" cssClass="error" />
					<br />
					<input type="hidden" name="id" value="${modifyAbsenceType.id}" />
					<%--   当前请假类型为：${modifyAbsenceType.type}<br /> <br/> --%>
	请假类型：<sf:input path="type" value="${modifyAbsenceType.type} " />
					<sf:errors path="type" cssClass="error" />
					<br />
					<br />
					<br />
					<input class="btn btn-primary" type="submit" value="修改请假类型" />
				</sf:form>
			</c:when>
			<c:otherwise>
				<h1>添加请假类型</h1>
				<tr>
					<td><a href="<c:url value="/manager/absenceTypeList" />">返回请假类型列表</a>
					</td>
				</tr>
				<sf:form method="POST" commandName="absenceType">
					<sf:errors path="*" cssClass="error" />
					<br />
					<br />
	请假类型：<sf:input path="type" />
					<sf:errors path="type" cssClass="error" />
					<br />
					<br />
					<input class="btn btn-primary" type="submit" value="添加请假类型" />
				</sf:form>
			</c:otherwise>
		</c:choose>
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>