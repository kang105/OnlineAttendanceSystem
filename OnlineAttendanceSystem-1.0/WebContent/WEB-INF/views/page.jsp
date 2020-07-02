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
          	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>
 <c:set var="page" value="${param.subTitle}" /> 
  每页${paginationSupport.pageSize}条请假记录，
	    第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条请假记录
	<br />

	<br />
<ul class="pagination">
	<c:forEach var="i" begin="1" end="${paginationSupport.totalPageCount}"  step="1">
    <li><a href="<c:url value="/${page}?pageNo=${i}" />">${i}</a></li>
</c:forEach>
    </ul>
  </body>
</html>
