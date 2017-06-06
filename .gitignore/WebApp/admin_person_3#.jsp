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
	<h style="font-family:黑体;font-size:30px;">&nbsp;修改邮箱</h>
	<hr>
	<form id="emailForm" name="emailForm" method="post" action="ModifyEmail" onsubmit="return check()">
		<table style="font-size:20px;" cellpadding="10">
			<tr><td>邮&nbsp;箱</td><td>：</td><td><%=session.getAttribute("EMAIL") %></td>		
			<tr><td>验 证 码</td><td>：</td><td><input type="text" name="code"></td>
				<td id="alert" style="font-size:20px;color:red;visibility:hidden;">验证码错误</td>
			</tr>
			
			<tr><td></td><td></td>
				<td><input type="submit" value="确定" id="btn"></td></tr>
		</table>
	</form>
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>

<script type="text/javascript">
function check() {
	String c1=document.forms["emailForm"]["code"].value;
	String c2=session.getAttribute("CODE");
	if(!c1.equals(c2)) {
		document.getElementById("alert").style.visibility="visible";
		return false;
	}
}
</script>


</html>