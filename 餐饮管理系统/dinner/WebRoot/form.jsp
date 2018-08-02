<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored ="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'form.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	//修改数量
	function updateNum(id,txt,oldNum,deid){
		if(confirm("确定修改数量吗？")){
			//获取数量文本框的值
			var num=txt.value;
			if(!isNaN(num))
				/* 
				修改订单中订单项菜品数量 action指令为updateItem 
				 传递参数菜品id（订单项Map的key），菜品数量 isum
				 */
				window.location.href="formServlet?cid="+id+"&num="+num+"&action=update&did="+deid;
		}else{
			txt.value = oldNum;
		}
	}
	
	//删除菜品
	function deleteItem(cid,deid){
		if(confirm("确定要删除吗?")){
			/* 
			删除订单中订单项 action指令为deleteItem
			传递参数菜品id（订单项Map的key）
			*/		
			window.location.href="formServlet?cid="+cid+"&did="+deid+"&action=deleteItem";
		}
	}
	function exit(){
			if(confirm("你确定要退出吗")){
			window.location.href="LoginServlet?action=exit";
			}
	}
	
	
	//结算订单
	function pay(id){		
		var sum = document.form1.incom.value.trim();
		if(sum==null||sum==""){
		alert("请输入实收金额");
		}else{	
		if(confirm("实收"+sum+"元，确定结账吗?")){
			//结账 action指令为pay 传递参数订单id 	
			window.location.href="formServlet?did="+id+"&action=sellte&incom="+sum;
			}	
		}
	}
	
	//取消订单
	function cancle(id){
		if(confirm("确定取消订单?")){	
			//取消或免单 action指令为cancel 传递参数订单id 		
			window.location.href="formServlet?did="+id+"&action=cancel";
		}
	}
	
	</script>
	<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
	<style>
	#z1{ padding-left: 50px; }
	#z2{ padding-left: 600px; }
	#a1{ padding-left: 270px; }
	#a2{ padding-left: 80px; }
	#a3{ padding-left: 80px; }
		.price{width: 40px }
		.price2{width: 70px }
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
		#content { margin-top:0px; height:75%; ;position: relative; }
		p.sig { margin:0 auto; width:680px; padding:1em 0; }
		form { margin:1em 0; padding:.2em 20px; background:#eee; }
	</style>
  </head>
  
  <body>
  
  <div id="container">
 
  <img src="images/banner.gif" width="880px" height="120" />
         <h2 align="center">----点菜信息----</h2>  
         <span id ="z1">这是<span style="color:red">${dname}</span>的订单</span>
            <a id ="z2" href="javascript:exit()">退出登录</a>
        <p>     
            <a id="a1" href="menu">返回主菜单</a>
    		<a id="a2" href="pageMenuServlet?did=${orderFrom.deid}">返回点菜</a>
            <a id="a3" href="javascript:cancle(${orderFrom.deid})">取消订单</a> 
    	<!-- 返回首页 action指令为returnIndex -->
    	 
    	<!-- 返回点菜 action指令为returnDishes 传递参数为订单id -->
    	</p>
    
    
    <form name="form1" action="SettleServlet" method="post">
         <input type="hidden" name="action" value="sellte">
         <input type="hidden" name="did" value="${orderForm.did}">
    	    <table border="1">
    		   <tr>
    			    <th>菜名</th>
    			    <th>价格</th>
    			    <th>数量</th>
    			    <th>合计</th>
    			    <th>操作</th>
    		   </tr>
    		<!-- 遍历订单中的订单项 -->
  		<c:forEach items="${orderFrom.items}" var="item">
   		   <tr>
    		 	<td>${item.value.cuisine.cname }</td>
    		 	<td>${item.value.cuisine.cprice }</td>
    		 	<td >
    		 	   <input class="price" type="number" min="1" 
    		 	   value="${item.value.inum }" 
    		 	   onchange="updateNum(${item.key },this,${item.value.inum},${orderFrom.deid} )">
    		 	</td>
    		 	<td>
    		 	    <fmt:formatNumber type="number" 
    		 	      value="${item.value.sum }" maxFractionDigits="2">
    		 	    </fmt:formatNumber>   
    		    </td>
    		    
 		            <td>
 		               <a href="javascript:deleteItem(${item.key},${orderFrom.deid})">删除</a>
 		           </td>
   		    </tr>
	  </c:forEach>
			
			<tr>
			    <td colspan="2" align="right">总计:
			       <fmt:formatNumber type="number" value="${orderFrom.total }" maxFractionDigits="2">
			       </fmt:formatNumber>
			    </td>
			    <td colspan="2" align="right">实收 
			        <input class="price2" type="number" size="2"  name="incom" 
			        required="required"  min="0" step="0.01">
			    </td>
			    <td>
			         <a href="javascript:pay(${orderFrom.deid})"> 结账</a> 
			    </td>
			</tr>
    	</table>
   </form>
</div>

</body>
</html>
