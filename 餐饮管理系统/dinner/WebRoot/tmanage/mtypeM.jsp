<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mtypeM.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/style2.css" />
	<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
	<style>
		.context{border-top:dashed 1px #ccc;padding-top:20px;margin:5px;}
		.context{text-align:center;}
		.context .left{ float:left;}
		.context .center{ display:inline;}
		.context .right{ float:right;}
		body { margin:0; padding:0; background:#f1f1f1; font:70% Arial, Helvetica, sans-serif; color:#555; line-height:150%; text-align:left; }
		a { text-decoration:none; color:#057fac; }
		a:hover { text-decoration:none; color:#f1590e; }
		h1 { font: 400% KaiTi ; margin:auto; line-height:40px;text-align:center }
		#container { margin:0 auto; width:880px; background:#fff; padding-bottom:20px; }
		#content { margin-top:0px; height:77%; position: relative; }
		p.sig { margin:0 auto; width:680px; padding:1em 0; }
		form { margin:1em 0; padding:.2em 20px; background:#eee; }
	</style>
	<script type="text/javascript">
		function useT(id,pageno){
			if(confirm("确定要启用该类型吗?")){
				window.location.href="MenutypeServlet?action=use&mid="+id+"&pageNum="+pageno;
			}
		}
		
		function banT(id,pageno,state){
			if(state != 0){
				alert("该类型正被菜品使用，无法停用！")
			}else{
				if(confirm("确定要停用该类型吗?")){
				window.location.href="MenutypeServlet?action=ban&mid="+id+"&pageNum="+pageno;
				}
			}
		}
		
		function upType(id,txt,oldtype,pageno){
			if(confirm("是否确定修改该类型名？")){
				var type = txt.value;
				if(type==null||type==""){
					alert("类型名不能为空！");
				}else{
					window.location.href="MenutypeServlet?action=update&mid="+id+"&pageNum="+pageno+"&mtype="+type;
				}
			}else{
				txt.value = oldtype;
			}
		}
		function showadd(){
			alert("更多功能敬请期待");
		}
	</script>

  </head>
  
  <body>
  	<div id="container">
  	<img src="images/banner.gif" width="880px" height="120" />
    <h1>菜品类型管理</h1>
    <div id="content" >
    	<div class="context">
    	<div class="left"><a href="MenutypeServlet?action=add" class="a_demo">新增菜品类型</a></div>
			<div class="center">
				<strong>
	   			<c:choose>
	  			<c:when test="${user.type eq 0 }">
				<a href="cuisine" class="a_demo">菜品管理</a> &nbsp;&nbsp;&nbsp;
				<a href="amanage" class="a_demo">账号管理</a> &nbsp;&nbsp;&nbsp;
				<a href="MenutypeServlet" class="a_demo">餐桌管理</a> &nbsp;&nbsp;&nbsp;
				<a href="incomeServlet" class="a_demo">统计查询</a> &nbsp;&nbsp;&nbsp;
				<a href="javascript:showadd()" class="a_demo">更多功能</a> &nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
				<a href="amanage?action=updatePwd&aid=${user.id }" class="a_demo">修改密码</a> &nbsp;&nbsp;&nbsp;
				</c:otherwise>
	    		</c:choose>
	    		</strong>
			</div>
			<div class="right"><a href="menu" class="a_demo">返回主页面 </a></div>
			</div>
    
    <table border="1" style="margin-bottom: 90px;">
    <thead>
    	<tr>
    	<th>类型ID</th>
    	<th>菜品类型</th>
    	<th>类型状态</th>
    	<th>操作</th>
    	</tr>
    </thead>
    <c:forEach items="${page.data }" var="mtype">
    <tr>
    <td>${mtype.mid }</td>
    <td><input type="text" name="mtype" required="required" value="${mtype.mtype }" size="6" onchange=" upType('${mtype.mid }',this,'${mtype.mtype }','${page.pageNum }')"></td>
    <td>${mtype.mexist eq 1?'在用':'停用' }</td>
    <td>
    	<c:choose>
    		<c:when test="${mtype.mexist eq 1}">
    			<a href="javascript:banT(${mtype.mid },${page.pageNum },${mtype.status } )" class="a_demo">停用该类型</a>
    		</c:when>
    		<c:otherwise>
	  			<a href="javascript:useT(${mtype.mid },${page.pageNum })" class="a_demo">启用该类型</a>
	  		</c:otherwise>
    	</c:choose>	
    </td>
    </tr>
    </c:forEach>
    </table>
    
    <table  height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px #C3C3C3 solid; position:fixed;bottom:0px; width: 880;">
  		<tr>
  		<td align="center" style="text-align:center;">
	  		<a href="MenutypeServlet?pageNum=${page.first }">首页</a>&nbsp;&nbsp;&nbsp;
    		<a href="MenutypeServlet?pageNum=${page.up }">上一页</a>&nbsp;&nbsp;&nbsp;
    		当前第${page.pageNum }页，共计${page.count }页&nbsp;&nbsp;&nbsp;
    		<a href="MenutypeServlet?pageNum=${page.down }">下一页</a>&nbsp;&nbsp;&nbsp;
    		<a href="MenutypeServlet?pageNum=${page.last }">末页</a>&nbsp;&nbsp;&nbsp;
    		
	  </td>
	  </tr> 	
    </table>
	  </div>
  	</div>
  </body>
</html>
