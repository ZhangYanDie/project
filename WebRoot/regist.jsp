<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
			$("button").click(function(){
				$.ajax({
					url:"regist.do",
					type:"post",
					data:$("#form1").serialize(),
					success:function(data){
						$("#msg").html(data);
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath }/regist.do" method="post" id="form1">
  <input type="hidden" name="method" value="regist"/>
  	用户名&nbsp<input type="text" name="userName"/><br>
          密&nbsp&nbsp&nbsp码&nbsp<input type="text" name="password"/><br>
    <input type="submit" value="注册"/>
  </form>
  <a href="login.jsp">登录</a><br>
  <label style='font-size:20px' id="msg"></label>
  
  </body>
</html>
