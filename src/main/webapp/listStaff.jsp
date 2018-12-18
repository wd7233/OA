<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
	<table border="1" width="70%">
   		<tr>
   			<td>Id</td>
   			<td>姓名</td>
   			<td>性别</td>
   			<td>电话</td>
   			<td>权限</td>
   			<td>操作</td>
   		</tr>
   		<c:forEach items="${stafflist}" var="staff">
   		<tr>
   			<td>${staff.id }</td>
   			<td>${staff.name }</td>
   			<td>${staff.sex }</td>
   			<td>${staff.telephone }</td>
   			<td>${staff.role }</td>
   			<td><a href="<%=path %>/muserController/updateUserUI.do?id=${staff.id }">编辑</a>&nbsp;
   			<a href="<%=path %>/muserController/deleteUser.do?id=${staff.id }">删除</a></td>
   		</tr>
   		</c:forEach>
   </table>
  <!--  <a href="<%=path %>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
   
</body>
</html>