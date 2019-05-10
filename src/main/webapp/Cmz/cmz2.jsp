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
<link rel="stylesheet" href="<%=path%>/Cmz/index.css" media="screen"
	type="text/css">
<style>
.boxDom {
	width:100%;
	height:100%;
	position:absolute;
	overflow:hidden;
	background: url(img/xiaoma1.jpg) no-repeat;
    background-size: 100% 100%;
    -webkit-background-size: 100% 100%;
    font-family: "宋体";
	
}
.string {
	width:300px;
	height:400px;
	position:absolute;
	overflow:hidden;
	color:#000;
	font-size:4em;
	line-height:1.5em;
	cursor:pointer;
	white-space:nowrap;
	z-index:9999919999999;
}
</style>
<head>
<meta charset="UTF-8" />
<title>原生JS实现弹幕效果</title>
</head>
<body>
	<div class="boxDom" id="boxDom">
	</div>
</body>
<script>
$(function() {
    var pageW = parseInt($(document).width());
    var pageH = parseInt($(document).height());
    var boxDom = $("#boxDom");
    var Top, Right;
    var width;
    width = pageW;
    var colorArr = ["#cfaf12", "#12af01", "#981234", "#adefsa", "#db6be4", "#f5264c", "#d34a74"];
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
