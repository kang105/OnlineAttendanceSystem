<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>分页</title>
 <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/css/bootstrap.min.css" />" >
  </head>
  <body>
 <c:set var="page" value="${param.subTitle}" /> 
  每页${paginationSupport.pageSize}条请假记录，
	    第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条请假记录
	<br />
	<c:if test="${paginationSupport.previousPage}">
		<a
			href="<c:url value="/${page}?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
	</c:if>
	<c:if test="${paginationSupport.nextPage}">
		<a
			href="<c:url value="/${page}?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
	</c:if>
	<br />
	
  </body>
</html>
