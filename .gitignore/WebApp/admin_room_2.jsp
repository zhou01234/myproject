<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间管理</title>
</head>
<body>

<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;添加房间</h>
	<hr>
	<form id="addRoom" name="addRoom" method="post" action="AddRoom" onsubmit="return check();">
		<table style="font-size:20px;" cellpadding="10">		
			<tr><td>楼&nbsp;名</td><td>：</td><td><input type="text" name="building" value="${building}"></td>
			</tr>
			<tr><td> 房 间 号 </td><td>：</td><td><input type="text" name="roomNum" value="${roomNum}"></td>
				<td id="alert" style="font-size:20px;color:red;visibility:hidden;">格式不正确</td></tr>
			<tr><td> 是否可用 </td><td>：</td>
				<td><select name="available">
					<option value=""> -选择- </option>
	        		<option value="0"> 0-停用 </option>
	            	<option value="1"> 1-可用 </option>
				</select></td>
			</tr>
			<tr><td>等&nbsp;级</td><td>：</td>
				<td><select name="rank">
					<option value=""> -选择- </option>
	        		<option value="2">&nbsp;&nbsp;2</option>
	            	<option value="3">&nbsp;&nbsp;3</option>
	            	<option value="4">&nbsp;&nbsp;4</option>
	            	<option value="5">&nbsp;&nbsp;5</option>
				</select></td>
			</tr>
			<tr></tr>
			<tr><td></td>
				<td><input type="submit" value="确定" id="btn"></td></tr>
		</table>
	</form>
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>

<c:if test="${flag==false}">
	<Script>alert("房间已存在。")</Script>
</c:if>
<c:if test="${flag==true}">
	<Script>alert("添加成功。")</Script>
</c:if>


<script type="text/javascript">

function check() {
	var r=/^[1-9]\d*$/;		
	var n=document.forms["addRoom"]["roomNum"].value;	 
	 var b=document.forms["addRoom"]["building"].value;
	 var a=document.forms["addRoom"]["available"].value;
	 var g=document.forms["addRoom"]["rank"].value;
	 if(n==""||b==""||a==""||g=="") {
		 alert("请输入完整。");
		 return false;}
	 if (!r.test(n)) {
		 document.getElementById("alert").style.visibility="visible";
		 return false;}
}


</script>


</html>