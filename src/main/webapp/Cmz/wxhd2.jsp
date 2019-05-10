<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE html>

<html>
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-1.11.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.min.js"
	charset="utf-8"></script>
<script src="<%=basePath%>/js/jquery-ui-timepicker-addon.js"></script>
<link href="<%=basePath%>/css/jquery-ui-timepicker-addon.css"
	rel="stylesheet" />
<link rel="icon" href="<%=basePath%>/image/log.png" type="image/x-icon" />
<link type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css"
	rel="stylesheet" />
<style>
.center {
	position: absolute;
	width: 800px;
	height: 520px;
	margin: auto;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	background-color: blue;
	overflow-x: hidden;
	overflow-y: hidden;
}
</style>
<head>
<meta charset="UTF-8" />
<title>原生JS实现弹幕效果</title>
</head>
<body>
	<div id="box" class="center"></div>
	<input type="text" id="txt" />
	<button onclick="send()">提交内容</button>
	<button onclick="start()">启动</button>
</body>
<script>
 var $j = jQuery.noConflict();
function start()
{
	$j(function()
	{
		$.ajax({
			url : "<%=path%>/Cmz/getDanmu.do",
			type : "post",
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(res) {
				var danmuList = res.danmuList;
				for (var i = 0; i < danmuList.length; i++) 
				{
					alert(danmuList[i].content);
					send(danmuList[i].content) ;
				}
			},
			error : function(err) {
				alert("格式错误，请重新编辑~", err);
			}
		})
	});

 }
</script>
<script>

function send(str) {
	var word = str;
	var span = document.createElement('span');
	var top = parseInt(Math.random() * 500) - 20;
	var color1 = parseInt(Math.random() * 256);
	var color2 = parseInt(Math.random() * 256);
	var color3 = parseInt(Math.random() * 256);
	var color = "rgb(" + color1 + "," + color2 + "," + color3 + ")";
	top = top < 0 ? 0 : top;
	span.style.position = 'absolute';
	span.style.top = top + "px";
	span.style.color = color;
	span.style.left = '500px';
	span.style.whiteSpace = 'nowrap';
	var nub = (Math.random() * 10) + 1;
	span.setAttribute('speed', nub);
	span.speed = nub;
	span.innerHTML = word;
	$('box').appendChild(span);
	$('txt').value = "";
}
	function $(str) {
		return document.getElementById(str);
	}
	function send() {
		var word = $('txt').value;
		var span = document.createElement('span');
		var top = parseInt(Math.random() * 500) - 20;
		var color1 = parseInt(Math.random() * 256);
		var color2 = parseInt(Math.random() * 256);
		var color3 = parseInt(Math.random() * 256);
		var color = "rgb(" + color1 + "," + color2 + "," + color3 + ")";
		top = top < 0 ? 0 : top;
		span.style.position = 'absolute';
		span.style.top = top + "px";
		span.style.color = color;
		span.style.left = '500px';
		span.style.whiteSpace = 'nowrap';
		var nub = (Math.random() * 10) + 1;
		span.setAttribute('speed', nub);
		span.speed = nub;
		span.innerHTML = word;
		$('box').appendChild(span);
		$('txt').value = "";
	}
	setInterval(move, 200);
	function move() {
		var spanArray = $('box').children;
		for (var i = 0; i < spanArray.length; i++) {
			spanArray[i].style.left = parseInt(spanArray[i].style.left)
					- spanArray[i].speed + 'px';
		}
	}
</script>
</html>
