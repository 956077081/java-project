<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addcuisine.jsp' starting page</title>
    
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
	<script type="text/javascript">
	function addcuisine(){
	 var cimg=document.form1.cimg.value;//获取上传图片的名字；
	 
	 if(cimg==null||cimg==""){
	 	alert("没有图片");
	 }else{
	 var cname=document.form1.cname.value.trim(); 
	 if(cname==null||cname==""){
	 alert("名字不能为空");}else{
	 var ccost=document.form1.ccost.value.trim();
	 var cprice=document.form1.cprice.value.trim();
	  var cinfo=document.form1.cinfo.value.trim();
	 if(cprice==null||cprice==""){
	 alert("价格不能为空"); }else{
	 
	
	 var mid=document.form1.mid.value;//获取类型值
	 
	 
	 
	 document.form1.action="cuisine?action=addover&cname="+cname+"&ccost="+ccost+"&cprice="+cprice+"&mid="+mid+"&cimg="+cimg+"&cinfo="+cinfo;
	 document.form1.submit();
	}
	}
	}
	}
	
</script>
  </head>
  
  <body>
  	<div id="container">
  	<img src="images/banner.gif" width="880px" height="120" />
  	<h1>新增菜品</h1>
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
			  	<form method="post" name="form1" enctype="multipart/form-data" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    				<div class="form-group">
					<div class="alert alert-success" role="alert">填写菜品信息</div>
					</div>
					<div class="form-group">
							<input type="text" class="form-control" name="cname"  placeholder="新菜品名" autocomplete="off">
					</div>
					<div class="form-group">
							<input type="number" min="0" name="ccost" class="form-control"  placeholder="菜品成本" autocomplete="off">
					</div>
					<div class="form-group">
							<input type="number" min="1" name="cprice" class="form-control"  placeholder="菜品定价" autocomplete="off">
					</div>
					<div class="form-group">
							<label class="sr-only"></label>菜品类别：
							<select name= "mid" class="form-control" >
			    		<c:forEach items="${listmenu}" var="me">
			    			<option value="${me.mid}"> ${me.mtype}</option>	
			    		</c:forEach>
			    		</select>
					</div>
					<div class="form-group">
							<label class="sr-only"></label>选择图片：
							<input type="file" name="cimg" class="form-control">
					</div>
					<div class="form-group">
					<textarea rows="5" cols="25" name = "cinfo"></textarea>
					</div>
 <%--  <p>新的菜品名字：<input type="text" name="cname"></p>
<!--   <p>成本:<input type="number" name="ccost" ></p> -->
  <p>价格:<input type="number" name="cprice"></p>
  
   <p>类型<select name="mid">
         <c:forEach items="${mlist}" var="me">
			<option value="${me.mid}"> ${me.mtype}</option>	
			</c:forEach> 
   </select></p>
   <p>图片:<input type="file" name="cimg" ></p> --%>
   
   <div class="form-group" align="center">
  		<input type="button" value="提交"  onclick="addcuisine()" class="btn btn-primary">
  	</div>
  </form>
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
