<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/query.css" rel="stylesheet" type="text/css" />
<title>房间管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;房间查询</h>
	<hr>
	<form id="queryForm" name="queryForm" method="post" action="QueryRoom">
	<tr>
		<td>&nbsp;楼名：</td><td><input type="text" name="building" value="${building}"></td>
		<td>&nbsp;房间号：</td><td><input type="text" name="roomNum" value="${roomNum}"></td>
		<td>&nbsp;日期：</td><td><input type="date" name="date" value="${date}"></td>
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
	         <script>
	        	var option=document.forms["queryForm"]["time"].options;
	        	for(i=1;i<=6;i++) {  
	        		if(option[i].value==${time}) { 
	        		 option[i].selected = true;}
	        	}   	
	        </script>
	    </td>
	    <input type="submit" value="查询" onclick="return check()">
	    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td>
   			<a href="javascript:void(0);" onclick="topage('1')"><<</a>&nbsp;
   			<a href="javascript:void(0);" onclick="topage('${PAGE.currentpage-1}')"><</a>&nbsp;
   			<input type="text" name="cp" style="width:20px" 
   			value="${PAGE.currentpage}" onkeypress="if(event.keyCode==13) return topage(value);">/${PAGE.pages}&nbsp;
			<a href="javascript:void(0);" onclick="topage('${PAGE.currentpage+1}')">></a>&nbsp;
   			<a href="javascript:void(0);" onclick="topage('${PAGE.pages}')">>></a>	
   		</td>
   		</tr>
	</form>
	
	<form name="view" method="post" action="">
	    <table align="center" style="font-size:20px;width:70%;text-align:center" border="1">
	    	<tr>
	    		<th>楼名</th>
	    		<th>房间号</th>
	    		<th>等级</th>
	    		<th>操作</th>
	    	</tr>
	    	
	    	<c:forEach items="${LIST}" var="r" varStatus="status">
	    		<tr id="r_${status.index}">
	    			<td><input class='qf' id='r_${status.index}_1' disabled="disabled" value="${r.building}"></td>
					<td><input class='qf' id='r_${status.index}_2' disabled="disabled" value="${r.roomNum}"></td>
					<td><input class='qf' id='r_${status.index}_3' disabled="disabled" value="${r.rank}"></td>
					<td>
						
					</td>
	    		</tr>
	    	</c:forEach>	    	
      	</table>
	</form>	
	
	
	
</div>
<jsp:include page="user_menu.jsp" flush="true"/>
<%@ include file="user_menu.jsp" %>
</body>

<script type="text/javascript">

function topage(p) {
	var r=/^[1-9]\d*$/;
	
	if( (!r.test(p)) || (p<1) || (p>${PAGE['pages']}) ){ 
		//alert("wrong");
		document.forms['queryForm']['cp'].value='${PAGE.currentpage}';
		return false;
		} 
	document.forms['queryForm']['cp'].value=p;
	document.forms['queryForm'].submit();
 }

function check() {	
	var date=document.forms['queryForm']['date'].value;
	var time=document.forms['queryForm']['time'].value;
	if(date==""||time=="") {
		alert("请完整填写时间信息。");
		return false;
	}
	
	var db = new Date(date.replace(/-/g, "/"));
	if(new Date() > Date.parse(db)) {
	alert("选择日期至少为当日后一天。");
	return false;}
	
} 
	
</script>


</html>