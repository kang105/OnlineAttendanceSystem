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
	
	<ul class="nav nav-tabs justify-content-center">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_uncheck" />">未审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/absenceList_check" />">已审核请假列表</a></li>
			<li class="nav-item"><a class="nav-link "
				href="<c:url value="/manager/absenceTypeList" />">查看所有请假类型</a></li>
			<li class="nav-item"><a class="nav-link "
				href="<c:url value="/manager/MemberList" />">查看所有员工信息</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="<c:url value="/manager/Department" />">查看所有部门信息</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/manager/attend_search_time" />">查询考勤记录</a>
			<li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/home" />">退出登录</a></li>
		</ul>
		
	<center>
		<a href="<c:url value="/manager" />">返回首页</a>
		<h1>department树状图</h1>
		<div>
			<form method="POST" name="selectDepartment">
				输入部门名<input type="text" name="departmentName" /><br /> <br /> <input
					class="btn btn-primary" type="submit" value="搜索部门" />
			</form>
			<br /> <a class="btn btn-primary"
				href="<c:url value="/manager/addDepartment" />">新增部门</a>
		</div>
		<br />
	</center>


	<div class="layui-row layui-col-space10">
		<div class="layui-col-md3"></div>
		<div class="layui-col-md2"></div>
		<div class="layui-col-md4">
			<div id="test1" style="zoom:1.3"></div>
		</div>
		<div class="layui-col-md3"></div>
	</div>


	<script type="text/javascript" src="../resources/layui/layui.js"></script>
	<script type="text/javascript"
		src="../resources/js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
		layui.use(['tree','layer'], function() {
			var tree = layui.tree;
			var layer = layui.layer;
			var data1 = ${data}; //${data};	
			//渲染
			var inst1 = tree.render({
				id : 'id_1',
				elem : '#test1', //绑定元素
				data : data1,
				edit: true,
				onlyIconControl : true,
				//customOperate : true,
				 click : function(obj) {
					var _data = obj.data;
					var title = _data.title;
					var id = _data.id;
					layer.msg("id: " + id + ", " + "title: " + title);
				}, 
			     operate : function(obj) {
					var type = obj.type; //得到操作类型：add、edit、del
					var _data = obj.data; //得到当前节点的数据
					var _title = _data.title;
					var elem = obj.elem; //得到当前节点元素
					var id = _data.id;
					if (type === 'update') { //修改节点
						var nt = elem.find('.layui-tree-txt').html();
						var new_title = nt ? nt : "未命名";
						jQuery.post("fixDepartmentTree", {Id: id, newName: new_title},function (msg) {
							var _msg = eval('(' + msg + ')');
							var __msg = _msg.msg;
							var __title = _msg.title;
							 if(__msg == "1"){
								 layer.msg("修改成功")
							 }else if(__msg == "0"){
								 layer.msg("修改失败，已有相同名字的部门");
								// reloadTree(data2);
							 }else if(__msg == "-1"){
							 }else{
								 layer.msg("修改失败");
								// elem.find('.layui-tree-txt').html(__title);
							 }
						 });
					} else if (type === 'del') { //删除节点;
						if(_title == "公司"){		
							return;
						}else{
						jQuery.post("delDepartmentTree", {Id: id},function (msg) {
							 if(msg == "1"){
								 layer.msg("删除成功");
							 }else{
								 layer.msg("删除失败");
							 }
						 });
						}
			  }
					}
				}
			)
			}		
		);
	</script>

	<br />
	<div>
		<br /><jsp:include page="../../Footer_ins.jsp"></jsp:include>
	</div>
</body>

</html>