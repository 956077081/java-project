<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
   String bug = (String)request.getAttribute("bug");

 %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
	</script>
	<style>
*{
	padding:0px;
	margin:0px;
	}

body{
	font-family:Arial, Helvetica, sans-serif;
	background:url(images/grass.jpg);
	font-size:13px;
    
	}
img{
	border:0;
	}
.lg{width:468px; height:468px; margin:100px auto; background:url(images/login_bg.png) no-repeat;}
.lg_top{ height:200px; width:468px;}
.lg_main{width:400px; height:180px; margin:0 25px;}
.lg_m_1{
	width:290px;
	height:100px;
	padding:60px 55px 20px 55px;
}
.ur{
	height:29px;
	border:0;
	color:#666;
	width:236px;
	margin:4px 28px;
	background:url(images/user.png) no-repeat;
	padding-left:10px;
	font-size:16pt;
	font-family:Arial, Helvetica, sans-serif;
}
.pw{
	height:29px;
	border:0;
	color:#666;
	width:236px;
	margin:4px 28px;
	background:url(images/password.png) no-repeat;
	padding-left:10px;
	font-size:16pt;
	font-family:Arial, Helvetica, sans-serif;
}
.bn{width:330px; height:72px; background:url(images/enter.png) no-repeat; border:0; display:block; font-size:18px; color:#FFF; font-family:Arial, Helvetica, sans-serif; font-weight:bolder;}
.lg_foot{
	height:80px;
	width:330px;
	padding: 6px 68px 0 68px;
}
.rd{
	color:#666;
	margin:9px 5px;
	padding-left:40px;
	margin-left:35px;
	font-size:16pt;
}

</style>
  </head>
  
  <body>
  <div class="lg">
<!-- LoginServlet -->
    <form action="LoginServlet" method="post">
    <div class="lg_top"></div>
    <div class="lg_main">
        <div class="lg_m_1">
        <!-- 隐藏参数，action表示当前操作为login -->
        <input type="hidden" name="action" value="login">
     
    	<table>
    	<tr><td>
    	<input type="text" name="username" required  class="ur" placeholder="请输入账号"></td></tr>
    	<tr><td>
    	<input type="password" name="password" required class="pw" placeholder="请输入密码"></td></tr>
    	<tr align="center">
    	<td>
    	<input type="radio" name="type" value="1" id="ptyg"  checked="checked">
    	<label for="ptyg">普通员工</label>
    	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="radio" name="type" value="0" id="gly" >
    	<label for="gly">管理员</label>
    	</td></tr>
   		</table>
   		   </div>
    </div>
    <div class="lg_foot">
    	<p align="center">
    	     <span style="color:red">  ${bugs} &nbsp;</span>
    	</p>
    	<p>
    	   <input type="submit" value="登录" class="bn">  
        </p>
    	
    	</div>
    </form>
    </div>
  </body>
</html>
