<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>修改员工信息</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
		<h1>
			员工
			<c:if test="${not empty member }">
				<c:out value="${member.userName}" />
			</c:if>
			的个人信息
		</h1>
		<c:if test="${not empty sessionScope.error_1}">
			<h2>
				<c:out value="${sessionScope.error_1}"></c:out>
			</h2>
		</c:if>
		<a href="<c:url value="/manager/MemberList" />">返回首页</a> <br /> <br />
		<div>
			<sf:form method="POST" commandName="member">
				<div class="form-group">
					<label>名字</label>
					<sf:input class="form-control" style="width:200px" path="userName"
						value="${member.userName}" />
					<sf:errors path="userName" cssClass="error" />
				</div>
				<div class="form-group">
					<label>密码</label>
					<sf:password class="form-control" style="width:200px"
						path="password" value="${member.password}" />
					<sf:errors path="password" cssClass="error" />
				</div>
				<div class="form-group">
					<label>电话</label>
					<sf:input class="form-control" style="width:200px" path="phone"
						value="${member.phone}" />
					<sf:errors path="phone" cssClass="error" />
				</div>
				<div class="form-group">
					<label>电子邮箱</label>
					<sf:input class="form-control" style="width:200px" path="email"
						value="${member.email}" />
					<sf:errors path="email" cssClass="error" />
				</div>
				<div class="form-group">
					<label>性别</label>
					<sf:select items="${sessionScope.sex}" class="form-control"
						style="width:200px" path="sex" value="${member.sex}" />
					<sf:errors path="sex" cssClass="error" />
				</div>
				<c:if
					test="${not empty member.department.department && not empty sessionScope.departments}">
					<div class="form-group">
						<label>部门</label>
						<sf:select class="form-control" style="width:200px"
							path="department" items="${sessionScope.departments}"
							itemLabel="department" itemValue="id"
							selected="${member.department}" />
					</div>
				</c:if>
				<input class="btn btn-primary" type="submit" value="修改" />
			</sf:form>
			<br /> <br />
		</div>
	</center>
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>