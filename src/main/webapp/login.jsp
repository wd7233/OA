<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>启程云商</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
<style>
html {
	height: 100%
}

body {
	margin: 0;
	height: 100%;
	background: #fff;
}

canvas {
	display: block;
	width: 100%;
	height: 100%;
}

.body_content {
	position: absolute;
	top: 30%;
	left: 20%;
	height: 20%;
	background: palevioletred;
	width: 20%;
}

.main {
	filter: alpha(opacity : 90);
	opacity: 0.5;
	left: 1200px;
	top: 200px;
	position: absolute;
	width: 300px;
	height: 400px;
	background-color: Pink;
	border-top-left-radius:20px;
	border-top-right-radius:20px;
	border-bottom-left-radius:20px;
	border-bottom-right-radius:20px;
}
#editDiv {
	z-index: 9999;
	width: 280px;
	height: 280px;
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
<script>
function displayDiv(cnt)
{
		$("#editDiv").show();
}
function hideDiv()
{
			$("#editDiv").hide();
}
function regist()
{
	$.ajax({
        type: "POST",
        url: "<%=path%>/loginController/regist.do",
		data : {
			name:$("#name").val(),
			account : $("#accountR").val(),
			pwd : $("#pwdR").val(),
			role :$("#role").val(),
		},
		dataType : 'text',
		async : false,
		success : function(data) {
			alert('注册成功');
			$("#editDiv").hide();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error ：" + textStatus);
		}
	});
}
</script>
</head>
<body>
	<canvas id="canvas"> </canvas>
	<script src="./js/index.js"></script>
	<div id="editDiv">
			<br />
			<br />&nbsp; &nbsp; &nbsp; &nbsp;姓 名：<input style="width: 160px" id="name" type="text"
				value="" /> <br />
			<br /> &nbsp; &nbsp; &nbsp;&nbsp;账 号：<input id="accountR"style="width: 160px" type="text" value="" /> <br />
			<br />&nbsp; &nbsp; &nbsp;&nbsp;密 码：<input id="pwdR" style="width: 160px"type="text" value="" /> <br />
			<br /> &nbsp; &nbsp; &nbsp; &nbsp;权 限：<select style="width: 160px; height: 25px;"
				class="select" id="role" name="role">
				<!-- 	<option value="0">管理员</option> -->
					<option value="1">客 服</option>
					<option value="2">拍 手</option>
			</select> <br />
			<br />&nbsp; &nbsp; &nbsp; &nbsp; <input type="button" style="width: 80px; height: 30px;"
				value="注 册" onclick="regist()" /> &nbsp; &nbsp; &nbsp; &nbsp;<input
				type="button" style="width: 80px; height: 30px;" value="取 消"
				onclick="hideDiv()" />
		</div>
	<form action="<%=path%>/loginController/login.do" method="POST">
		<div class="main">
			<div>
				<a style="left: 80px; top: 50px; position: relative;"><font
					style="font-family: Times New Roman; font-weight: 1000; font-size: 30px;">启
						程 云 商</font></a>
			</div>
			<font
				style="left: 20px; top: 115px; position: relative; font-weight: 100;">账号：
			</font> </br> <input
				style="left: 90px; top: 90px; position: relative; height: 30px"
				type="text" name="account"> </br> <font
				style="left: 20px; top: 145px; position: relative; font-weight: 100;">密码：
			</font> </br> <input
				style="left: 90px; top: 120px; position: relative; height: 30px"
				type="text" name="pwd"> </br> <input
				style="left: 30px; top: 160px; position: relative; height: 40px; width: 100px"
				type="submit" value="登 录" />
			<button class="button"  type="button" id="update"
							style="left: 70px; top: 160px; position: relative; height: 40px; width: 100px"
							onclick="displayDiv(1)">注 册</button>
		</div>
	</form>
</body>
</html>