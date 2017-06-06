<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间预约</title>
</head>
<body>

<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;提交申请</h>
	<hr>
	<form id="requestForm" name="requestForm" method="post" action="SendRequest">
		<table style="font-size:20px;" cellpadding="10">		
			<tr><td>楼&nbsp;名</td><td>：</td><td><input type="text" name="building" value="${building}"></td></tr>
			<tr><td> 房 间 号 </td><td>：</td><td><input type="text" name="roomNum" value="${roomNum}"></td></tr>
			<tr><td>日&nbsp;期</td><td>：</td><td><input type="date" name="date" value="${date}"></td></tr>
			<tr><td>时&nbsp;段</td><td>：</td>
			<td><select name="time">
	    		<option value="">——选择时段——</option>
	        	<option value="1">08：00——09：30</option>
	            <option value="2">10：00——11：30</option>	
	            <option value="3">13：30——15：00</option>
	            <option value="4">15：30——17：00</option>	
	            <option value="5">17：30——19：00</option>
	            <option value="6">19：30——21：00</option>	            			
	        </select> 
	         <script>
	        	var option=document.forms["queryForm"]["time"].options;
	        	for(i=1;i<=6;i++) {  
	        		if(option[i].value==${time}) { 
	        		 option[i].selected = true;}
	        	}   	
	        </script></td>
	        </tr>        
	        <tr><td>长期预约</td><td>：</td>
	        <td>
	        <input type="checkbox" name="longterm" value="1"> (两个月内每周)
	        </td></tr>
			<tr><td>缘&nbsp;由</td><td>：</td>
			<td>
				<textarea name="reason" maxlength=200 style="font-size:20px" rows="5" cols="50"></textarea>
			</td>
			</tr>
			
			<tr><td></td><td></td>
				<td><input type="submit" value="确认" onclick="return check()"></td></tr>
		</table>
	</form>
</div>
<jsp:include page="user_menu.jsp" flush="true"/>
<%@ include file="user_menu.jsp" %>
</body>

<c:if test="${flag==1}">
	<Script>alert("房间不存在。");</Script>
</c:if>
<c:if test="${flag==2}">
	<Script>alert("权限不够。")</Script>
</c:if>
<c:if test="${flag==3}">
	<Script>alert("预约成功。");</Script>
</c:if>
<c:if test="${flag==4}">
	<Script>alert("成功发出申请。");</Script>
</c:if>
<c:if test="${flag==5}">
	<Script>alert("预约失败。")</Script>
</c:if>

<script type="text/javascript">
function check() {
	 var building = document.forms["requestForm"]["building"].value;
	 var roomNum = document.forms["requestForm"]["roomNum"].value;
	 var date = document.forms["requestForm"]["date"].value;
	 var time = document.forms["requestForm"]["time"].value;
	 var reason = document.forms["requestForm"]["reason"].value;
	 if(building==""||roomNum==""||date==""||time==""||reason=="") {
		 alert("请输入完整信息。");
		 }
	 var r = /^[1-9]\d*$/;
	 if (!r.test(roomNum)) {alert("房间号为正整数");};
	 var longterm = document.forms["requestForm"]["longterm"].checked;
	 
	 var db = new Date(date.replace(/-/g, "/"));
	 var today = new Date();
		if(today >= Date.parse(db)) {
		alert("选择日期至少为当日后一天。");
		return false;}
		
	 if(Date.parse(db)> Date.parse(addDate(today,30))) {
		 alert("请选择一个月内的时间");
		 return false;
	 }
	 
	 if( ${RANK<3} && longterm ) {
		 alert("权限不够，无法长期预约。");
		 return false;
	 }
	 
} 

function addDate(date,days){ 
    var d=new Date(date); 
    d.setDate(d.getDate()+days); 
    var m=d.getMonth()+1; 
    return d.getFullYear()+'-'+m+'-'+d.getDate(); 
  } 
</script>


</html>