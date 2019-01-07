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
table tr:nth-child(odd) {
	background: #9cdbe0;
}
table tr:nth-child(even) {
	background: #f8cacd;
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
	border-bottom: 2px solid #FFFFFF;
	border-top: 2px solid #FFFFFF;
	border-left: 2px solid #FFFFFF;
	border-right: 2px solid #FFFFFF;
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
	background: #ADD8E6;
	position: absolute;
	left: 30%;
	top: 40%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid;
	display: none;
	text-align: center; /*让文字水平居中*/
}
#expressDiv {
	z-index: 9999;
	width: 500px;
	height:40px;
	background: #FFFFFF;
	position: absolute;
	right: 0%;
	top: 17%;
	margin-left: -100px;
	margin-top: -100px;
	border: 1px solid #FFFFFF;
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
	background: #ADD8E6;
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
  
  function selectExpress() {
		$.ajax({
			url : "<%=path%>/specialorderController/selectExpress.do",
			type : "post",
			data : {
				province:$("#provinceSelect").val(),
				weight:$("#weightSelect").val()},
			dataType : 'json',
			success : function(data) {
				$("#bsExpress").html(data.baishi);
				$("#yzExpress").html(data.youzheng);
				$("#stExpress").html(data.shengtong);
				$("#anExpress").html(data.anneng);
				$("#expressDiv").show();
			},
			error : function(err) {
				alert("格式错误，请重新编辑~", err);
			}
		})
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
				$("#changeEdit").val(cnt);
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
  function edit()
  {
	  var cnt = $("#changeEdit").val();
	  $("#changeEdit").val("");
	  $.ajax({
          type: "POST",
          url: "<%=path%>/specialorderController/sendOrder.do",
			data : {
				orderId:$("#orderIdEdit").val(),
				courierCompany:$("#courierCompanyEdit").val(),
				courierNumber:$("#courierNmuberEdit").val(),
				deliverTime:$("#deliverTimeEdit").val(),
			},
			dataType : 'text',
			async : false,
			success : function(data) {
					alert('保存成功');
					if($("#courierNmuberEdit").val() == '')
					{
						$("#tabColor"+cnt).css("background","YELLOW");
					}
					else
					{
						$("#tabColor"+cnt).css("background","GREEN");
					}
					$("#courierNmuberEdit").val('');
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
  var falg = true;
  function sendDetail(cnt)
  {
	  
	  if(falg)
	  {
	    $('table tr').find('td:eq(6)').hide();
	    $('table tr').find('td:eq(9)').hide();
	    $('table tr').find('td:eq(10)').hide();
	    $('table tr').find('td:eq(11)').hide();
	    $('table tr').find('td:eq(12)').hide();
	    $('table tr').find('td:eq(13)').hide();
	    $('table tr').find('td:eq(14)').hide();
	    $('table tr').find('td:eq(17)').hide();
	    $('table tr:even').show();
        falg=false;
	   }
	  else
	  {
		  $('table tr').find('td:eq(6)').show();
		    $('table tr').find('td:eq(9)').show();
		    $('table tr').find('td:eq(10)').show();
		    $('table tr').find('td:eq(11)').show();
		    $('table tr').find('td:eq(12)').show();
		    $('table tr').find('td:eq(13)').show();
		    $('table tr').find('td:eq(14)').show();
		    $('table tr').find('td:eq(17)').show();
		    $('table tr:even').hide();
		    $('table tr:eq(0)').show();
	        falg=true;

	  }
	  //  $("#orderMesg").show();
  }
  function cancel()
  {
	  $("#detailDiv").hide();
  }
  function displayDiv()
  {
	  $("#detailDiv").hide();
	  $("#editDiv").hide();
  }
</script>
<body>
	<div id="editDiv">
		<br />
		<laber style="text-align:center;">订 单 编 辑</laber>
		<br /> <br /> 订单编号：<input disabled="disabled" style="width: 160px"
			id="orderIdEdit" type="text" value="" /> &nbsp;&nbsp;&nbsp; 商品名称：<input
			style="width: 160px" disabled="disabled" id="goodNameEdit"
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 订单状态：<select
			style="width: 160px; height: 20px;" disabled="disabled"
			class="select" id="stateEdit" name="stateEdit">
			<option value="未发货">未发货</option>
			<option value="待签收">待签收</option>
			<option value="已签收">已签收</option>
		</select> <br /> <br />实收付款：<input disabled="disabled" id="priceEdit"
			type="text" value="" /> &nbsp;&nbsp;&nbsp;颜色/规格：<input
			disabled="disabled" disabled="disabled" id="skuEdit" type="text"
			value="" /> &nbsp;&nbsp;&nbsp;商品数量：<input disabled="disabled"
			id="countEdit" type="text" value="" /> <br /> <br />
		收&nbsp;货&nbsp;人&nbsp;：<input id="consigneeEdit" disabled="disabled"
			type="text" value="1" /> &nbsp;&nbsp;&nbsp; 联系方式：<input
			id="phoneEdit" disabled="disabled" type="text" value="" />
		&nbsp;&nbsp;&nbsp;买家留言：<input id="messageEdit" type="text"
			disabled="disabled" value="" /> <br /> <br />&nbsp;&nbsp;&nbsp;省&nbsp;&nbsp;
		份&nbsp;：<input disabled="disabled" id="provinceEdit" type="text"
			value="1" /> &nbsp;&nbsp;&nbsp;城市/县：<input disabled="disabled"
			id="areasEdit" type="text" value="" /> &nbsp;&nbsp;&nbsp; 街道地址：<input
			disabled="disabled" id="streetEdit" type="text" value="" /> <br />
		<br />&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp; 注&nbsp;： <input
			disabled="disabled" id="remakeEdit" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 延期时间： <input id="proTimeEdit" disabled="disabled"
			type="text" value="0.0" /> &nbsp;&nbsp;&nbsp; 售后状态：<input
			disabled="disabled" id="afterStateEdit" type="text" value="" /> <br />
		<br />快递公司：<input id="courierCompanyEdit" type="text" value="1" />
		&nbsp;&nbsp;&nbsp; 快递单号：<input id="courierNmuberEdit" type="text"
			value="" /> &nbsp;&nbsp;&nbsp; 发货时间：<input id="deliverTimeEdit"
			type="text" value="" /> <br /> <br />
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
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 售后状态：<input
			disabled="disabled" id="afterStateDetail" type="text" value="" /> <br />
		<br />快递公司：<input id="courierCompanyDetail" disabled="disabled"
			type="text" value="" /> &nbsp;&nbsp;&nbsp; 快递单号：<input
			id="courierNmuberDetail" disabled="disabled" type="text" value="" />
		&nbsp;&nbsp;&nbsp; 发货时间：<input id="deliverTimeDetail"
			disabled="disabled" type="text" value="" />
	</div>
	<center>
		<form id="upload" enctype="multipart/form-data" method="post"
			action="<%=path%>/specialorderController/getSendSpecialorder.do">
			<input type="hidden" id="changeEdit" value="" /> 关 键 字 ： <input
				id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp; &nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <input
				type="submit" style="width: 100px; height: 30px;" value="查询" />
		</form>
		<br />
		重量 ：<input style=" width:60px;" id="weightSelect" type="text" value="" />&nbsp;KG&nbsp;&nbsp;&nbsp;
		省份 ：<input style=" width:60px;" id="provinceSelect" type="text"value=""   />&nbsp;&nbsp;&nbsp;
		<button onclick="selectExpress()" style="width:140px; height: 30px;"><font  style="letter-spacing:5pt;font-size: 20px;">快递查询</font></button>
		<div id= "expressDiv">
		百世：<font  style="color: red;font-size: 30px;"><laber id = "bsExpress"></laber></font>
		 &nbsp;&nbsp;&nbsp;邮政：<font  style="color: red;font-size: 30px;"><laber id = "yzExpress"></laber></font>
		&nbsp;&nbsp;&nbsp; 申通：<font  style="color: red;font-size: 30px;"><laber id = "stExpress"></laber></font>
		&nbsp;&nbsp;&nbsp; 安能：<font  style="color: red;font-size: 30px;"><laber id = "anExpress"></laber></font>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="sendDetail()" style="width:140px; height:30px;"><font  style="letter-spacing:5pt;font-size: 20px;">发货一览</font></button>
		<br /> <br />
		<table border="1" width="100% " id="tabDe">
			<tr height="60px" style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td style="text-align: center; width: 8px;">序号</td>
				<td id="orderIdF" style="text-align: center; width: 50px;">订单编号</td>
				<td style="text-align: center; width: 20px;">商品名稱</td>
				<td style="text-align: center; width: 50px;">商品規格</td>
				<td style="text-align: center; width: 50px;">商品颜色</td>
				<td style="text-align: center; width: 30px;">备 注</td>
				<td style="text-align: center; width: 5px;">数量</td>
				<td style="text-align: center; width: 15px;">收货人</td>
				<td style="text-align: center; width: 30px;">手机号码</td>
				<td style="text-align: center; width: 15px;">省份</td>
				<td style="text-align: center; width: 80px;">地址</td>
				<td style="text-align: center; width: 10px;">实收款</td>
				<td style="text-align: center; width: 30px;">创建时间</td>
				<td style="text-align: center; width: 20px;">操 作</td>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="status">
				<c:if test="${ order.courierNumber == ''}">
					<tr id="tabColor${status.index+1 }" height="50px" bgcolor="#FFFACD"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.courierNumber != '' }">
					<tr id="tabColor${status.index+1 }" height="50px" bgcolor="GREEN"
						onclick="detail(${status.index+1})">
				</c:if>
				<td style="text-align:center;">${status.index+1 }</td>
				<td style="width: 100px;" id="orderId${status.index+1}">${order.orderId}</td>
				<td>${order.goodName}</td>
				<td><b>${order.sku}</b></td>
				<td><b>${order.color}</b></td>
				<td style="width: 100px;white-space:normal;word-break:break-all;"><b>${order.remakes}</b></td>
				<td>${order.count}</td>
				<td>${order.consignee}</td>
				<td>${order.telephone}</td>
				<td style="white-space:normal;word-break:break-all;">${order.province}</td>
				<td style="white-space:normal;word-break:break-all;" >${order.city}${order.area}${order.street}</td>
				<td>${order.price}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>
					<button class="button" id="editBut"
						style="width: 60px; height: 30px;"
						onclick="showEditDiv(${status.index+1})">发 货</button>
				</td>
				</tr>
				<tr id="orderMesg" height="60px" bgcolor="#FFE1FF" id="orderMesg"
					style="display: none;">
					<td style="text-align: center; white-space:normal;word-break:break-all;" colspan="4" >
						<font style="font-size: 20px;color: black"><b>
						${order.consignee}&nbsp;&nbsp; ${order.telephone} &nbsp;&nbsp;   
						${order.province}${order.city}${order.area}${order.street}&nbsp;&nbsp;</b>
						</font>
					</td>
					<td style="text-align: center; " colspan="3">
					净重：<font style="color: red">${order.weight}</font>KG&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					百世：<font style="color: red">${order.baishiPrice}</font>&nbsp;&nbsp; 
					邮政：<font style="color: red">${order.youzhengPrice}</font> &nbsp;&nbsp;
					申通：<font style="color: red">${order.shengtongPrice}</font>&nbsp;&nbsp;
					安能： <font style="color: red">${order.annengPrice}</font>
					</td>
				    <td style="text-align: center; " colspan="">
					<font style="font-size: 30px;color: black"><b>${order.expressPrice}</b></font></td>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>