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
<head>
<meta charset="UTF-8" />
<title>原生JS实现弹幕效果</title>
</head>
<body>
	<table style="text-align: center;" border="1" width="70%" id = "tt">
		<tr height="60px"
			style="font-weight: bold; text-align: center; background: #054c84; color: #FFFFFF;">
			<td style="width: 30px">序号</td>
			<td>openid</td>
			<td>content</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${danmuList}" var="dm" varStatus="status">
			<tr height="50px" style="text-align: center;" id ="trId${status.index+1}">
				<td>${status.index+1 }</td>
				<td><label">${dm.openId}</label></td>
				<td>${dm.content }</td>
				<td><button class="button" id="update"
						onclick="danmuSend(${dm.id}, ${status.index+1})"
						style="width: 60px; height: 30px; background: none; border: 2px solid #FFFFFF;">
						<font style="font-size: 17px; color: #054C84;"><b>上 墙</b></font>
					</button>
					<button class="button" id="update"
						onclick="del(${dm.id },${status.index+1})"
						style="width: 60px; height: 30px; background: none; border: 2px solid #FFFFFF;">
						<font style="font-size: 17px; color: #054C84;"><b>删 除</b></font>
					</button>
					</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
function danmuSend(id,cnt)
{
	$.ajax({
        type: "POST",
        url: "<%=path%>/Cmz/fabu.do",
		data : {
			id : id,
			isPass : 0
		},
		dataType : 'text',
		async : false,
		success : function(data) {
			$('table tr:eq('+cnt+')').remove();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error ：" + textStatus);
		}
	});
}
$(function(){
    $("table tr").click(function() {
        $(this).remove();
    });
});
function del(id,cnt)
{
	$.ajax({
        type: "POST",
        url: "<%=path%>/Cmz/fabu.do",
		data : {
			id : id,
			isPass : 3
		},
		dataType : 'text',
		async : false,
		success : function(data) {
			$('table tr:eq('+cnt+')').remove();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error ：" + textStatus);
		}
	});
}
send();
</script>
</html>
