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
<script type="text/javascript" src="<%=basePath%>/js/jquery.ui.datepicker-zh-CN.js" charset="gb2312"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui-timepicker-zh-CN.js"></script>
<link href="<%=basePath%>/css/jquery-ui-timepicker-addon.css" rel="stylesheet"  />
<link rel="icon" href="<%=basePath%>/image/log.png" type="image/x-icon" />
<link type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css" rel="stylesheet" />
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
}
td{
	-o-text-overflow:ellipsis;
	text-overflow:ellipsis;
	overflow:hidden;
	white-space:nowrap;
	width:100%;
}
.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
}
#orderCntDiv{
	width: 1000px;
	height: 40px;
	background: #00000;
	left: 50%;
	top: 50%;
    text-align: center;/*让文字水平居中*/
	
}
font
{
	font-size: 30px;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
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
             $("#startDate").datepicker('setDate',new Date(new Date().getTime() - 24*60*60*1000)); 
             $("#endDate").datepicker('setDate',new Date());

        });
  function editPrice(cnt)
  {
	    var res = confirm("是否确认已经涨价");
	    if(res == true){
	  		$.ajax({
       	   		type: "POST",
         		 url: "<%=path%>/orderController/updateEditPriceState.do",
				data : {
				 orderId : $("#orderId"+cnt).html()
				},
				dataType : 'text',
				async : false,
				success : function(data) {
					var color =  $("#orderId"+cnt).css("background-color");
					 $("#editPrice"+cnt).css("background-color",color);
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error ：" + textStatus);
				}
			});
	  	}
  }
</script>
<body>
		
	<center>
		<form id="upload" enctype="multipart/form-data" method="post" action="<%=path%>/orderController/getOrderListByUser.do">
		 开始时间 ： <input id="startDate" type="text" name="startDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;
		 关 键 字 ： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp;
		 	 &nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
						 负 责 人：	<select class = "select" id="staffId"  name="staffId">
						<c:forEach items="${staffList}" var="staff">
						<option value = "${staff.id}">${staff.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; 
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
		 <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
		<br/>
		<div id="orderCntDiv">
		&nbsp;&nbsp;&nbsp; 订单合计：<font style = "color : green">${totalCnt}</font>
			&nbsp;&nbsp;&nbsp; 退款订单：<font style = "color : #D1D1D1">${backCnt}</font>
			&nbsp;&nbsp;&nbsp; 退款率：<font style = "color : #BA55D3">${backRate}</font>
			&nbsp;&nbsp;&nbsp; 待解决订单：<font style = "color : #FFD700">${proCnt}</font>
			&nbsp;&nbsp;&nbsp; 待涨价订单：<font style = "color : #FF34B3">${editCnt}</font>
			&nbsp;&nbsp;&nbsp; 昨日订单：<font style = "color : red">${yesterDayCnt}</font>
		</div>
		<br/>
		<table border="1" width="100% ">
			<tr height="60px" bgcolor="#D2691E">
				<td style="text-align:center;width: 15px;">Id</td>
				<td style="text-align:center;width: 50px;">店铺名称</td>
				<td style="text-align:center;width: 80px;">拼多多-订单编号</td>
				<td style="text-align:center;width: 60px;">淘宝-订单编号</td>
				<td style="text-align:center;width: 50px;">交易时间</td>
				<td style="text-align:center;width: 50px;">商品名称</td>
				<td style="text-align:center;width: 50px;">商品详情</td>
				<td style="text-align:center;width: 15px;">数量</td>
				<td style="text-align:center;width: 50px;">客户留言</td>
				<td style="text-align:center;width: 30px;">卖出价格</td>
				<td style="text-align:center;width: 30px;">买入价格</td>
				<td style="text-align:center;width: 30px;">涨价</td>
				<td style="text-align:center;width: 50px;">备注</td>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="status">
			<c:if test="${ order.type ==1 }" >
					<tr height="50px" bgcolor="#98FB98">
				</c:if>
				<c:if test="${order.type==2  }" >
					<tr height="50px" bgcolor="RED">
				</c:if>
				<c:if test="${order.type==3 }" >
					<tr height="50px" bgcolor="YELLOW">
				</c:if>
				<c:if test="${order.type==4 }" >
					<tr height="50px" bgcolor="#CCCCCC">
				</c:if>
				<td style="text-align:center;">${status.index+1 }</td>
				<td>${order.shopName}</td>
				<td style="width:100px;"  id="orderId${status.index+1}">${order.orderId}</td>
				<td style="width:100px;">${order.tbId}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd" /></td>
				<td>${order.goodName}</td>
				<td>${order.goodDetail}</td>
				<td style="text-align:center;">${order.count}</td>
				<td>${order.message}</td>
				<td>${order.sellPrice}</td>
				<td>${order.buyPrice}</td>
				<c:if test="${order.isEditPrice ==1 or order.isEditPrice == null}" >
				<td id ="editPrice${status.index+1}">${order.editPrice}</td>
				</c:if>
				<c:if test="${order.isEditPrice ==0}" >
					<c:if test="${order.editPriceType ==0}" >
					 <td id ="editPrice${status.index+1}" onClick = "editPrice(${status.index+1})" bgcolor="#FF34B3" >${order.editPrice}</td>
					</c:if>
					<c:if test="${order.editPriceType ==1}" >
					 <td id ="editPrice${status.index+1}"   onClick = "editPrice(${status.index+1})" bgcolor="#8A2BE2" >下 架</td>
					</c:if>
					<c:if test="${order.editPriceType ==2}" >
					 <td id ="editPrice${status.index+1}"  onClick = "editPrice(${status.index+1})" bgcolor="#8A2BE2" >${order.editPrice}</td>
					</c:if>
				</c:if>
				<td>${order.remake}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>