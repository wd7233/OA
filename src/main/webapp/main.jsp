<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en" >

<head>
<meta charset="UTF-8">
<title>Side Menu</title>

<!--默认样式-->
<link rel="stylesheet" href="<%=path %>/css/reset.min.css">

<!--图标样式-->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css'>

<!--主要样式-->
<link rel="stylesheet" href="<%=path %>/css/style.css">


</head>

<body>

<div class="page">
	<!--左侧导航-->
	<div class="navbar">
		<div class="user">
			<div class="user__pic"></div>
			<div class="user__name">Jane Santigold</div>
		</div>
		<div class="bar">
			<div class="options">
			<c:forEach items="${menuList}" var="menuP" varStatus="status">
				<ul>
					<li class="active">${menuP.name}</li>
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
</div>



</body>

</html>
