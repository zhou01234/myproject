<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>预约管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;申请列表</h>
	<hr>
	<form id="queryForm" name="queryForm" method="post" action="ListRequest">
	<table>
		<td>&nbsp;用户：</td><td><input type="text" name="username" value="${username}"></td>
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
	    </td>		
		<td>&nbsp;&nbsp;</td>
	    <td><input type="submit" value="搜索" name="submit"></td>
	    <td>&nbsp;&nbsp;</td>	    
	</table>    	
	</form>
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>
<c:if test="${flag==0}">
	<Script>alert("预约冲突，申请失败，已取消。")</Script>
</c:if>
<c:if test="${flag==1}">
	<Script>alert("已通过用户"+document.forms['queryForm']['username'].value+"的预约。")</Script>
</c:if>
<c:if test="${flag==2}">
	<Script>alert("用户"+document.forms['queryForm']['username'].value+"的预约申请取消。")</Script>
</c:if>
<c:if test="${flag==3}">
	<Script>alert("已通过用户"+document.forms['queryForm']['username'].value+"的长期预约。")</Script>
</c:if>


</html>