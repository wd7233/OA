<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	border-bottom: 2px solid #FFFFFF;
	border-top: 2px solid #FFFFFF;
	border-left: 2px solid #FFFFFF;
	border-right: 2px solid #FFFFFF;
}
td{
	-o-text-overflow:ellipsis;
	text-overflow:ellipsis;
	overflow:hidden;
	white-space:nowrap;
	width:100%;
}
#addDiv {
	z-index: 9999;
	width:200px;
	height: 100px;
	background: #ADD8E6;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
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
		<div id="addDiv">
				<br /> 提现金额 ： <input id="amount" type="text" value="" name="amount"  style="width: 80px"/>
			<input type="hidden" value="" id="shopNumber">
			<br /><br /><button onclick="save()"  style="width: 50px;height: 25px">保 存</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="closeDiv()"  style="width: 50px;height: 25px">取 消</button>
		</div>


		<table style="text-align: center" border="1" width="70%">
			<tr height="60px"  style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td  style=" width:20px ">Id</td>
				<td>店铺编号</td>
				<td>店铺名称</td>
				<td>负责人</td>
				<td>金额</td>
				<td>录入时间</td>
				<td>是否提取</td>
				<td>提取时间</td>
			</tr>
			<c:forEach items="${withdrawNameList}" var="withdrawName" varStatus="status">
				<c:if test="${ withdrawName.isWithdraw ==0 }">
				<tr bgcolor = "#f8cacd" height="50px"></c:if>
				<c:if test="${ withdrawName.isWithdraw ==1 }">
					<tr bgcolor = "#9cdbe0" height="50px"></c:if>
					<td>${status.index+1 }</td>
					<td>${withdrawName.shopNumber}</td>
					<td>${withdrawName.shopName}</td>
					<td>${withdrawName.staffName}</td>
					<td>${withdrawName.amount}</td>
					<td><fmt:formatDate value="${withdrawName.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${ withdrawName.isWithdraw ==0 }">未提取</c:if>
						<c:if test="${ withdrawName.isWithdraw ==1 }">已提取</c:if></td>
					<td><c:if test="${ withdrawName.isWithdraw ==1 }"><fmt:formatDate value="${withdrawName.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>