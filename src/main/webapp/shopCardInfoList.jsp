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

td {
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	width: 100%;
}
#addDiv {
	z-index: 9999;
	width:220px;
	height: 180px;
	background: #FFEC8B	;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
	opacity: 0.9;
}



</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺列表</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
</head>
<script>

	function addAmount(shopNumber,cnt,shopName){
		$("#addDiv").show();
		$("#addDiv").css("top",(300+(cnt-1)*30)+"px");
		$("#shopNumber").val(shopNumber);
		$("#shopNameAdd").html(shopName);
	}

	function save() {
		var amount = $("#amount").val();
		var reg = /^[1-9]\d*00$/;
		if (!amount.match(reg)){
			alert("必须填写100的整数倍");
			return;
		}
		var shopNumber = $("#shopNumber").val();
		$.ajax({
			type: "POST",
			url: "<%=path%>/shopController/saveAmount.do",
			data : {
				amount:amount,
				shopNumber:shopNumber
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('保存成功');
				$("#addDiv").hide();
				$("#amount").val("");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	
	function closeDiv() {
		$("#amount").val("");
		$("#addDiv").hide();
	}
</script>
<body>
	<center>
		<div id="addDiv">
			<br /><br />店铺名称 ：
			<font style = "font-size: 17px;color: black;" ><b><label id = "shopNameAdd"></label></b></font>
			<br /><br />提现金额 ： <input id="amount" type="text" value="" name="amount"  style="width: 90px"/>
			<input type="hidden" value="" id="shopNumber">
			<br /><br /><button onclick="save()"  style="width: 50px;height: 25px">保 存</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="closeDiv()"  style="width: 50px;height: 25px">取 消</button>
		</div>


		<table style="text-align: center" border="1" width="70%">
			<tr  height="60px" style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td  style=" width:20px ">Id</td>
				<td>店铺名称</td>
				<td>开户人</td>
				<td>卡号</td>
				<td>身份证</td>
				<td>开户地址</td>
				<td>联系方式</td>
				<td>操作</td>
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
					<td><button style="width: 60px; height: 30px;background:none;border:2px solid #FFFFFF;" 
						onclick="addAmount('${card.shopNumber }','${status.index+1 }','${card.shopName }')"><font style = "font-size: 17px;color: #054C84;" ><b>提 现</b></font></button></td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>