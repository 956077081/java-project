<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	h2{font-size: 40px;font-weight: 2000;font-family:  "微软雅黑"; color:#fa6e31;}
	
	</style>
	<script type="text/javascript">
		var s = 4;
		function showTime(){
			s=s-1;
			document.getElementById("second").innerHTML="<font color='red'>"+s+"</font>秒后自动跳回类型管理页";
			//每隔1000毫秒调用一次
			if(s<=0){
				location.href="MenutypeServlet";
			}
			window.setTimeout("showTime()",1000);
		}
	</script>
  </head>
  
  <body onload="showTime()">
  	 <div align="center">
  	 	<br>
  		<h2>类型处理成功！</h2>
  		<img src="images/succes.gif" >
  	</div>
  	
  	<div id="second" align="center"><font color='red'>4</font>秒后自动跳回类型管理页</div>
  	<br>
  	<div align="center" style="color:#36f">若无法跳转，请点击<a href="tmanage">返回类型管理页</a></div>

  </body>
</html>
