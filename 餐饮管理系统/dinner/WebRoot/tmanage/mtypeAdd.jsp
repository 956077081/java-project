<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mtypeAdd.jsp' starting page</title>
    
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
    	<h1>新增类型</h1>
    	<div class="context">
				<div class="center">
					<strong>
	   				<a href="tmanage" class="a_demo">返回管理</a> &nbsp;&nbsp;&nbsp;
					</strong>
				</div>
			</div>
			<div class="container" style="width:880px;">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
					<br>
   				<form action="MenutypeServlet" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    			<!-- 用户确认添加 action为addover -->
    			<input type="hidden" name="action" value="addover" >
     <!--<p>类型名：<input type="text" name = "mtype" required="required"></p>-->
    			<div class="form-group">
						<div class="alert alert-success" role="alert">填写类型名</div>
					</div>
    			<div class="form-group">
							<input type="text" class="form-control" name = "mtype" required="required"  placeholder=" 菜品类型名" autocomplete="off">
					</div>
					<div class="form-group" align="center">
    				<input type="submit" value="确认添加" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;
    				<!--<input type="button" value="返回" onclick="javascript:location.href='tmanage'">-->
    			</div>		
   		</form>
   		<br><br><br><br><br>
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
