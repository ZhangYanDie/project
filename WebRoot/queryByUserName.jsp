<%@ page language="java" import="java.util.*,beans.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询某个用户</title>
    
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
		function deleteById(id){
			$.ajax({
				url:"deleteById.action",
				type:"post",
				data:{
					method:"deleteById",
					id:id,
					pageIndex:${page.pageIndex}
				},
				success:function(data){
					if(data=='1'){
						$("tr_"+id).remove();
						$("msg").html("删除成功");
					}else{
						$("msg").html("删除失败");
					}
				}
			});
		}
		
		function query(){
			var userName = doument.getElementById("userName").value;
			window.location.href="queryByUserName&method=queryByUserName&userName="+userName;
		}
	</script>
  </head>
  
  <body>
   <jsp:include page="/top.jsp"></jsp:include>
    <hr>
  	  <input type="text" id="userName"/>
  	  <button id="but" onclick="query();">查询</button><br><br>
  	<table border="1" width="50%" height="50%">
  	<tr><th>序号</th><th>ID</th><th>用户名</th><th>密码</th><th>操作</th></tr>
     <c:if test="${userList!=null }">
     	<c:if test="${userList.size()>0 }">
     		<c:forEach var="user" items="userList">
     			<tr id="tr_${user.id} }">
     				<td>user.id</td>
     				<td>user.userName</td>
     				<td>user.password</td>
     				<td><td><a href="javascript:void(0);" onclick="deleteById(${user.id });">删除</a>||<a href="${pageContext.request.contextPath }/updateUserPassword.jsp?id=${user.id }&userName=${user.userName}&password=${user.password}">修改</a></td></td>
     			</tr>
     		</c:forEach>
     	</c:if>
     </c:if>
     <tr>
    		<td colspan='3'></td>
    	</tr>
    </table>
    <label style="font-size:20px" id="msg"></label>
  </body>
</html>
