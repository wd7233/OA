<!-- 客服 -->
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
	background: #f8cacd;
}
table tr:nth-child(even) {
	background: #9cdbe0;
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
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺列表</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
</head>
<script>
	function update(cnt) {
		$.ajax({
            type: "POST",
            url: "<%=path%>/loginController/updateNums.do",
			data : {
				shopNumber : $("#shopNumber"+cnt).html(),
				num : $("#num"+cnt).val(),
				staffId : $("#oriLoanerIdType"+cnt).val()
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('保存成功');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function exportGoods() {
	var goodType = $("#goodType").val();
	var url = "<%=path%>/loginController/downLoadGoods.do";
	window.open(url+"?goodType="+goodType);
	}
</script>
<body>
	<center>
	<form method="post" action="<%=path%>/loginController/getShop.do">
	<br/>
	 商品类型 ：<select style="width: 150px; height: 30px;" class = "select" id="goodType"  name="goodType">
						<c:forEach items="${goodTypeList}" var="goodType">
						<option value="${goodType.id}">${goodType.type}</option>
						</c:forEach>
					</select>
		<button style="width: 200px; height: 30px;" onclick="exportGoods()">获取商品</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button  onclick="window.open('<%=path%>/scalpingController/getOrder.do')" style="width: 200px; height: 50px;">刷单记录</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button  onclick="window.open('<%=path%>/specialorderController/getSpecialorder.do')" style="width: 200px; height: 50px;">单品订单</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.open('<%=path%>/loginController/loadShops.do')" style="width: 200px; height: 50px;">淘单单接入</button>
		<br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.open('<%=path%>/shopController/goodsLower.do')" style="width: 200px; height: 50px;">店铺下架</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button  onclick="window.open('<%=path%>/orderController/getOrderListByUser.do')" style="width: 200px; height: 50px;">订单查询</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button  onclick="window.open('<%=path%>/shopController/getCardInfo.do')" style="width: 200px; height: 50px;">卡号详情</button>
		<br/><br/>
			 关 键 字： <input id="keyWord" type="text" name="keyWord" /> &nbsp;&nbsp;&nbsp;&nbsp;
			 &nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
						 负 责 人：	<select class = "select" id="staff"  name="staffName">
						 <option value = "-1">全部</option>
						<c:forEach items="${staffList}" var="staff">
						<option value = "${staff.id}">${staff.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
			 	<br/>
			 		<br/>
		</form>	 		
		<table style="text-align: center;" border="1" width="70%">
			<tr height="60px" style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td style=" width:30px ">序号</td>
				<td>店铺编号</td>
				<td>店铺名称</td>
				<td>账号</td>
				<td>商品数量</td>
				<td>负责人</td>
				<td>操 作</td>
			</tr>
			<c:forEach items="${shoplist}" var="shop" varStatus="status">
				<tr height="50px" style="text-align: center;">
					<td>${status.index+1 }</td>
					<td><label id="shopNumber${status.index+1}">${shop.number}</label></td>
					<td>${shop.name }</td>
					<td>${shop.account }</td>
					<td><input style="height: 20px" type="text"
						id="num${status.index+1}" value="${shop.goodsnum }" /></td>
					<td>
					<select class = "select" id="oriLoanerIdType${status.index+1}"  name="staffName${status.index+1}">
							<option selected  = "selected" value = "${shop.staffId}">${shop.staffName }</option>
						<c:forEach items="${staffList}" var="staff">
						<c:if  test="${shop.staffId!=staff.id }">
						<option value = "${staff.id}">${staff.name}</option>
						</c:if>
						</c:forEach>
					</select></td>
					<td><button class="button" id="update"
						onclick="update(${status.index+1})" style="width: 60px; height: 30px;background:none;border:2px solid #FFFFFF;"><font style = "font-size: 17px;color: #054C84;" ><b>保 存</b></font></button></td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>