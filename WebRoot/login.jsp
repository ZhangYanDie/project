<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#but").click(function(){
				$.ajax({
					url:"login.do",
					type:"post",
					data:{
						userName:$("#userName").val(),
						password:$("#password").val(),
						method:"login"
					},
					success:function(data){
						$("#msg").html(data);
					}
					
				});
			});
		});
	</script>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath }/login.do" method="post">
  <input type="hidden" name="method" value="login"/>
  	 用户名：<input type="text" id="userName" name="userName"/><br>
   	 密    码：<input type="text" id="password" name = "password"/><br>
   	 <input type="submit" id="but" value="登录"/>
   	 
  </form>
   	 <a href="regist.jsp">注册</a><br>
   	 <label style='font-size:20px' id="msg"></label>
   	 <img alt="" src="static/images/aaa.jpg">
  </body>
</html>
