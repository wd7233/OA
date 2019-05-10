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

html,body {
	margin:0px;
	padding:0px;
	width:100%;
	height:100%;
	font-family:"微软雅黑";
	font-size:62.5%;
	background:#ccc;
}
.boxDom {
	width:100%;
	height:100%;
	position:relative;
	overflow:hidden;
	background:BLUE;
}
.idDom {
	width:100%;
	height:60px;
	background:#666;
	position:fixed;
	bottom:0px;
	
}
.content {
	display:inline-block;
	width:430px;
	height:40px;
	position:absolute;
	left:0px;
	right:0px;
	top:0px;
	bottom:0px;
	margin:auto;
	
}
.title {
	display:inline;
	font-size:4em;
	vertical-align:bottom;
	color:#fff;
}
.text {
	border:none;
	width:300px;
	height:30px;
	border-radius:5px;
	font-size:2.4em;
}
.btn {
	width:60px;
	height:30px;
	background:#f90000;
	border:none;
	color:#fff;
	font-size:2.4em;
}
.string {
	width:300px;
	height:40px;
	position:absolute;
	overflow:hidden;
	color:#000;
	font-size:4em;
	line-height:1.5em;
	cursor:pointer;
	white-space:nowrap;
}
</style>
<head>
<meta charset="UTF-8" />
<title>原生JS实现弹幕效果</title>
</head>
<body>
	<div class="boxDom" id="boxDom">
		<div class="idDom" id="idDom">
			<div class="content">
				<p class="title">吐槽:</p>
				<input type="text" class="text" id="text">
				<button type="button" class="btn" id="btn">发射</button>
				<button onclick="start()">启动</button>
			</div>
		</div>
	</div>
</body>
<script>
$(function() {
    var pageW = parseInt($(document).width());
    var pageH = parseInt($(document).height());
    var boxDom = $("#boxDom");
    var btnDom = $("#btn");
    var Top, Right;
    var width;
    width = pageW;
    var colorArr = ["#cfaf12", "#12af01", "#981234", "#adefsa", "#db6be4", "#f5264c", "#d34a74"];
    btnDom.bind("click", auto);
    document.onkeydown = function(e) {
        if (e.keyCode == 13) {
            auto();
        }
    }
	
 // 设置定时器,间隔1000毫秒调用一次
    setInterval(function() {
        $.post("<%=path%>/Cmz/getDanmu.do", {}, function(data) {
            // data为获取的弹幕数据
            auto(data.danmu);
            // 有就展示,没有就忽略
        }, 'json');
    }, 3000);
 
    function auto(text) {
        var creSpan = $("<span class='string'></span>");
        //var text = $("#text").val();
        creSpan.text(text);
        $("#text").val("");
        Top = parseInt(pageH * (Math.random()));
        var num = parseInt(colorArr.length * (Math.random()));
        if (Top > pageH - 90) {
            Top = pageH - 90;
        }
        creSpan.css({
            "top": Top,
            "right": -300,
            "color": getRandomColor()
        });
        boxDom.append(creSpan);
        var spanDom = $("#boxDom>span:last-child");
        spanDom.stop().animate({
            "right": pageW + 300
        }, 10000, "linear", function() {
            $(this).remove();
        });
    }

    function getRandomColor() {
        return '#' + (function(h) {
            return new Array(7 - h.length).join("0") + h
        })((Math.random() * 0x1000000 << 0).toString(16));
    }
});
//随机获取弹幕颜色的函数
function getRandomColor () {
  let randomColor = []
  for (let i = 0 ; i < 3; ++i){
    let color = Math.floor(Math.random() * 256).toString(16)
    color = color.length == 1 ? '0' + color : color
    randomColor.push(color)
  }
  return '#' + randomColor.join('')
}

</script>
</html>
