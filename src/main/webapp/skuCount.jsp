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
<title>SKU销量排名统计</title>
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
  
</script>
<body>
	<center>
		<form id="upload" enctype="multipart/form-data" method="post"
			action="<%=path%>/specialorderController/skuCount.do">
			开始时间 ： <input id="startDate" type="text" name="startDate" />&nbsp;&nbsp;&nbsp;&nbsp;
			结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			店铺名称： <select
				class="select" id="shopNumber" name="shopNumber">
				<option value="">全部</option>
				<c:forEach items="${shopList}" var="shop">
						<option value = "${shop.number}">${shop.name}</option>
						</c:forEach>
			</select> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  onclick="window.open('<%=path%>/specialorderController/skuCount.do')" style="width: 200px; height: 50px;">型号统计</button>
		</form>
		<br />
		<br />
		<table border="1" width="100% ">
			<tr height="60px" style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td style="text-align: center; width: 8px;">序号</td>
				<td style="text-align: center; width: 30px;">SKU</td>
				<td style="text-align: center; width: 50px;">已出售数量</td>
			</tr>
			<c:forEach items="${orderList}" var="order" varStatus="status">
			<tr height="50px" bgcolor="#EBEBEB" >
				<td style="text-align: center;">${status.index+1 }</td>
				<td>${order.sku}</td>
				<td >${order.cnt}</td>
				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>