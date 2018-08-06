<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatecuisine.jsp' starting page</title>
    
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
	<!--<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />-->
	<!--<script type="text/javascript" src="tablecloth/tablecloth.js"></script>-->
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
  	<h1>菜品修改</h1>
  		<div class="context">
			<div class="center">
				<strong>
	   			<a href="cuisine" class="a_demo">返回管理</a> &nbsp;&nbsp;&nbsp;
				</strong>
			</div>
		</div>
  	
    <div class="container" style="width:880px;">
    	<div class="row">
				<div class="col-md-4 col-md-offset-4">
    		<form action="cuisine" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    			<div class="form-group">
					<div class="alert alert-success" role="alert">修改菜品价格及类型</div>
				</div>
    			<input type="hidden" name="action" value="updateover">
    			<input type="hidden" name="cid" value="${cu.cid }">
    
    			<div class="form-group">
							<label class="sr-only"></label>菜品ID：
							<input type="text" class="form-control"   readonly="readonly" value="${cu.cid }">
					</div>
					<div class="form-group">
							<label class="sr-only"></label>菜品名称：
							<input type="text" class="form-control"   readonly="readonly" value="${cu.cname }">
					</div>
					<div class="form-group">
							<label class="sr-only"></label>图片：
							<img alt="" src="images/${cu.cimg}" width="120" height="80">
					</div>
					<div class="form-group">
							<label class="sr-only"></label>菜品类别：
							<select name= "type" class="form-control">
			    		<c:forEach items="${mlist}" var="m">
			    		<option value='${m.mid}' ${m.mid==cu.mid?'selected':""}> ${m.mtype}</option>
			    		</c:forEach>
			    		</select>
					</div>
					<div class="form-group">
							<label class="sr-only"></label>菜品价格：
							<input type="number" class="form-control" min="1" required="required" value="${cu.cprice }"  name = "cprice" step="0.01">
					</div>
					<div class="form-group "  >
							<label class="sr-only"></label>菜品描述：
							<textarea    name = "cinfo" cols= "20px" rows="5px">
								${cu.cinfo }
							</textarea>
					</div>
					
					
					
    <!--<table>
    	<tr><th>菜品id</th><td>${cu.cid }</tr>
    	<tr><th>菜品名称</th><td>${cu.cname }</tr>
    	<tr><th>图片</th><td><img alt="" src="images/${cu.cimg}" width="120" height="80"></td></tr>
    	<tr><th>菜品价格</th><td><input type="number" min="1" required="required" value="${cu.cprice }"  name = "cprice"> </td></tr>
    	<tr><th>菜品类别</th>
    	<td>
    		<select name= "type">
    		<c:forEach items="${mlist}" var="m">
    		<option value='${m.mid}' ${m.mid==cu.mid?'selected':""}> ${m.mtype}</option>
    		</c:forEach>
    		</select>
    	<td></td></tr>
    </table>-->
    		<div class="form-group" align="center">
					<input type ="submit" value="保存修改" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type ="reset" value="重置" class="btn btn-primary">
			</div>
    </form>
    
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
