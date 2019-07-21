<!-- 客服 -->
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

.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
}

#orderCntDiv {
	width: 1000px;
	height: 40px;
	background: #00000;
	left: 50%;
	top: 50%;
	text-align: center; /*让文字水平居中*/
}

#editDiv {
	z-index: 9999;
	width: 900px;
	height: 390px;
	background: #FFEC8B;
	position: absolute;
	left: 30%;
	top: 40%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
	text-align: center; /*让文字水平居中*/
}
#suppleDiv {
	z-index: 9999;
	width: 900px;
	height: 390px;
	background: #FFEC8B;
	position: absolute;
	left: 30%;
	top: 40%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
	text-align: center; /*让文字水平居中*/
}
font {
	font-size: 30px;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
}

#detailDiv {
	z-index: 9099;
	width: 900px;
	height: 390px;
	background: #FFEC8B;
	position: absolute;
	left: 30%;
	top: 40%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
	opacity: 0.9;
	text-align: center; /*让文字水平居中*/
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
             jQuery('#deliverTimeDetail').datepicker({
                 dateFormat: "yy-mm-dd HH:mm"
             });
             $("#startDate").datepicker('setDate',new Date(new Date().getTime() - 24*60*60*1000)); 
             $("#endDate").datepicker('setDate',new Date());

        });
  
  function uploadPic() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/specialorderController/importOrder.do",
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
  function showSuppleDiv(cnt)
  {
	  $("#detailDiv").hide();
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/getOrderDetailByOrderId.do",
			data : {
				orderId:$("#orderId"+cnt).html()
			},
			dataType : 'json',
			async : false,
			success : function(data) {
					$("#orderIdSupple").attr("value", data.orderId);
					$("#goodNameSupple").val(data.goodName);
					$("#stateSupple").val(data.state);
					
					$("#priceSupple").val(data.price);
					$("#skuSupple").val(data.sku);
					
					$("#consigneeSupple").val(data.consignee);
					
					$("#remakeSupple").val(data.rmakes);
					$("#afterStateSupple").val(data.afterState);
					
					$("#detailDiv").hide();
					$("#suppleDiv").show();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
  }
  function showEditDiv(cnt)
  {
	  $("#detailDiv").hide();
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/getOrderDetailByOrderId.do",
			data : {
				orderId:$("#orderId"+cnt).html()
			},
			dataType : 'json',
			async : false,
			success : function(data) {
				  $("#orderIdEdit").attr("value", data.orderId);
					$("#goodNameEdit").val(data.goodName);
					$("#stateEdit").val(data.state);
					
					$("#priceEdit").val(data.price);
					$("#skuEdit").val(data.sku);
					$("#countEdit").val(data.count);
					
					$("#consigneeEdit").val(data.consignee);
					$("#phoneEdit").val(data.telephone);
					$("#messageEdit").val(data.message);
					
					$("#provinceEdit").val(data.province);
					$("#areasEdit").val(data.area);
					$("#streetEdit").val(data.street);
					
					$("#remakeEdit").val(data.rmakes);
					$("#proTimeEdit").val(data.proTime);
					$("#afterStateEdit").val(data.afterState);
					
					$("#courierCompanyEdit").val(data.courierCompany);
					$("#courierNmuberEdit").val(data.courierNmuber);
					$("#deliverTimeEdit").val(data.deliverTime);
					$("#detailDiv").hide();
					$("#editDiv").show();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
  }
  function supple()
  {
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/suppleOrder.do",
			data : {
				orderId:$("#orderIdSupple").val(),
				priceAdd:$("#priceAddSupple").val(),
				messageAdd:$("#messageSupple").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
					alert('保存成功');
					$("#detailDiv").hide();
					$("#editDiv").hide();
					$("#suppleDiv").hide();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
  }
  function edit()
  {
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/editOrder.do",
			data : {
				orderId:$("#orderIdEdit").val(),
				stateN:$("#stateEdit").val(),
				consignee:$("#consigneeEdit").val(),
				telephone:$("#phoneEdit").val(),
				message:$("#messageEdit").val(),
				province:$("#provinceEdit").val(),
				area:$("#areasEdit").val(),
				street:$("#streetEdit").val(),
				remakes:$("#remakeEdit").val(),
				courierCompany:$("#courierCompanyEdit").val(),
				courierNumber:$("#courierNmuberEdit").val(),
				deliverTime:$("#deliverTimeEdit").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
					alert('保存成功');
					$("#detailDiv").hide();
					$("#editDiv").hide();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
  }
  function detail(cnt)
  {
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/getOrderDetailByOrderId.do",
			data : {
				orderId:$("#orderId"+cnt).html()
			},
			dataType : 'json',
			async : false,
			success : function(data) {
				$("#orderIdDetail").attr("value", data.orderId);
				$("#goodNameDetail").val(data.goodName);
				$("#stateDetail").val(data.state);
				
				$("#priceDetail").val(data.price);
				$("#skuDetail").val(data.sku);
				$("#countDetail").val(data.count);
				
				$("#consigneeDetail").val(data.consignee);
				$("#phoneDetail").val(data.telephone);
				$("#messageDetail").val(data.message);
				
				$("#provinceDetail").val(data.province);
				$("#areasDetail").val(data.area);
				$("#streetDetail").val(data.street);
				
				$("#remakeDetail").val(data.rmakes);
				$("#proTimeDetail").val(data.proTime);
				$("#afterStateDetail").val(data.afterState);
				
				$("#courierCompanyDetail").val(data.courierCompany);
				$("#courierNmuberDetail").val(data.courierNmuber);
				$("#deliverTimeDetail").val(data.deliverTime);
				$("#detailDiv").show();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	  
  }
  function cancel()
  {
	  $("#detailDiv").hide();
  }
  function displayDiv()
  {
	  $("#detailDiv").hide();
	  $("#editDiv").hide();
	  $("#suppleDiv").hide();
  }
</script>
<body style  = "background-color:green;">
<div id="suppleDiv">
		<br />
		<laber style="text-align:center;">订 单 补发</laber>
		<br /> <br /> 订单编号：<input disabled="disabled" style="width: 160px"
			id="orderIdSupple" type="text" value="" /> &nbsp;&nbsp;&nbsp; 商品名称：<input
			style="width: 160px" disabled="disabled" id="goodNameSupple"
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 订单状态：<select disabled="disabled"
			style="width: 160px; height: 20px;" class="select" id="stateSupple"
			name="stateSupple">
			<option value="未发货">未发货</option>
			<option value="待签收">待签收</option>
			<option value="已签收">已签收</option>
		</select> <br /> <br />实收付款：<input disabled="disabled" id="priceSupple"
			type="text" value="" /> &nbsp;&nbsp;&nbsp;颜色/规格：<input
			disabled="disabled" id="skuSupple" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 售后状态：<input disabled="disabled" id="afterStateSupple"
			type="text" value="" /> <br /> <br /> 收&nbsp;货&nbsp;人&nbsp;：<input
			id="consigneeSupple"  disabled="disabled" type="text" value="" /> &nbsp;&nbsp;&nbsp;备注：<input
			id="remakeSupple" disabled="disabled" type="text" value="" /> &nbsp;&nbsp;&nbsp; 补差价：<input
			id="priceAddSupple" type="text" value="" /> 
		<br /> <br /><br />补发内容： 
			<textarea id="messageSupple" rows="3" cols="20"  style ="width:700px ;height : 80px">
			</textarea>
		&nbsp;&nbsp;&nbsp;  <br /> <br />
		<button class="button" id="editBut"
			style="width: 100px; height: 30px;" onclick="supple()">保
			存</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="button" id="editBut"
			style="width: 100px; height: 30px;" onclick="displayDiv()">取
			消</button>
	</div>
	<div id="editDiv">
		<br />
		<laber style="text-align:center;">订 单 编 辑</laber>
		<br /> <br /> 订单编号：<input disabled="disabled" style="width: 160px"
			id="orderIdEdit" type="text" value="" /> &nbsp;&nbsp;&nbsp; 商品名称：<input
			style="width: 160px" disabled="disabled" id="goodNameEdit"
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 订单状态：<select
			style="width: 160px; height: 20px;" class="select" id="stateEdit"
			name="stateEdit">
			<option value="未发货">未发货</option>
			<option value="待签收">待签收</option>
			<option value="已签收">已签收</option>
		</select> <br /> <br />实收付款：<input disabled="disabled" id="priceEdit"
			type="text" value="" /> &nbsp;&nbsp;&nbsp;颜色/规格：<input
			disabled="disabled" id="skuEdit" type="text" value="" />
		&nbsp;&nbsp;&nbsp;商品数量：<input disabled="disabled" id="countEdit"
			type="text" value="" /> <br /> <br /> 收&nbsp;货&nbsp;人&nbsp;：<input
			id="consigneeEdit" type="text" value="1" /> &nbsp;&nbsp;&nbsp; 联系方式：<input
			id="phoneEdit" type="text" value="" /> &nbsp;&nbsp;&nbsp;买家留言：<input
			id="messageEdit" type="text" value="" /> <br /> <br />&nbsp;&nbsp;&nbsp;省&nbsp;&nbsp;
		份&nbsp;：<input id="provinceEdit" type="text" value="1" />
		&nbsp;&nbsp;&nbsp;城市/县：<input id="areasEdit" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 街道地址：<input id="streetEdit" type="text" value="" />

		<br /> <br />&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp; 注&nbsp;： <input
			id="remakeEdit" type="text" value="" /> &nbsp;&nbsp;&nbsp; 延期时间： <input
			id="proTimeEdit" disabled="disabled" type="text" value="0.0" />
		&nbsp;&nbsp;&nbsp; 售后状态：<input disabled="disabled" id="afterStateEdit"
			type="text" value="" /> <br /> <br />快递公司：<input
			id="courierCompanyEdit" type="text" value="1" /> &nbsp;&nbsp;&nbsp;
		快递单号：<input id="courierNmuberEdit" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 发货时间：<input id="deliverTimeEdit" type="text"
			value="" /> <br /> <br />
		<button class="button" id="editBut"
			style="width: 100px; height: 30px;" onclick="edit(${status.index+1})">保
			存</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="button" id="editBut"
			style="width: 100px; height: 30px;" onclick="displayDiv()">取
			消</button>
	</div>
	<div id="detailDiv" onclick="cancel()">
		<br />
		<laber style="text-align:center;">订 单 详 情</laber>
		<br /> <br /> 订单编号：<input disabled="disabled" style="width: 160px"
			id="orderIdDetail" type="text" value="" /> &nbsp;&nbsp;&nbsp; 商品名称：<input
			style="width: 160px" disabled="disabled" id="goodNameDetail"
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 订单状态：<input
			disabled="disabled" id="stateDetail" type="text" value="" /> <br />
		<br />实收付款：<input disabled="disabled" id="priceDetail" type="text"
			value="" /> &nbsp;&nbsp;&nbsp;颜色/规格：<input disabled="disabled"
			id="skuDetail" type="text" value="" /> &nbsp;&nbsp;&nbsp;商品数量：<input
			disabled="disabled" id="countDetail" type="text" value="" /> <br />
		<br /> 收&nbsp;货&nbsp;人&nbsp;：<input disabled="disabled"
			id="consigneeDetail" type="text" value="1" /> &nbsp;&nbsp;&nbsp;
		联系方式：<input disabled="disabled" id="phoneDetail" type="text" value="" />
		&nbsp;&nbsp;&nbsp;买家留言：<input disabled="disabled" id="messageDetail"
			type="text" value="" /> <br /> <br />&nbsp;&nbsp;&nbsp;省&nbsp;&nbsp;
		份&nbsp;：<input disabled="disabled" id="provinceDetail" type="text"
			value="1" /> &nbsp;&nbsp;&nbsp;城市/县：<input disabled="disabled"
			id="areasDetail" type="text" value="" /> &nbsp;&nbsp;&nbsp; 街道地址：<input
			disabled="disabled" id="streetDetail" type="text" value="" /> <br />
		<br />&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp; 注&nbsp;： <input
			disabled="disabled" id="remakeDetail" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 延期时间： <input id="proTimeDetail" disabled="disabled"
			type="text" value="0.0" /> &nbsp;&nbsp;&nbsp; 售后状态：<input
			disabled="disabled" id="afterStateDetail" type="text" value="" /> <br />
		<br />快递公司：<input id="courierCompanyDetail" disabled="disabled"
			type="text" value="1" /> &nbsp;&nbsp;&nbsp; 快递单号：<input
			id="courierNmuberDetail" disabled="disabled" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 发货时间：<input id="deliverTimeDetail"
			disabled="disabled" type="text" value="" />
	</div>
	<center>
		<form id="upload" enctype="multipart/form-data" method="post"
			action="<%=path%>/specialorderController/getSpecialorder.do">
			开始时间 ： <input id="startDate" type="text" name="startDate" />&nbsp;&nbsp;&nbsp;&nbsp;
			结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;
			关 键 字 ： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; 负 责 人： <select class="select" id="staffId"
				name="staffId">
				<c:forEach items="${staffList}" var="staff">
					<option value="${staff.id}">${staff.name}</option>
				</c:forEach>
			</select> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 订单状态： <select
				class="select" id="orderState" name="orderState">
				<option value="">全部</option>
				<option value="未发货">未发货</option>
				<option value="待签收">待签收</option>
				<option value="已签收">已签收</option>
			</select> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			店铺名称： <select
				class="select" id="shopNumber" name="shopNumber">
				<option value="">全部</option>
				<c:forEach items="${shopList}" var="shop">
						<option value = "${shop.number}">${shop.name}</option>
						</c:forEach>
			</select> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  <input
				type="submit" style="width: 100px; height: 30px;" value="查询" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<br /> <br /> <input type="file" name="file" id="pic" /> <input
				type="button" style="width: 100px; height: 30px;" value="导入"
				onclick="uploadPic()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  onclick="window.open('<%=path%>/specialorderController/getSendSpecialorder.do')" style="width: 200px; height: 50px;">发货订单</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  onclick="window.open('<%=path%>/specialorderController/skuCount.do')" style="width: 200px; height: 50px;">SKU统计</button>
		</form>
		<br />
		<br />
		<table border="1" width="100% ">
			<tr height="60px" style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td style="text-align: center; width: 8px;">序号</td>
				<td style="text-align: center; width: 30px;">店铺名称</td>
				<td style="text-align: center; width: 50px;">订单编号</td>
				<td style="text-align: center; width: 50px;">商品名稱</td>
				<td style="text-align: center; width: 50px;">商品颜色</td>
				<td style="text-align: center; width: 50px;">商品規格</td>
				<td style="text-align: center; width: 30px;">创建时间</td>
				<td style="text-align: center; width: 5px;">数量</td>
				<td style="text-align: center; width: 15px;">收货人</td>
				<td style="text-align: center; width: 30px;">是否刷单</td>
				<td style="text-align: center; width: 30px;">售后状态</td>
				<td style="text-align: center; width: 40px;">操 作</td>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="status">
			
			<c:if test="${order.sdName == null}">
				<c:if test="${ order.afterState =='售后处理中' }">
					<tr height="50px" bgcolor="#F8CACD"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.afterState=='无售后或售后取消' }">
					<tr height="50px" bgcolor="#9CDBE0"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.afterState=='退款成功' }">
					<tr height="50px" bgcolor="#F8E040"
						onclick="detail(${status.index+1})">
				</c:if>
			</c:if>
			<c:if test="${order.sdName != null}">
					<tr height="50px" bgcolor="#EBEBEB" 
						onclick="detail(${status.index+1})">
			</c:if>
				<td style="text-align: center;">${status.index+1 }</td>
				<td>${order.shopName}</td>
				<td style="width: 100px;" id="orderId${status.index+1}">${order.orderId}</td>

				<td>${order.goodName}</td>
				<td>${order.color}</td>
				<td>${order.sku}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${order.count}</td>
				<td>${order.consignee}</td>
				<td >${order.sdName}</td>
				<td >${order.afterState}</td>
				<td>
					<button class="button" id="editBut"
						style="width: 60px; height: 30px;background:none;border:2px solid #FFFFFF;"
						onclick="showEditDiv(${status.index+1})"><font style = "font-size: 17px;color: #054C84;" ><b>编 辑</b></font></button>
						&nbsp;&nbsp;
					<!-- 	<button class="button" id="editBut"
						style="width: 60px; height: 30px;background:none;border:2px solid #FFFFFF;"
						onclick="showSuppleDiv(${status.index+1})"><font style = "font-size: 17px;color: #054C84;" ><b>补 发</b></font></button>
					 -->
				</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>