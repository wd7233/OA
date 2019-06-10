<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-1.11.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.min.js"
	charset="utf-8"></script>
<script src="<%=basePath%>/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ui.datepicker-zh-CN.js" charset="gb2312"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui-timepicker-zh-CN.js"></script>
<link href="<%=basePath%>/css/jquery-ui-timepicker-addon.css" rel="stylesheet"  />
<link rel="icon" href="<%=basePath%>/image/log.png" type="image/x-icon" />
<link type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css" rel="stylesheet" />

<!DOCTYPE html>
<html lang="en" >
<head>
<meta charset="UTF-8">
<style>
.div-right{height:100vh;border: 1px solid red;} 
</style>
<title>Side Menu</title>

<!--默认样式-->
<link rel="stylesheet" href="<%=path %>/css/reset.min.css">

<!--图标样式-->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css'>

<!--主要样式-->
<link rel="stylesheet" href="<%=path %>/css/style.css">


</head>
<script>

function hideMenu(index)
{
	$("#menuUl"+index).toggle("slow",function(){ $("#menuT").show();});
}
</script>
<body>

<div class="page">
	<!--左侧导航-->
	<div class="navbar">
		<div class="user">
			<div class="user__pic"></div>&nbsp;&nbsp;&nbsp;
			<div class="user__name">${user.name}</div>
		</div>
		<div class="bar">
			<div class="options">
			<c:forEach items="${menuList}" var="menuP" varStatus="status">
				<ul  onclick = "hideMenu(${status.index+1 })" id ="menuUl${status.index+1}">
					<li class="active" id = "menuT" >${menuP.name}</li>
					<c:forEach items="${menuP.menus}" var="menu" varStatus="status">
					<li  class="options__division" >${menu.name}</li>
					</c:forEach>
				</ul>
			</c:forEach>
			</div>
			<div class="settings">
				<ul>
					<li class="acc-settings"><i class="fas fa-cog"></i> Account Settings</li>
					<li><i class="fas fa-sign-out-alt"></i> Logout</li>
				</ul>
			</div>
		</div>
	</div>
	<div class ="div_right" id = "showDiv" >
	<%@ include file="./scalpingOrder.jsp" %>  
	</div>
</div>



</body>

</html>
