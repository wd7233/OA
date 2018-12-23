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
table tr:nth-child(odd) {
	background: #ccc;
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
 function getLucre() {
		$.ajax({
            type: "POST",
            url: "<%=path%>/lucreController/getLucre.do",
			data : {
				startDate : $("#startDate").val(),
				endDate : $("#endDate").val()
			},
			dataType : 'json',
			async : false,
			success : function(data) {
			  alert('收 入 : '+data.sellTotal+'\n'+
					'佣 金 : '+data.commissionTotal+'\n'+
			 		'支 出 : '+data.buyTotal+'\n'+
			 		'利 润 : '+data.lucre);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
</script>
<body>
	<center>
		<form id="upload" enctype="multipart/form-data" method="post" action="<%=path%>/lucreController/getOrderStaff.do">
		 开始时间 ： <input id="startDate" type="text" name="startDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;
		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
			
		</form>
		<br/>
		<table border="1" width="100% ">
			<tr height="60px" bgcolor="#EE2C2C">
				<td style="text-align:center;">Id</td>
				<td style="text-align:center;">名 称</td>
				<td style="text-align:center;">店铺数量</td>
				<td style="text-align:center;">订单数量</td>
				<td style="text-align:center;">退款数量</td>
				<td style="text-align:center;">退款率</td>
				<td style="text-align:center;">待解决数量</td>
				<td style="text-align:center;">待涨价数量</td>
				<td style="text-align:center;">总计利润</td>
			</tr>
			<c:forEach items="${lucreList}" var="lucre" varStatus="status">
				<tr height="50px">
				<td style="text-align:center;">${status.index+1 }</td>
				<td style="width:100px;">${lucre.userName}</td>
				<td>${fn:length(lucre.shopList)}</td>
				<td>${lucre.orderCnt}</td>
				<td>${lucre.backCnt}</td>
				<td>${lucre.backRate}</td>
				<td>${lucre.proCnt}</td>
				<td>${lucre.editCnt}</td>
				<td>${lucre.lucre}</td>
				</tr>
			</c:forEach>
		</table>
		<br/><br/>
		<button onclick="getLucre()" style="width: 200px; height: 50px;">总计</button>
		
	</center>
</body>
</html>