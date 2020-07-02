<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假列表</title>
 <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/css/bootstrap.min.css" />" >
  </head>
  <body>
   <div class="header"> 
   		<%@ include file="../../Header_ins.jsp"%> 
  </div> 
  <ul class="nav nav-tabs justify-content-center">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_uncheck" />">未审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/manager/absenceList_check" />">已审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link "
				href="<c:url value="/manager/absenceTypeList" />">查看所有请假类型</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/MemberList" />">查看所有员工信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/Department" />">查看所有部门信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/attend_search_time" />">查询考勤记录</a>
			<li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
  <center>
		<form id="Form1" name="Form1"  method="post">
		<tr>
			<td align="right"><a href="<c:url value="/manager" />">返回首页</a>

			</td>
		</tr><br/><br/>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" border="0">
				<TBODY>
					<tr>
						<td  align="center" bgColor="#afd1f3">
							<strong>请假列表</strong>
						</TD>
					</tr>
					<tr>
						<td  align="center">
							<table class="table table-striped">
								<thead>
									<tr>
										<td align="center" width="10%">员工</td>
										<td align="center" width="10%">请假类型</td>
										<td align="center" width="17%">请假描述</td>
										<td align="center" width="10%">请假时间</td>
										<td align="center" width="10%">是否核查</td>
										<td align="center" width="10%">核查者</td>
										<td align="center" width="10%">核查时间</td>
										<td align="center" width="10%">审核结果</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${paginationSupport.items }" var="absence" varStatus="vs">
											<tr>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													${absence.member.userName }
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													${absence.absenceType.type }
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="17%">
													${absence.decription }
												</td>	
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													<%-- <fmt:formatDate value="${absence.absenceTime }"
										pattern="yyyy-MM-dd HH:mm:ss" /> --%>
													${absence.absenceTime }
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
														已审核
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													${absence.manager.username }
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													<%-- <fmt:formatDate value="${absence.check_time }"
										pattern="yyyy-MM-dd HH:mm:ss" /> --%>
													${absence.check_time }
													
												</td>
												<td style="CURSOR: hand; HEIGHT: 22px" align="center"
													width="10%">
													<c:if test="${ absence.isChecked == false}">
														未通过
													</c:if>
													<c:if test="${ absence.isChecked == 'true'}">
														已通过
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	<jsp:include page="../../page.jsp">
	<jsp:param name="subTitle" value="manager/absenceList_check" /> 
	</jsp:include> 
	</center>
		<div>
		<br/><jsp:include page="../../Footer_ins.jsp"></jsp:include> 
   </div>
</body>
</html>