<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
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
             $("#startDate").datepicker('setDate',new Date()); 
             $("#endDate").datepicker('setDate',new Date());
        });
  
  function uploadPic() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/scalpingController/importOrder.do",
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
</script>
<body>
	<center>
		<form id="upload" enctype="multipart/form-data" method="post" action="<%=path%>/scalpingController/getOrder.do">
		 开始时间 ： <input id="startDate" type="text" name="startDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;&nbsp;&nbsp;
		 关 键 字 ： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		 <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
				
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="file" name="file" id="pic" /> 
		<input
				type="button" style="width: 100px; height: 30px;" value="导入"
				onclick="uploadPic()" />
		</form>
		<br/>
		<table border="1" width="100% ">
			<tr class = "ftr" height="60px"  style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
				<td style="text-align:center;">序号</td>
				<td style="text-align:center;">导入文件</td>
				<td style="text-align:center;">订单编号</td>
				<td style="text-align:center;">收货人</td>
				<td style="text-align:center;">联系方式</td>
				<td style="text-align:center;">价格</td>
				<td style="text-align:center;">刷单日期</td>
				<td style="text-align:center;">状态</td>
			</tr>
		<c:forEach items="${orderList}" var="order" varStatus="status">
				<tr height="50px">
				<td style="text-align:center;">${status.index+1 }</td>
					<td style="width:100px;">${order.goodNumber}</td>
				<td>${order.orderId}</td>
				<td>${order.consignee}</td>
				<td>${order.phone}</td>
				<td>${order.skuPirce}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${order.state}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>