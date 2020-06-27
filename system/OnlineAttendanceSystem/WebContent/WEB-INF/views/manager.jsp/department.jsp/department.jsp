<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="../resources/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../resources/style.css">

<meta charset="UTF-8">
<title>Department</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
	<div class="header">
		<%@ include file="../../Header_ins.jsp"%>
	</div>
	<center>
		<a href="<c:url value="/manager" />">返回首页</a>
		<h1>department树状图</h1>
		<div>
			<form method="POST" name="selectDepartment">
				输入部门名<input type="text" name="departmentName" /><br />
				<br /> <input class="btn btn-primary" type="submit" value="搜索部门" />
			</form>
			<br /> <a class="btn btn-primary"
				href="<c:url value="/manager/addDepartment" />">新增部门</a>
		</div>
		<br />
	</center>
	
	
	<div class="layui-row layui-col-space10">
  <div class="layui-col-md3">
    
  </div>
  <div class="layui-col-md2">
    
  </div>
  <div class="layui-col-md4">
   <div id="test1" ></div>
  </div>
  <div class="layui-col-md3">
    
  </div>
</div>
		
	
		<script type="text/javascript" src="../resources/layui/layui.all.js"></script>
		<script type="text/javascript">
  layui.use('tree', function(){
    var tree = layui.tree;
   var data1 = ${data};
    //渲染
    var inst1 = tree.render({
      elem: '#test1'  //绑定元素
      ,data: data1
    });
  });
  </script>
	
	<br />
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>

</html>