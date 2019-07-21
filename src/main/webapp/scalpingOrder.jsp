<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
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
.fy_page ul{
				padding:0;
				min-width: 450px;
}
.fy_page ul::after{
				content: '';
				display: block;
				clear: both;
}
.fy_page ul li{
				float: left;
				width:auto;
				min-width:32px;
				height: 30px;
				line-height:30px;
				list-style: none;
}
.fy_page a{
				color:#aaa;
				font-family: "微软雅黑";
				padding:0 10px;
				text-decoration: none;
				display: block;
				text-align: center;
				border: 1px solid #ccc;
				border-left: none;
	}
			.fy_page ul li:first-child a{
				border-left: 1px solid #ccc;
			}
			
			.fy_page ul li a:hover{
				background-color: dodgerblue;
			}
			.fy_page ul li a:hover{
				color: white;
			}
			.fy_page .disabled a:hover{
				background-color: white;
				cursor:not-allowed;
				color: #aaa;
			}
			.fy_page .active a{
				background-color: dodgerblue;
				color: white;
			}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
</head>
<script>
	var baseURL = "${pageContext.request.contextPath}/";
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
			url : baseURL+"scalpingController/importOrder.do",
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
  function uploadPingjia()
  {
	  var form = document.getElementById('upload'),
		formData = new FormData(form);
	$.ajax({
		url : baseURL+"scalpingController/importPingjia.do",
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
  function selectS()
  {
	$.ajax({
		url : baseURL+"scalpingController/getOrderS.do",
		type : "post",
		processData : false,
		contentType : false,
		date:
		{
			startTime:$("#startDate").val(),
			endTime : $("#endDate").val(),
			keyWord : $("#keyWord").val(),
		},
		success : function(res) {
			$("#showDiv").html(res);

		},
		error : function(err) {
			alert("err", err);
		}
	})
  }
  P.initMathod({
      params: {elemId: '#fy_page',total:$("#totalRecord").val()},
      requestFunction: function () {
         // P.config.total = parseInt(Math.random() * 10 + 85);//此处模拟总记录变化

          //TODO ajax异步请求过程,异步获取到的数据总条数赋值给 P.config.total
          
          //列表渲染自行处理
          $.ajax({
		url : baseURL+"scalpingController/getOrderS.do",
		type : "post",
		processData : false,
		contentType : false,
		date:
		{
			startTime:$("#startDate").val(),
			endTime : $("#endDate").val(),
			keyWord : $("#keyWord").val(),
		},
		success : function(res) {
			$("#showDiv").html(res);

		},
		error : function(err) {
			alert("err", err);
		}
	})
          console.log(JSON.stringify(P.config));
      }
  });
</script>
<body >
	<center>
		<form id="upload" enctype="multipart/form-data" method="post">
		 开始时间 ： <input id="startDate" type="text" name="startDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
		 结束时间 ： <input id="endDate" type="text" name="endDate" />&nbsp;&nbsp;&nbsp;&nbsp;
		 关 键 字 ： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		订单状态 :	<select class = "select" id="orderType"  name="orderType">
					<option value = "-1" select = "select">全部</option>
					<option value = "0">正常订单</option>
					<option value = "1">异常订单</option>
					</select>	
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			
		 <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
				<input type="button"
				style="width: 100px; height: 30px;" value="查询S" onclick="selectS()"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="file" name="file" id="pic" /> 
		<input
				type="button" style="width: 100px; height: 30px;" value="导入订单"
				onclick="uploadPic()" />
				
		<input
				type="button" style="width: 100px; height: 30px;" value="导入评价"
				onclick="uploadPingjia()" />
		</form>
		<br/>
		<div id="orderCntDiv">
			&nbsp;&nbsp;&nbsp; 正常订单数：<font style = "color : #FF34B3">${orderCnt}</font>
			&nbsp;&nbsp;&nbsp; 异常订单数：<font style = "color : red">${backCnt}</font>
			&nbsp;&nbsp;&nbsp; 总价小计：<font style = "color : #FFD700">${totalPrice}</font>
		</div>
		<br/>
		<table border="1" width="100% ">
			<tr class = "ftr" height="20px"  style="font-weight:bold;text-align: center;background:#054c84;color : #FFFFFF;">
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
				<c:if test="${ order.afterState =='售后处理中' }">
					<tr height="50px" bgcolor="#F8CACD"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.afterState=='无售后或售后取消' || order.afterState=='订单未关闭'}">
					<tr height="50px" bgcolor="#9CDBE0"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.afterState=='退款成功' }">
					<tr height="50px" bgcolor="#F8E040"
						onclick="detail(${status.index+1})">
				</c:if>
				<c:if test="${order.afterState == null || order.afterState == ''}">
					<tr height="50px" bgcolor="#EE0000" 
						onclick="detail(${status.index+1})">
				</c:if>
				<td style="text-align:center;">${status.index+1 }</td>
					<td style="width:100px;">${order.goodNumber}</td>
				<td>${order.orderId}</td>
				<td>${order.consignee}</td>
				<td>${order.phone}</td>
				<td>${order.skuPirce}</td>
				<td><fmt:formatDate value="${order.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${order.afterState}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="fy_page" id="fy_page">
		<input type="hidden" id = "totalRecord" value = "${totalRecord}"/>
		</div>
	</center>
</body>
</html>