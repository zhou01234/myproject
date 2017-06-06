<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;修改密码</h>
	<hr>
	<table style="font-size:20px;" cellpadding="10">
		<tr><td>旧密码</td><td>：</td><td><input type="password" name="oldpwd"></td></tr>
		
		<tr><td>新密码</td><td>：</td><td><input type="password" name="newpwd1"></td></tr>
		
		<tr><td>新密码确认</td><td>：</td><td><input type="password" name="newpwd2"></td></tr>
		
	</table>
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>
</html>