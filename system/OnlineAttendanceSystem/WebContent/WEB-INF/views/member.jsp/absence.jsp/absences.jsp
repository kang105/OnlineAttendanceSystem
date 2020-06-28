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
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/attendance" />">我的考勤</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/member/takeOff" />">申请休假</a></li>
		<li class="nav-item"><a class="nav-link active"
			href="<c:url value="/member/absence" />">我的申请</a></li>
		<li class="nav-item"><a class="nav-link"
			href="<c:url value="/home" />">退出登录</a></li>
	</ul>
	<center>
		<h1>

			请假记录<br />
		</h1>
		<c:if test="${empty sessionScope.member }">
			<a href="<c:url value="/member/test" />">获取test session</a>
			<br />
		</c:if>
		<a href="<c:url value="/member" />">返回首页</a>
		<c:if
			test="${not empty sessionScope.member && not empty sessionScope.member.id}">
			<ul class="absenceList">
				<div style="width: 40%">
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th scope="col" style='text-align: center;'>请假时间</th>
								<th scope="col" style='text-align: center;'>请假类型</th>
								<th scope="col" style='text-align: center;'>描述</th>
								<th scope="col" style='text-align: center;'>审查状态</th>
								<th scope="col" style='text-align: center;'>审查人</th>
								<th scope="col" style='text-align: center;'>审查时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${paginationSupport.items}" var="absence">
								<tr>
									<td style='text-align: center;'><fmt:formatDate
											value="${absence.absenceTime}" pattern="yyyy-MM-dd" /></td>
									<td style='text-align: center;'><c:out
											value="${absence.absenceType.type}" /></td>
									<td style='text-align: center;'><c:out
											value="${absence.decription}" /></td>
									<c:if test="${empty absence.manager}">
										<td colspan="3" style='text-align: center;'>待审查</td>
									</c:if>
									<c:if test="${not empty absence.manager}">
										<c:if test="${absence.isChecked eq true}">
											<td style='text-align: center;'>通过</td>
											<td style='text-align: center;'><c:out
													value="${absence.manager.username}" /></td>
											<td style='text-align: center;'><fmt:formatDate
													value="${absence.check_time}" pattern="yyyy-MM-dd" /></td>
										</c:if>
										<c:if test="${absence.isChecked eq false}">
											<td style='text-align: center;'>未通过</td>
											<td style='text-align: center;'><c:out
													value="${absence.manager.username}" /></td>
											<td style='text-align: center;'><fmt:formatDate
													value="${absence.check_time}" pattern="yyyy-MM-dd" /></td>
										</c:if>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</ul>
		</c:if>
			 <jsp:include page="../../page.jsp">
			<jsp:param name="subTitle" value="member/absence" /> 
		</jsp:include>
		
	</center>
	<div class="footer">
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>
</html>