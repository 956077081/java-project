<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dishes.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function demand(id,state){
			if(state==0){
				alert("还未点菜，无法查询！")
			}else{
				window.location.href="formServlet?did="+id+"&fid="+state;
			}
			
		}
		function exit(){
			if(confirm("你确定要退出吗")){
			window.location.href="LoginServlet?action=exit";
			}
	}
	
			//重置按钮
			function resetd(){
				document.getElementById("type").options[0].selected=true;
				document.getElementById("p1").value="";
				document.getElementById("p2").value="";
				document.getElementById("name").value="";
			}
	</script>
	<style >
	 body{ background-image: url("images/back1.jpg"); }
		a{ text-decoration: none; }
		a:hover{color:#ff0080;}
		.div1{width: 960px ; margin:auto; background-color: #baffc4 ;}
		#fh{padding-left: 50px;}
		#yd{padding-left: 300px;}
		#ex{padding-left: 300px;}
		#ceng{ text-align: center;}
		.price{ width:50px;}
		.sp1{padding-left:25px;}
		.prod_list{width: 960px ; height: 800px; }
		.li1{
	list-style-type:none;
	float:left;position:relative;
	width:230px;
	height:200px;
	padding:30px;
	text-align: center;
	background-color: #baffc4;
}
.prod_list ul li img{
	width:220px;
	height:168px;
	border:1px solid #999999;
	border-radius: 12px;
}
.prod_list ul li p{
	font-size:14px;
	line-height:20px;
	margin:0;	
}
h1 { font: 250% YouYuan ; margin:auto; line-height:40px;text-align:center;color: #D43F3A; }		
form { margin:1em 0; padding:auto; background:#e1f6c3; }		
		
		
		
	</style>
  </head>
  
  <body>

	<div  class="div1">
		<div class="div1">
			<table width="960" height="120" border="0" cellpadding="0" cellspacing="0">
			<tr>
    	<td><img src="images/banner.gif" width="960" height="120" /></td>
  		</tr>
  		</table>
			<h1 align="center">欢迎点菜</h1>
			<br>
			<p align="center">
				<span style="color:red">${bug }</span>
			</p>
			<a id="fh" href="menu">返回主菜单</a> <a id="yd"
				href="javascript:demand(${did},${dstatus })">查看已点菜单 </a> <a id="ex"
				href="javascript:exit()">退出登录</a>
		</div>
		<div id="ceng">
			<form action="pageMenuServlet" method="post">
				<input type="hidden" name="deid" value="${did}"> 
				菜的价格:
				<input class="price" type="number" name="price1" id="p1" value="${mp.price1==0?'':mp.price1 }"  min="0" max="10000"> 
				元--
				<input type="number" class="price" name="price2" id="p2" value="${mp.price2==0?'':mp.price2 }"   min="1" max="10000">
				元 
				<span class="sp1"></span>
				<span class="sp1"></span>
				菜的名字:<input type="text" size="8" name="cname" id="name" value="${mp.cname }"> 
				<span class="sp1"></span>
				<select name="ctype" id="type">
					<option value="" ${mp.ctype==""?'selected':""}>--查询全部类型--</option>
					<c:forEach items="${mplist}" var="list">
						<option value="${list.mtype}"
							${mp.ctype==list.mtype?'selected':""}>${list.mtype}</option>
					</c:forEach>
				</select> 
				<input type="submit" value="查询"> <input type="button" value="重置" onclick="resetd()">
			</form>
		</div>
		<div class="prod_list">
			<ul>
				<c:forEach items="${page.data}" var="clist">
					<li class="li1"><img alt="" src="images/${clist.cimg}">
						<p>${clist.cname }</p>
						<p>
							<span>价格:</span>${clist.cprice }</p>
						<p>
							<a href="pageMenuServlet?cid=${clist.cid }&pageNum=${page.pageNum}&did=${did}&cname=${mp.cname}&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}&action=click">点这道菜</a>
						</p></li>
				</c:forEach>
			</ul>
		</div>
		<div  class="div1">
			<p align="center">
				<a
					href="pageMenuServlet?pageNum=${page.first}&deid=${did}&cname=${mp.cname}&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}">首页</a>
				<a
					href="pageMenuServlet?pageNum=${page.up}&deid=${did}&cname=${mp.cname}&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}">上一页</a>
				当前第${page.pageNum}页，共计${page.count}页 <a
					href="pageMenuServlet?pageNum=${page.down}&deid=${did}&cname=${mp.cname}&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}">下一页</a>
				<a
					href="pageMenuServlet?pageNum=${page.last}&deid=${did}&cname=${mp.cname}&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}">末页</a>

			</p>
		</div>
	</div>
</body>
</html>
