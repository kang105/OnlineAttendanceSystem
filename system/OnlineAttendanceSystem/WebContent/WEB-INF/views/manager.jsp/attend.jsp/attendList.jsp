<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
<title>考勤列表</title>
 <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/css/bootstrap.min.css" />" >
          <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
          <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
  body{padding: 20px;}
  .demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
  .demo-footer{padding: 50px 0; color: #999; font-size: 14px;}
  .demo-footer a{padding: 0 5px; color: #01AAED;}
  </style>
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
			<br />开始日期:<br /><br>
<input type="text" class="demo-input" id="test1" name="bdate" placeholder="请选择日期">
<script src="../resources/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
</script>

		<br/><br/>
		<br />结束日期:<br /><br>
<input type="text" class="demo-input" id="test2" name ="edate" placeholder="请选择日期">
<script src="../resources/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#test2' //指定元素
});
</script>
	<br/>
			<br /> <button type="submit" class="btn btn-primary">搜索考勤</button><br><br>
	</form>
	<form id="Form1" name="Form1" method="post">
		<table style="width:50%" cellSpacing="1" cellPadding="0"  align="center"
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