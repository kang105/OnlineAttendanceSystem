<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>添加部门</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
		<h1>添加部门</h1>
		<a href="<c:url value="/manager/Department" />">返回部门信息</a> <br /> <br />
		<div>
			<c:if test="${not empty sessionScope.error_1}">
			<h2>
				<c:out value="${sessionScope.error_1}"></c:out>
				</h2>
			</c:if>
			
			<sf:form method="POST" commandName="DForm">
				<div class="form-group">
					<label>部门名字</label>
					<sf:input class="form-control" style="width:200px" path="name" />
					<sf:errors path="department" cssClass="error" />
				</div>
				<div class="form-group">
					<label>上级部门</label>
					<sf:select class="form-control" style="width:200px"
						path="department" items="${sessionScope.departments}"
						itemLabel="department" itemValue="id" />
				</div>

				<input class="btn btn-primary" type="submit" value="添加" />
			</sf:form>
		</div>

	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>