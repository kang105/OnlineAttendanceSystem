<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工注册</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../Header_ins.jsp"%>
	</div>
	<center>
		<h1>员工注册</h1>
<c:if test="${not empty sessionScope.error_1}">
			<h2 align="center">
				<c:out value="${sessionScope.error_1}" />
			</h2>
			</c:if>
		<sf:form method="POST" commandName="member">
			<sf:errors path="*" cssClass="error" />
			<br />
			<br />
    
          用户名:<sf:input path="userName" />
			<sf:errors path="userName" cssClass="error" />
			<br />
			<br />
	密码:<sf:password path="password" />
			<sf:errors path="password" cssClass="error" />
			<br />
			<br />
	电话:<sf:input path="phone" />
			<sf:errors path="phone" cssClass="error" />
			<br />
			<br />
	邮箱:<sf:input path="email" />
			<sf:errors path="email" cssClass="error" />
			<br />
			<br />
	性别<sf:select path="sex" items="${sessionScope.sex}" />
			<br />
			<br />

			<input type="submit" value="注册" />
		</sf:form>

		<a href="<c:url value="/home" />">返回首页</a>
	</center>
	<div>
		<br /><jsp:include page="../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>