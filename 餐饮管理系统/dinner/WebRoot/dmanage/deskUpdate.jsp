<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deskUpdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/style2.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/style.css">
	<style>
		.context{border-top:dashed 1px #ccc;padding-top:20px;margin:5px;}
		.context{text-align:center;}
		.context .left{ float:left;}
		.context .center{ display:inline;}
		.context .right{ float:right;}
		#container { margin:0 auto; width:880px; background:#fff; padding-bottom:20px; }
		h1 { font: 280% KaiTi ; margin:auto; line-height:40px;text-align:center }
	</style>
  </head>
  
  <body>
  	<div id="container">
  		<img src="images/banner.gif" width="880px" height="120" />
    	<h1>修改餐桌名</h1>
    	<div class="context">
				<div class="center">
					<strong>
	   				<a href="dmanage" class="a_demo">返回管理</a> &nbsp;&nbsp;&nbsp;
					</strong>
				</div>
			</div>
			<div class="container" style="width:880px;">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
					<br>
					
    			<form action="DeskServlet" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    			<input type="hidden" name="action" value="updateover">
    			<input type="hidden" name="did" value="${desk.did }">
    			
    			<div class="form-group">
						<div class="alert alert-success" role="alert">填写新的餐桌/包厢名</div>
					</div>
					<div class="form-group">
							<label class="sr-only"></label>餐桌ID：
							<input type="text" class="form-control"   readonly="readonly" value="${desk.did }">
					</div>
					<div class="form-group">
							<label class="sr-only"></label>餐桌/包厢名：
							<input type="text" class="form-control" name = "dname" value="${desk.dname }" required="required"  autocomplete="off">
					</div>
    <!--<p>餐桌编号：<input type="text" name = "dname" value="${desk.dname }" required="required"></p>-->
    			<div class="form-group" align="center">
	    				<input type ="submit" value="保存修改" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;
    					<input type ="reset" value="重置" class="btn btn-primary">
    			</div>
    	</form>
    	
    	<br><br><br><br><br>
    	<br><br><br><br><br>
    	</div>
		</div>
			
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
  </body>
</html>
