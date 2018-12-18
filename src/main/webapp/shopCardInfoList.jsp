<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE html >
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-1.11.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.min.js"
	charset="utf-8"></script>
<html>
<style>
.button {
	width: 80px;
	height: 20px;
}

.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
}

table tr:nth-child(odd) {
	background: #ccc;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
	white-space: nowrap;
	font-size: 14px;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
/**	text-shadow: 0 1px 2px rgba(0, 0, 0, .3);**/
	text-align: center;
	vertical-align: center;
	table-layout: fixed;
	word-break: break-all; 
	word-wrap: break-word; 
}
td{
	-o-text-overflow:ellipsis;
	text-overflow:ellipsis;
	overflow:hidden;
	white-space:nowrap;
	width:100%;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺列表</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
</head>
<script>
</script>
<body>
	<center>
	<form method="post" action="<%=path%>/shopController/getShop.do">

		<table style="text-align: center" border="1" width="70%">
			<tr height="60px">
				<td  style=" width:20px ">Id</td>
				<td>店铺名稱</td>
				<td>开户人</td>
				<td>卡号</td>
				<td>身份证</td>
				<td>开户地址</td>
				<td>联系方式</td>
			</tr>
			<c:forEach items="${cardlist}" var="card" varStatus="status">
				<tr height="50px">
					<td>${status.index+1 }</td>
					<td><label>${card.shopName}</label></td>
					<td>${card.name }</td>
					<td>${card.cardno }</td>
					<td>${card.userNo }</td>
					<td>${card.cardLocation }</td>
					<td>${card.cardPhone }</td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>