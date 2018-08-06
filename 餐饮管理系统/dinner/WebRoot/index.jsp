<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style2.css">
	
	<script type="text/javascript">
		function kong(id){
			window.location.href="pageMenuServlet?did="+id;
		}
		
		function man(id,state){
			window.location.href="formServlet?did="+id+"&fid="+state;
		}
		
		function showAdd(){
			alert("更多功能，敬请期待！");
		}
		
		function exit(){
			if(confirm("确定要退出吗？")){
				window.location.href="LoginServlet?action=exit"//?表示连接，action是控件名，exit是控件值
			}
		}
	</script>
	
	<style type="text/css">
		body,td,th{
			font-size:14px;
			line-height:20px;
		}
		body{
			background-image: url("images/login_bg3.jpg") ;
			font-size:14px;
		}
		.contain{
			width:1230px;
			text-align:left;
			background:#FEFEF6;
		}
		.tab{
			width:1230px;
			height:292px;
			padding:4px;
			border:1px solid #C3C3C3;
			text-align:center;
			margin:10px;
		}
		.tit{
			width:437px;
			text-align:center;
			display:block;
			background:#EBEBEB;
			line-height:20px;
			color:#FF0000;
		}
		.lg{
			width:1230px;
			height:800px;
			margin:100px auto;
		}
		.tb1{
			/*background-image: url("images/daohang2.jpg")*/
		}
		a{
			font-size:20px;
			color:#000;
		}
		span{
			font-family:"华文行楷";
			font-size:35px;
			border-bottom:5px solid #6F9;
			text-align:center;
			margin:0 auto;
		}
		.pct{
			width:50px;
			height:50px;
			border:5px solid #6F9;
			border-radius:10%;
		}
	</style>
  </head>
  
  <body>
    <center>
   	<div class="contain">
   		<table width="1230" height="110" border="0" cellpadding="0" cellspacing="0">
   			<tr>
   				<td>
   					<img src="images/banner.gif" width="1230" height="120" />
   				</td>
   			</tr>
   		</table>
   		<table width="1230" height="90" border="0" cellpadding="0" cellspacing="0" class="tb1" style="border-top:1px;">
   			<tr>
   				<td align="center">
   				<strong>
   					<c:choose>
   						<c:when test="${user.type eq 0}"><!-- 判断是否是管理员 -->
   							<a href="cuisine" class="a_demo">菜单管理</a>&nbsp;&nbsp;&nbsp;
   							<a href="DeskServlet" class="a_demo">餐桌管理</a>&nbsp;&nbsp;&nbsp;
   							<a href="javascript:showAdd()" class="a_demo">人员管理</a>&nbsp;&nbsp;&nbsp;
   							<a href="MenutypeServlet" class="a_demo">类型管理</a>&nbsp;&nbsp;&nbsp;
   							<a href="censue" class="a_demo">统计查询</a>&nbsp;&nbsp;&nbsp;
   							<a href="javascript:showAdd()" class="a_demo">更多功能</a>&nbsp;&nbsp;&nbsp;
   							<a href="javascript:exit()" class="a_demo">退出登录</a>&nbsp;&nbsp;&nbsp;
   						</c:when>
   						   
                               <c:otherwise>
                                      <a href="amanage?action=updatePwd&aid=${user.id }" class="a_demo">修改密码</a>&nbsp;&nbsp;&nbsp;
                                      <a href="javascript:exit()" class="a_demo">退出登录</a>
                                 
                               </c:otherwise>
   					</c:choose>
   				</strong>
   				</td>
   			</tr>
   		</table>
   		<table width="1230" height="90" border="0" cellpadding="5" cellspacing="5">
   			<tr >
   				<td colspan="5" align="center" >	
   					<span >招牌菜式</span>
   				</td>
   			</tr>
   			<tr>
   				<td class="pct"> <img src="images/15.jpg" /> </td>
   				<td class="pct"> <img src="images/18.jpg" /> </td>
   				<td class="pct"> <img src="images/16.jpg" /> </td>
   				<td class="pct"> <img src="images/22.jpg" /> </td>
   				<td class="pct"> <img src="images/4.jpg" /> </td>
   			</tr>
   		</table> 
   		
   		<table width="1230" height="60" border="0" cellpadding="0" cellspacing="0" style="border-top:1px;" class="tb1">
   			<tr>
   				  <td align="center">
                     <a href="menu?type=1">查询全部餐桌</a>
                     <a href="menu?type=2" >查询在餐餐桌</a>
                     <a href="menu?type=3" >查询空位餐桌</a>
                </td>
   			</tr>
   		</table>
   		<table width="1230" height="750" border="0" cellpadding="0" cellspacing="0" style="border-top:1px;" class="tb1">
   			
		  <c:forEach items="${page.data }" var="desk" varStatus="status">
		  <tr>
         <td width="300" height="305" align="center" valign="top">
		<c:choose>
		      <c:when test="${desk.dstatus eq 0 }">
		               <div class="tab">
		                       <a href="javascript:kong(${desk.did })">
		                              <img src="images/kongzuo.jpg" width="300" height="285">
		                       </a>      
		               </div>        
		      </c:when>
		      
		      <c:otherwise>
		              <div class="tab">
		                     <a href="javascript:man(${desk.did },${desk.dstatus })">
		                           <img alt="" src="images/manzuo.jpg" width="300" height="285">
		                     </a>     
		              </div>
		      </c:otherwise>
		
		</c:choose>
                 
        <div class="tit" >${desk.dname }</div> 
     </td> 
         <c:if test="${page.count%2 eq 0}">
              <tr></tr>
         </c:if>
      </tr>
   </c:forEach>
   			
   		</table>
   		<table width="1230" height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px;margin:5% 0 0 0;">
   			<tr>
   				<td align="center">
   					   <a href="menu?pageNum=${page.first}&type=${type}" class="a_demo">首页</a>&nbsp;&nbsp;&nbsp;
                   <a href="menu?pageNum=${page.up}&type=${type}" class="a_demo">上一页</a>&nbsp;&nbsp;&nbsp;
                                                   当前第${page.pageNum }页,共计${page.count }页&nbsp;&nbsp;&nbsp;
                   <a href="menu?pageNum=${page.down}&type=${type}" class="a_demo">下一页</a>&nbsp;&nbsp;&nbsp;
                   <a href="menu?pageNum=${page.last}&type=${type}" class="a_demo">尾页</a>&nbsp;&nbsp;&nbsp; 
          </td>
   			</tr>
   		</table>
   	</div>
   </center>
  </body>
</html>
