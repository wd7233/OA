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
<style>
.boxDom {
	width:100%;
	height:100%;
	position:absolute;
	overflow:hidden;
	background: url(img/xiaoma123.jpg) no-repeat;
    background-size: 100% 100%;
    -webkit-background-size: 100% 100%;
    font-family: "宋体";
    z-index:9999919999999999;
	
}
.string {
	width:1000px;
	height:400px;
	position:absolute;
	overflow:hidden;
	color:#000;
	font-size:2em;
	font-weight:900px;
	line-height:1em;
	cursor:pointer;
	white-space:nowrap;
	z-index:9999919999999;
}
</style>
<head>
<meta charset="utf-8">
<title>褚梦泽.COM~</title>
<link rel="stylesheet" href="<%=path%>/Cmz/index.css" media="screen"
	type="text/css">
</head>
<body>
	<div class="container none"></div>
	<div class="mask"></div>
	
	<div id = "picCenter" >
	
	</div>
	<div id = "boxDom" >
	
	</div>
	<script type="text/javascript" src="<%=path%>/Cmz/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/Cmz/js/transform.js"></script>
	<script type="text/javascript" src="<%=path%>/Cmz/js/tick.js"></script>
	<!--data为静态数据 如用ajax获取请取消输入引入-->
	<!--抽奖动画-->
	<script type="text/javascript" src="<%=path%>/Cmz/js/3d.js"></script>
	<!--实际抽奖逻辑代码-->
	<script type="text/javascript" src="<%=path%>/Cmz/js/lucky.js"></script>
	<!-- ajax抽奖逻辑 -->
	<!-- <script type="text/javascript" src="js/ajaxLucky.js"></script> -->
	<script type="text/javascript" src="<%=path%>/Cmz/js/cmz.js"></script>
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
    }, 5000);
 
    function auto(text) {
        var creSpan = $("<span class='string'></span>");
        //var text = $("#text").val();
        creSpan.text(text);
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
            "right": pageW + 100
        }, 30000, "linear", function() {
            $(this).remove();
        });
    }

    function getRandomColor() {
        return '#' + (function(h) {
            return new Array(7 - h.length).join("0") + h
        })((Math.random() * 0x1000000 << 0).toString(16));
    }
});

</script>
</html>