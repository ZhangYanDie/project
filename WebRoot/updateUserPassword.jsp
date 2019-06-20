<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更新用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <jsp:include page="/top.jsp"></jsp:include>
    <hr>
    <form action='${pageContext.request.contextPath }/updateUserPassword.action' method='get'>
		<input type='hidden' name='method' value='updateUserPassword'/><br>
		<input type='hidden' name='id' value="<%=request.getParameter("id")%>"/><br>
		用户名:<input type='text' name='userName' value='<%=request.getParameter("userName")%>'/><br>
		密码:<input type='text' name='password' value='<%=request.getParameter("password")%>'/><br>
		<input type='submit' value='确定修改'/><br>
		</form>
  </body>
</html>
