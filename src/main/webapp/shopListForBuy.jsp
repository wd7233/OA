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

		<br/><br/>
			 关 键 字： <input id="keyWord" type="text" name="keyWord" /> &nbsp;&nbsp;&nbsp;&nbsp;
			  负 责 人：		<select class = "select" id="staff"  name="staffName">
							<option value = "-1">全部</option>
						<c:forEach items="${staffList}" var="staff">
						<option value = "${staff.id}">${staff.name}</option>
						</c:forEach>
					</select>
								&nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 &nbsp;&nbsp;&nbsp;&nbsp;
			 	 <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
			 	<br/>
			 		<br/>
		</form>	 		
		<table style="text-align: center" border="1" width="70%">
			<tr height="60px">
				<td  style=" width:20px ">Id</td>
				<td>店铺编号</td>
				<td>店铺名称</td>
				<td>账号</td>
				<td>负责人</td>
			</tr>
			<c:forEach items="${shoplist}" var="shop" varStatus="status">
				<tr height="50px">
					<td>${status.index+1 }</td>
					<td><label id="shopNumber${status.index+1}">${shop.number}</label></td>
					<td>${shop.name }</td>
					<td>${shop.account }</td>
					<td>${shop.staffName }</td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>