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
<script src="<%=basePath%>/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery.ui.datepicker-zh-CN.js" charset="gb2312"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-ui-timepicker-zh-CN.js"></script>
<link href="<%=basePath%>/css/jquery-ui-timepicker-addon.css"
	rel="stylesheet" />
<link rel="icon" href="<%=basePath%>/image/log.png" type="image/x-icon" />
<link type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css"
	rel="stylesheet" />
<style>
.button {
	width: 80px;
	height: 20px;
}
/**
.table {
	border-collapse: collapse;
	border-spacing: 0;
	white-space: nowrap;
	font-size: 2px;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .3);
	text-align: center;
	vertical-align: center;
}
**/
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

td {
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	width: 100%;
}

.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
}

#editDiv {
	z-index: 9999;
	width: 300px;
	height: 430px;
	background: #CFCFCF;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
}

#addDiv {
	z-index: 9999;
	width: 600px;
	height: 380px;
	background: #CFCFCF;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
}

#editPriceDiv {
	z-index: 9999;
	width: 300px;
	height: 210px;
	background: #CFCFCF;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
}

#addCommissionDiv {
	z-index: 9999;
	width: 300px;
	height: 210px;
	background: #CFCFCF;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
}

#addPddIdDiv {
	z-index: 9999;
	width: 300px;
	height: 250px;
	background: #CFCFCF;
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
<title>订单详情</title>
</head>
<script>
  jQuery(function () {
            // 时间设置
            jQuery('#startDate').datepicker({
                dateFormat: "yy-mm-dd"
            });
             jQuery('#endDate').datepicker({
                dateFormat: "yy-mm-dd"
            });

        });
  function uploadTbId()
  {
	  var form = document.getElementById('upload'),
		formData = new FormData(form);
	$.ajax({
		url : "<%=path%>/orderController/importTbId.do",
		type : "post",
		data : formData,
		processData : false,
		contentType : false,
		success : function(res) {
			alert('导入成功');
		},
		error : function(err) {
			alert("格式错误，请重新编辑~", err);
		}
	})
  }
	function uploadPic() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/orderController/importOrder.do",
			type : "post",
			data : formData,
			processData : false,
			contentType : false,
			success : function(res) {
				alert('导入成功' + res + '条数据');
			},
			error : function(err) {
				alert("格式错误，请重新编辑~", err);
			}
		})
	}
	function displayDiv(cnt)
	{
		$("#editDiv").css("top",(275+(cnt-1)*52)+"px");
			var orderId = $("#orderId"+cnt).html();
			var sellPrice = $("#sellPrice"+cnt).html();
			var buyPrice =$("#buyPrice"+cnt).html();
			var commission =$("#commission"+cnt).html();
			var orderType = $("#orderType"+cnt).val();
			var tbId = $("#tbId"+cnt).html();
			var remake = $("#remake"+cnt).html();
			$("#orderIdDiv").attr("value",orderId);
			$("#sellPriceDiv").attr("value",sellPrice);
			$("#buyPriceDiv").attr("value",buyPrice);
			$("#commissionDiv").attr("value",commission);
			$("#tbIdDiv").attr("value",tbId);
			$("#remakeDiv").attr("value",remake);
			$("#lineCnt").attr("value",cnt);
			$("#orderTypeDiv").val(orderType);
			$("#editDiv").show();
	}
	function hideDiv()
	{
				$("#addDiv").hide();	
				$("#editDiv").hide();
				$("#editPriceDiv").hide();
				$("#addCommissionDiv").hide();
				$("#addPddIdDiv").hide();
	}
	function editOrder()
	{
		$.ajax({
            type: "POST",
            url: "<%=path%>/orderController/editOrder.do",
			data : {
				orderId:$("#orderIdDiv").val(),
				buyPrice : $("#buyPriceDiv").val(),
				sellPrice : $("#sellPriceDiv").val(),
				commission :$("#commissionDiv").val(),
				tbId :$("#tbIdDiv").val(),
				remake :$("#remakeDiv").val(),
				orderType:$("#orderTypeDiv").val()
				
			},
			dataType : 'json',
			async : false,
			success : function(data) {
				alert('编辑成功');
				var cnt = $("#lineCnt").val();
				$("#sellPrice"+cnt).html(data.sellPrice);
				$("#buyPrice"+cnt).html(data.buyPrice);
				$("#commission"+cnt).html(data.commission);
				$("#tbId"+cnt).html(data.tbId);
				$("#remake"+cnt).html(data.remake);
				var orderType = data.orderType;
				if(orderType==1)
				{
					$("#tr"+cnt).css("background-color","#98FB98");
				}
				else if(orderType==2)
				{
					$("#tr"+cnt).css("background-color","RED");
				}
				else if(orderType==3)
				{
					$("#tr"+cnt).css("background-color","YELLOW");
				}
				else if(orderType==4)
				{
					$("#tr"+cnt).css("background-color","#CCCCCC");
				}
				$("#editDiv").hide();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function addOrder()
	{
			if($("#orderIdAdd").val() == ""|| $("#orderIdAdd").val() == null)
			{
				alert('订单号必填！');
				return;
			}
			if($("#buyIdAdd").val() == ""|| $("#buyIdAdd").val() == null)
			{
				alert('买入编号必填！');
				return;
			}
			if($("#shopNumberAdd").val() == ""|| $("#shopNumberAdd").val() == null)
			{
				alert('店铺编号必填！');
				return;
			}
			if($("#goodNameAdd").val() ==""|| $("#goodNameAdd").val() == null)
			{
				alert('商品名称必填！');
				return;
			}
			if($("#buyPriceAdd").val() == ""|| $("#buyPriceAdd").val() == null)
			{
				alert('买入价格必填！');
				return;
			}
			if($("#sellPriceAdd").val() == ""|| $("#sellPriceAdd").val() == null)
			{
				alert('卖出价格必填！');
				return;
			}
		$.ajax({
            type: "POST",
            url: "<%=path%>/orderController/addOrder.do",
			data : {         
				orderId:$("#orderIdAdd").val(),
				tbId:$("#buyIdAdd").val(),
				shopNumber:$("#shopNumberAdd").val(),
				goodName:$("#goodNameAdd").val(),
				goodDetail:$("#goodDetailAdd").val(),
				message:$("#messageAdd").val(),
				remake :$("#remakeAdd").val(),
				buyPrice : $("#buyPriceAdd").val(),
				sellPrice : $("#sellPriceAdd").val(),
				commission :$("#commissionAdd").val(),
				cnt :$("#goodCntAdd").val(),
				orderType:$("#orderTypeAdd").val()
				
			},
			dataType : 'text',
			async : false,
			success : function(data) {
					alert('保存成功');
					hideDiv();
					$("#orderIdAdd").val("");
					$("#buyIdAdd").val("");
					$("#shopNumberAdd").val("");
					$("#goodNameAdd").val("");
					$("#goodDetailAdd").val("");
					$("#messageAdd").val("");
					$("#remakeAdd").val("");
					$("#buyPriceAdd").val("");
					$("#sellPriceAdd").val("");
					
			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function editPrice()
	{
		$.ajax({
            type: "POST",
            url: "<%=path%>/orderController/editPrice.do",
			data : {
				orderId:$("#orderIdEditPrice").val(),
				price : $("#priceEditPrice").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('保存成功');
				$("#editPriceDiv").hide();
				$("#priceEditPrice").val('');
				$("#orderIdEditPrice").val('');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function addCommission()
	{
		$.ajax({
            type: "POST",
            url: "<%=path%>/orderController/addCommission.do",
			data : {
				orderId:$("#orderIdEditCommission").val(),
				commission : $("#commissionEditCommission").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('插入成功');
				$("#addCommissionDiv").hide();
				$("#orderIdEditCommission").val('');
				$("#commissionEditCommission").val('');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function addPddOrder()
	{
		$.ajax({
            type: "POST",
            url: "<%=path%>/orderController/addPddOrder.do",
			data : {
				orderId:$("#orderIdAddPdd").val(),
				buyId : $("#buyOrderIdAddPdd").val(),
				price : $("#priceAddPdd").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('插入成功');
				hideDiv();
					$("#orderIdAddPdd").val('');
				$("#buyOrderIdAddPdd").val('');
				$("#priceAddPdd").val('');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function displayEditPrice()
	{
		$("#editPriceDiv").show();
	}
	function displayAddCommission()
	{
		$("#addCommissionDiv").show();
	}
	function displayAddDiv()
	{
		$("#addDiv").show();
	}
	function displayPddDiv()
	{
		$("#addPddIdDiv").show();
	}
</script>
<body>

	<center>
		<div id="addDiv">
			<br />
			<laber style="text-align:center;">新增订单</laber>
			<br /> <br /> 订单编号：<input style="width: 160px" id="orderIdAdd"
				type="text" value="" /> &nbsp;&nbsp;&nbsp; 买入编号：<input
				style="width: 160px" id="buyIdAdd" type="text" value="" /> <br />
			<br /> 店铺编号：<input id="shopNumberAdd" type="text" value="" />
			&nbsp;&nbsp;&nbsp;订单类型：<select style="width: 160px; height: 25px;"
				class="select" id="orderTypeAdd" name="orderTypeAdd">
				<option value="2">拼多多购买</option>
				<c:forEach items="${orderTypeList}" var="orderType">
					<c:if test="${ orderType.id != 2 }">
						<option value="${orderType.id}">${orderType.orderType}</option>
					</c:if>
				</c:forEach>
			</select> <br /> <br /> 商品名称：<input id="goodNameAdd" type="text" value="" />
			&nbsp;&nbsp;&nbsp;商品详情：<input id="goodDetailAdd" type="text" value="" />
			<br /> <br /> 商品数量：<input id="goodCntAdd" type="text" value="1" />
			&nbsp;&nbsp;&nbsp; 买家留言：<input id="messageAdd" type="text" value="" />
			<br /> <br /> 卖出价格：<input id="sellPriceAdd" type="text" value="" />
			&nbsp;&nbsp;&nbsp;买入价格：<input id="buyPriceAdd" type="text" value="" />
			<br /> <br /> 佣&nbsp;&nbsp;&nbsp;金&nbsp;&nbsp; ： <input
				id="commissionAdd" type="text" value="0.0" /> &nbsp;&nbsp;&nbsp;
			备&nbsp;&nbsp;&nbsp;注 &nbsp;： <input id="remakeAdd" type="text"
				value="" /> <br /> <br /> <input type="button"
				style="width: 100px; height: 30px;" value="保 存" onclick="addOrder()" />
			&nbsp; &nbsp; &nbsp; &nbsp; <input type="button"
				style="width: 100px; height: 30px;" value="取 消" onclick="hideDiv()" />
		</div>
		<div id="editDiv">
			<br />
			<laber style="text-align:center;">编&nbsp;&nbsp;辑</laber>
			<input type="hidden" value="" id="lineCnt" /> <br /> <br /> 订单编号：<input
				style="width: 160px" id="orderIdDiv" type="text" value=""
				readonly="readonly" /> <br />
				<br /> 买入编号：<input
				style="width: 160px" id="tbIdDiv" type="text" value=""
				 /> <br /> 
				 <br /> 卖出价格：<input
				id="sellPriceDiv" type="text" value="" /> <br /> <br /> 买入价格：<input
				id="buyPriceDiv" type="text" value="" /> <br /> <br />
			佣&nbsp;&nbsp;&nbsp;&nbsp;金&nbsp;&nbsp; ：<input id="commissionDiv"
				type="text" value="" /> <br /> 
				<br /> 备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;：<input
				style="width: 160px" id="remakeDiv" type="text" value=""
				/> <br /> 
				<br /> 订单类型：<select
				style="width: 160px; height: 25px;" class="select" id="orderTypeDiv"
				name="orderTypeDiv">
				<c:forEach items="${orderTypeList}" var="orderType">
					<option value="${orderType.id}">${orderType.orderType}</option>
				</c:forEach>
			</select> <br /> <br /> <input type="button"
				style="width: 100px; height: 30px;" value="保 存"
				onclick="editOrder()" /> &nbsp; &nbsp; &nbsp; &nbsp; <input
				type="button" style="width: 100px; height: 30px;" value="取 消"
				onclick="hideDiv()" />
		</div>
		<div id="editPriceDiv">
			<br />
			<laber style="text-align:center;">商&nbsp;&nbsp;品&nbsp;&nbsp;涨&nbsp;&nbsp;价</laber>
			<br /> <br /> 订单编号：<input style="width: 130px"
				id="orderIdEditPrice" type="text" value="" /> <br /> <br /> 商品价格：<input
				id="priceEditPrice" style="width: 130px" type="text" value="" /> <br />
			<br /> <input type="button" style="width: 100px; height: 30px;"
				value="保 存" onclick="editPrice()" /> &nbsp; &nbsp; &nbsp; &nbsp; <input
				type="button" style="width: 100px; height: 30px;" value="取 消"
				onclick="hideDiv()" />
		</div>
		<div id="addCommissionDiv">
			<br />
			<laber style="text-align:center;">佣&nbsp;&nbsp;&nbsp;&nbsp;金</laber>
			<br /> <br /> 订单编号：<input style="width: 130px"
				id="orderIdEditCommission" type="text" value="" /> <br /> <br />
			佣&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金：<input
				id="commissionEditCommission" style="width: 130px" type="text"
				value="" /> <br /> <br /> <input type="button"
				style="width: 100px; height: 30px;" value="保 存"
				onclick="addCommission()" /> &nbsp; &nbsp; &nbsp; &nbsp; <input
				type="button" style="width: 100px; height: 30px;" value="取 消"
				onclick="hideDiv()" />
		</div>
		<div id="addPddIdDiv">
			<br />
			<laber style="text-align:center;">拼多多订单</laber>
			<br /> <br /> 订单编号：<input style="width: 130px" id="orderIdAddPdd"
				type="text" value="" /> <br /> <br /> 买入编号：<input
				id="buyOrderIdAddPdd" style="width: 130px" type="text" value="" />
			<br /> <br /> 买入价格：<input id="priceAddPdd" style="width: 130px"
				type="text" value="" /> <br /> <br /> <input type="button"
				style="width: 100px; height: 30px;" value="保 存"
				onclick="addPddOrder()" /> &nbsp; &nbsp; &nbsp; &nbsp; <input
				type="button" style="width: 100px; height: 30px;" value="取 消"
				onclick="hideDiv()" />
		</div>
		<form id="upload" enctype="multipart/form-data" method="post"
			action="<%=path%>/orderController/getOrderList.do">
			<input type="button" style="width: 100px; height: 30px;" value="店铺列表"
				onclick="window.open('<%=path%>/shopController/getShop.do')" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" style="width: 100px; height: 30px;" value="商品涨价"
				onclick="displayEditPrice()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" style="width: 100px; height: 30px;" value="佣金录入"
				onclick="displayAddCommission()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" style="width: 130px; height: 30px;"
				value="拼多多录入" onclick="displayPddDiv()" /> <br /> <br /> <input
				type="file" name="file" id="pic" /> <input type="button"
				style="width: 100px; height: 30px;" value="导入" onclick="uploadPic()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<input type="button" style="width: 120px; height: 30px;"
				value="淘宝订单号导入" onclick="uploadTbId()" /> <br /> <br /> 开始时间 ： <input
				id="startDate" type="text" name="startDate" />&nbsp;&nbsp;&nbsp;&nbsp;
			结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;
			关 键 字 ： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp;
			 	 &nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
						 负 责 人：	<select class = "select" id="staffIdSelect"  name="staffIdSelect">
						 <option value = "-1">全部</option>
						<c:forEach items="${staffList}" var="staff">
						<option value = "${staff.id}">${staff.name}</option>
						</c:forEach>
					</select>
						 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
			 订单类型：	<select class = "select" id="orderType"  name="orderType">
					 	<option value = "0">全部</option>
						<option value = "1">涨价订单</option>
						<option value = "-1">下架订单</option>
						<option value = "3">待解决订单</option>
						<option value = "4">退款订单</option>
					</select>
					&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" style="width: 100px; height: 30px;" value="查询" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input type="button" style="width: 100px; height: 30px;" value="新增订单"
				onclick="displayAddDiv()" />
		</form>
		<br />
		<table border="1" width="100% ">
			<tr height="60px" bgcolor="#D2691E">
				<td style="text-align: center; width: 15px;">Id</td>
				<td style="text-align: center; width: 90px;">卖出订单编号</td>
				<td style="text-align: center; width: 80px;">买入订单编号</td>
				<td style="text-align: center; width: 50px;">店铺名称</td>
				<td style="text-align: center; width: 30px;">负责人</td>
				<td style="text-align: center; width: 30px;">交易时间</td>
				<td style="text-align: center; width: 80px;">商品名称</td>
				<td style="text-align: center; width: 80px;">商品详情</td>
				<td style="text-align: center; width: 15px;">数量</td>
				<td style="text-align: center; width: 50px;">客户留言</td>
				<td style="text-align: center; width: 20px;">卖出价格</td>
				<td style="text-align: center; width: 20px;">买入价格</td>
				<td style="text-align: center; width: 20px;">佣金</td>
				<td style="text-align:center;width: 20px;">涨价</td>
				<td style="text-align: center; width: 50px;">备注</td>
				<td style="text-align: center; width: 30px;">操作</td>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="status">
				<c:if test="${ order.type ==1 }">
					<tr id="tr${status.index+1}" height="50px" bgcolor="#98FB98">
				</c:if>
				<c:if test="${order.type==2  }">
					<tr id="tr${status.index+1}" height="50px" bgcolor="RED">
				</c:if>
				<c:if test="${order.type==3 }">
					<tr id="tr${status.index+1}" height="50px" bgcolor="YELLOW">
				</c:if>
				<c:if test="${order.type==4 }">
					<tr id="tr${status.index+1}" height="50px" bgcolor="#CCCCCC">
				</c:if>
				<td style="text-align: center;">${status.index+1 }</td>
				<td style="width: 100px;" id="orderId${status.index+1}">${order.orderId}</td>
				<td id="tbId${status.index+1}">${order.tbId}</td>
				<td>${order.shopName}</td>
				<td>${order.staffName}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd" /></td>
				<td>${order.goodName}</td>
				<td>${order.goodDetail}</td>
				<td style="text-align: center;">${order.count}</td>
				<td>${order.message}</td>
				<td id="sellPrice${status.index+1}">${order.sellPrice}</td>
				<td id="buyPrice${status.index+1}">${order.buyPrice}</td>
				<td id="commission${status.index+1}">${order.commission}</td>
				<c:if test="${order.isEditPrice ==1 or order.isEditPrice == null}" >
				<td id ="editPrice${status.index+1}">${order.editPrice}</td>
				</c:if>
				<c:if test="${order.isEditPrice ==0}" >
				<td id ="editPrice${status.index+1}"  bgcolor="#FF34B3" >${order.editPrice}</td>
				</c:if>
				<td id="remake${status.index+1}">${order.remake}</td>
				<input type="hidden" value="${order.type}"
					id="orderType${status.index+1}" />
				<td style="text-align: center;">
					<button class="button" id="update"
						style="width: 60px; height: 30px;"
						onclick="displayDiv(${status.index+1})">编 辑</button>
				</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>