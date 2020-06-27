<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户端</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<ul class="nav nav-tabs justify-content-center">
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/attend" />">我要打卡</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/info" />">个人信息</a></li>
		<li class="nav-item"><a class="nav-link active"
			href="<c:url value="/member/attendance" />">我的考勤</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/takeOff" />">申请休假</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/absence" />">我的申请</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home" />">退出登录</a></li>
	</ul>
	<center>
		<h1>


			考勤<br />
		</h1>
		<c:if test="${empty sessionScope.member }">
			<a href="<c:url value="/member/test" />">获取test session</a>
			<br />
		</c:if>
		<a href="<c:url value="/member" />">返回首页</a>
		<c:if
			test="${not empty sessionScope.member && not empty sessionScope.member.id}">
			<div style="width: 40%">
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th scope="col" style='text-align: center;'>打卡日期:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paginationSupport.items}" var="attendance">
							<tr>
								<th style='text-align: center;'><fmt:formatDate
										value="${attendance.attendDate}" pattern="yyyy-MM-dd" /></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		 <jsp:include page="../../page.jsp">
			<jsp:param name="subTitle" value="member/attendance" /> 
		</jsp:include>
		
	</center>
	<div class="footer">
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>