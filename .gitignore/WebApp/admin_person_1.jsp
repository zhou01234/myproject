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
	<h style="font-family:黑体;font-size:30px;">&nbsp;个人信息</h>
	<hr>
	<table style="font-size:20px;" cellpadding="10">
		<tr><td>用户名</td><td>：</td><td><%=session.getAttribute("USERNAME")%></td></tr>
		
		<tr><td>姓&nbsp;名</td><td>：</td><td><%=session.getAttribute("NAME")%></td></tr>
		
		<tr><td>权&nbsp;限</td><td>：</td><td><%=session.getAttribute("RANK")%></td></tr>
		
		<tr><td>邮&nbsp;箱</td><td>：</td><td><%=session.getAttribute("EMAIL")%></td></tr>
	</table>
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>
</html>