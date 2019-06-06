<%@ page language="java" import="java.util.*,beans.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'queryAll.jsp' starting page</title>
    
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
		function test(){
			var pageIndex=document.getElementById("pageIndex").value;
			if(parseInt(pageIndex)<=0 || parseInt(pageIndex)>${page.pageCount}){
				alert("输入不在范围内!");
				document.getElementById("pageIndex").value=${page.pageIndex};
			}else{
				window.location.href="${pageContext.request.contextPath }/queryAll?method=queryAll&pageIndex="+pageIndex;
			}	
		}
		
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
					if(data == '1'){
						$("#tr_"+id).remove();
						$("#msg").html("删除成功");
					}else{
						$("#msg").html("删除失败");
					}
				}
			});
		}
	</script>
  </head>
  
  <body>
  <jsp:include page="/top.jsp"></jsp:include>
    <hr>

  	<table border="1" width="50%" height="50%" >
  	<tr><th>序号</th><th>ID</th><th>用户名</th><th>密码</th><th>操作</th></tr>
  	<c:forEach items="${userList }" var="user" >
  		<tr id="tr_${user.id }">
  			<td>${user.id }</td>
  			<td>${user.userName }</td>
  			<td>${user.password }</td>
  			<td><td><a href="javascript:void(0);" onclick="deleteById(${user.id });">删除</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/updateUserPassword.jsp?id=${user.id }&userName=${user.userName }&password=${user.password}">修改</a></td></td>
  		</tr>
  		
  	</c:forEach>
  </table>
  <label style='color:red;font-size:15px'>${msg }</label>
  	共有${page.rowCount }条记录   当前第${page.pageIndex }页,共${page.pageCount }页  
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<c:choose>
  		<c:when test="${page.hasPre}">
	  		<a href="${pageContext.request.contextPath }/queryAll.action?method=queryAll&pageIndex=1">首页</a>&nbsp;&nbsp;
	  		<a href="${pageContext.request.contextPath }/queryAll.action?method=queryAll&pageIndex=${page.pageIndex-1}">上一页</a>&nbsp;&nbsp;
  		</c:when>
  		<c:otherwise>
  			首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
  		</c:otherwise>
  	</c:choose>
  	
  	<c:choose>
  		<c:when test="${page.hasNext}">
	  		<a href="${pageContext.request.contextPath }/queryAll.action?method=queryAll&pageIndex=${page.pageIndex+1}">下一页</a>&nbsp;&nbsp;
  			<a href="${pageContext.request.contextPath }/queryAll.action?method=queryAll&pageIndex=${page.pageCount}">尾页</a>
  		</c:when>
  		<c:otherwise>
  			下一页&nbsp;&nbsp;尾页
  		</c:otherwise>
  	</c:choose>
  	
  	<input type="text" size="2" id="pageIndex" value="${page.pageIndex }"/>
  	<input type="button" value="转到" onclick="test();"/><br>
  	<label style="font-size:20px" id="msg"></label>
  </body>
</html>
