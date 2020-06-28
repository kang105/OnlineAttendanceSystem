<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤列表</title>
 <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/css/bootstrap.min.css" />" >
  </head>
  <body>
   <div class="header"> 
   		<%@ include file="../../Header_ins.jsp"%> 
  </div> 
  <center>
  <p>日期输入格式为:yyyy-mm-dd</p>
  <c:if test="${not empty sessionScope.error_1}">
   <c:out value="${sessionScope.error_1}"></c:out>
  </c:if>
  <form action="${ pageContext.request.contextPath }/manager/attend_search_time" method="post">
			 <br />
			<br />开始日期:<input  name="bdate" /><br /><br>
			结束日期：<input name="edate"  />
		<br/><br/>
	<br/>
			<br /> <input type="submit" value="搜索考勤" /><br><br>
	</form>
	<form id="Form1" name="Form1" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			border="0">
			<TBODY>
				<tr>
					<td align="center" bgColor="#afd1f3"><strong>考勤列表</strong></TD>
				</tr>
				<tr>
					<td align="right"><a href="<c:url value="/manager" />">返回首页</a>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table cellspacing="0" cellpadding="1" border="1" id="DataGrid1"
							width="100%">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
								<td align="center" width="10%">id</td>
								<td align="center" width="10%">员工</td>
								<td align="center" width="10%">考勤时间</td>
							</tr>
							<c:forEach items="${paginationSupport.items }" var="attendance"
								varStatus="vs">
								<tr>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${attendance.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${attendance.member.userName }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${attendance.attendDate }</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
	 <jsp:include page="../../page.jsp">
	<jsp:param name="subTitle" value="manager/attend_search_time" /> 
	</jsp:include>  
	
	</center>
	<div>
		<br/><jsp:include page="../../Footer_ins.jsp"></jsp:include> 
   </div>
</body>
</html>