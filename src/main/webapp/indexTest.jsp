<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html >
<script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>启程云商</title>
<link rel="icon" href="./image/log.png" type="image/x-icon" />
<style>

</style>
<script>
    function show(url) {
		var iframe = document.getElementById('iframeMenu');
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                iframe.src = "<%=path%>/amountController/getAmountList.do";
            }
        }
    }

</script>
</head>
<body>
<div class="col-md-2">
	<div class="sidebar-nav">
		<div class="nav-canvas">
			<ul class="nav nav-pills nav-stacked main-menu" id="main_menu">
				
			</ul>
		</div>
	</div>

</div>

<iframe   src="" width="70%" height=""  id="iframeMenu"  frameborder="0"  name="iframe名称"     scrolling="yes">
</iframe>
</body>

<script>
    $(function(){
        $.ajax({
            url:'<%=path%>/menuController/getMenu.do',
            type:'post',
            dataType:'json',
            data:{userName:''},
            async:false,
            success:function(data){
                $("#main_menu").empty();
                $("#main_menu").append("<li class='nav-header'>主菜单</li><li class='accordion'>");
                $.each(data,function(i,val) {
                    if(val["level"]==1){
                        var htm="<li class='accordion'><a href='#'>";
                        htm+="<i class='glyphicon glyphicon-home'></i>"+val.menu+"</a>";
                        htm+="<ul class='nav nav-pills nav-stacked' id="+val.id+">";
                        htm+="</ul></li>";
                        $("#main_menu").append(htm);
                    }
                });
                $.each(data,function(index,value){
                    if(value["level"]==2){
                        var ht="<li id="+value.id+"><a href='#' onClick=show("+ value.url +")><i class='glyphicon glyphicon-save'></i>"+value.menu+"</a></li>";
                        $("#"+value.supMenuId).append(ht);
                    }
                });
            }
        });
    });


</script>
</html>