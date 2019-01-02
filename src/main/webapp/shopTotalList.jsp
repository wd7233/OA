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
.main {
	background-color: #ff0000;
	transform: translate(-50%, -50%);
	position: absolute;
	top: 50%;
	left: 50%;
}
.select
{
width:80px;
height:20px;
}
.button
{
width:100px;
height:30px;
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
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺列表</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
</head>
<script>
	function update(cnt) {
		$.ajax({
            type: "POST",
            url: "<%=path%>/loginController/updateStaffName.do",
			data : {
				shopNumber : $("#shopNumber"+cnt).html(),
				staffId : $("#oriLoanerIdType"+cnt).val()
			},
			dataType : 'text',
			async : false,
			success : function(data) {
				alert('保存成功');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error ：" + textStatus);
			}
		});
	}
	function uploadPic() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/fileController/importTaobao.do",
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
	function uploadShop() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/fileController/importShop.do",
			type : "post",
			data : formData,
			processData : false,
			contentType : false,
			success : function(res) {
				alert('导入成功' + res + '条店铺');
			},
			error : function(err) {
				alert("格式错误，请重新编辑~", err);
			}
		})
	}
	function uploadExpress() {
		var form = document.getElementById('upload'),
			formData = new FormData(form);
		$.ajax({
			url : "<%=path%>/fileController/importExpress.do",
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
<form  id="upload" enctype="multipart/form-data" method="post" action="<%=path%>/loginController/getShop.do">
	<center>
	<br/>
	<button  onclick="window.open('<%=path%>/loginController/downNewLoadGoods.do')">获取商品</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.open('<%=path%>/loginController/loadShops.do')" >淘单单接入</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button  onclick="window.open('<%=path%>/orderController/getOrderList.do')" >订单查询</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.open('<%=path%>/lucreController/getOrderStaff.do')" >利润统计</button>
		<br/>
		<br/>
 				<input type="file" name="file"id="pic" />
				 <input type="button"
				 value="店铺导入"
				onclick="uploadShop()" />
					&nbsp;&nbsp;&nbsp;&nbsp;
				 快递公司：	<select class = "select" id="expressType"  name="expressType">
							<option value = "0">百世</option>
							<option value = "1">邮政</option>
							<option value = "2">申通</option>
							<option value = "3">圆通</option>
					</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="button"
				 value="快递计费导入"
				onclick="uploadExpress()" />
				&nbsp;&nbsp;&nbsp;&nbsp;
					商品类型 ：<select style="width: 150px; height: 30px;" class = "select" id="goodType"  name="goodType">
						<c:forEach items="${goodTypeList}" var="goodType">
						<option value="${goodType.id}">${goodType.type}</option>
						</c:forEach>
					</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			 <input type="button"
				 value="商品导入"
				onclick="uploadPic()" />
		<br/>
			<br/>
		 关 键 字： <input id="keyWord" type="text" name="keyWord" />&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 负 责 人：	<select class = "select" id="staff"  name="staffName">
							<option value = "-1">全部</option>
						<c:forEach items="${staffList}" var="staff">
						<option value = "${staff.id}">${staff.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
				style="width: 100px; height: 30px;" value="查询" />
					<br/><br/>
					</form>
		<table style="text-align: center" border="1" width="80%">
			<tr height="60px" stytle = "font-weight:bold;">
				<td  style=" width:20px ">Id</td>
				<td>店铺编号</td>
				<td>店铺名称</td>
				<td>账号</td>
				<td>所有人</td>
				<td>商品数量</td>
				<td>负责人</td>
				<td>操 作</td>
			</tr>
			<c:forEach items="${shoplist}" var="shop" varStatus="status">
			<c:if test="${ shop.isopen ==1 }">
				<tr height="50px" bgcolor="RED">
			</c:if>
			<c:if test="${ shop.isopen ==0 }">
				<tr height="50px" >
			</c:if>
					<td>${status.index+1 }</td>
					<td><label id="shopNumber${status.index+1}">${shop.number}</label></td>
					<td>${shop.name }</td>
					<td>${shop.account }</td>
					<td>${shop.userName }</td>
					<td>${shop.goodsnum }</td>
					<td>
					<select class = "select" id="oriLoanerIdType${status.index+1}"  name="staffName${status.index+1}">
							<option value = "${shop.staffId}">${shop.staffName }</option>
						<c:forEach items="${staffList}" var="staff">
						<c:if  test="${ shop.staffId!=staff.id }">
						<option value = "${staff.id}">${staff.name}</option>
						</c:if>
						</c:forEach>
					</select></td>
					<td>
				<button  class = "button"  onclick="update(${status.index+1})">保
						存</button></td>
				</tr>
			</c:forEach>
		</table>
	</center>
	<!--  <a href="<%=path%>/addUser.jsp" style="left:300px;position:relative;height:30px">新增用户</a><br/> -->
</body>
</html>