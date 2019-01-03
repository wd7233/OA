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

	function getAmount(name,id){
		if(confirm('确定要设置为已提取吗？')){
			$.ajax({
				type: "POST",
				url: "<%=path%>/amountController/setWithdraw.do",
				data : {
					name:name
				},
				dataType : 'text',
				async : false,
				success : function(data) {
					alert('设置成功');
					$("#amount"+id).html("");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error ：" + textStatus);
				}
			});

			return true;
		}else{
			return false;
		}
	}

    function getAmountAll(){
        if(confirm('确定要全部设置为已提取吗？')){
            $.ajax({
                type: "POST",
                url: "<%=path%>/amountController/setWithdrawAll.do",
                dataType : 'text',
                async : false,
                success : function(data) {
                    alert('设置成功');
                    var tableId = document.getElementById("tab");
                    for (var i = 1; i < tableId.rows.length; i++) {
                        $("#amount"+i).html("");
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("error ：" + textStatus);
                }
            });

            return true;
        }else{
            return false;
        }
    }

	function userNoList(name){
		name=encodeURI(encodeURI(name))
		window.open('<%=path%>/amountController/userNoList.do?userName='+name);
	}

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

        <font style="font-size: 20px">可提金额 ： </font><font style="font-size: 40px" color="red"><label id="amountSum"></label></font>元
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="getAmountAll()" style="font-size: 20px">一键提现</button><br/><br/>
		<table style="text-align: center" border="1" width="70%" id="tab">
			<tr height="60px">
				<td  style=" width:20px ">Id</td>
				<td>姓名</td>
				<td>开户行</td>
				<td>卡号</td>
				<td>预留手机号</td>
				<td>密码</td>
				<td>未提现金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${cardAmountList}" var="cardAmount" varStatus="status">
				<tr height="50px">
					<td>${status.index+1 }</td>
					<td>${cardAmount.name}</td>
					<td>${cardAmount.cardLocation}</td>
					<td>${cardAmount.cardno}</td>
					<td>${cardAmount.cardPhone}</td>
					<td>${cardAmount.cardPwd}</td>
					<td id="amount${status.index+1 }" name="amount">${cardAmount.amount}</td>
					<td><button onclick="getAmount('${cardAmount.name}','${status.index+1}')">提取标记</button>
						<button onclick="userNoList('${cardAmount.name}')">详 情</button></td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
<script>

$(function() {
    var tableId = document.getElementById("tab");
    var amount = "0";
    for (var i = 1; i < tableId.rows.length; i++) {
        if (tableId.rows[i].cells[6].innerHTML != null & tableId.rows[i].cells[6].innerHTML != ""){
            amount = parseFloat(amount) + parseFloat(tableId.rows[i].cells[6].innerHTML);
        }
    }
    $("#amountSum").html(amount);
});

</script>

</html>