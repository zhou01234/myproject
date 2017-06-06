<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>房间管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;房间查询</h>
	<hr>
	<form id="queryForm" name="queryForm" method="post" action="QueryRoom">
		<td>&nbsp;楼名：</td><td><input type="text" name="building"></td>
		<td>&nbsp;房间号：</td><td><input type="text" name="roomNum"></td>
		<td>&nbsp;日期：</td><td><input type="date" name="date"></td>
		<td>&nbsp;时段:</td>	        
	    <td><select name="time">
	    		<option value="">——选择时段——</option>
	        	<option value="1">08：00——09：30</option>
	            <option value="2">10：00——11：30</option>	
	            <option value="3">13：30——15：00</option>
	            <option value="4">15：30——17：00</option>	
	            <option value="5">17：30——19：00</option>
	            <option value="6">19：30——21：00</option>	            			
	        </select> 
	    </td>
	    <input type="submit" value="查询" onclick="return check()">
	</form>
</div>
<jsp:include page="user_menu.jsp" flush="true"/>
<%@ include file="user_menu.jsp" %>
</body>

<script type="text/javascript">
function check() {	
	var date=document.forms['queryForm']['date'].value;
	var time=document.forms['queryForm']['time'].value;
	if(date==""||time=="") {
		alert("请完整填写时间信息。");
		return false;
	}
	
	var db = new Date(date.replace(/-/g, "/"));
	if(new Date() >= Date.parse(db)) {
	alert("选择日期至少为当日后一天。");
	return false;}
}
	
</script>


</html>