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
	<form id="emailForm" name="emailForm" method="post" action="SendCode" onsubmit="return check()">
		<table style="font-size:20px;" cellpadding="10">		
			<tr><td>邮&nbsp;箱</td><td>：</td><td><input type="text" name="email"></td>
			<td id="alert" style="font-size:20px;color:red;visibility:hidden;">格式不正确</td>
			</tr>
			
			<tr><td></td><td></td>
				<td><input type="submit" value="获取验证码" id="btn"></td></tr>
		</table>
	</form>
</div>
<jsp:include page="user_menu.jsp" flush="true"/>
<%@ include file="user_menu.jsp" %>
</body>

<script type="text/javascript">
function check() {
	 var patrn  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	 var mail = document.forms["emailForm"]["email"].value;
	 if (patrn.test(mail)) return true;
	 else {
	 document.getElementById("alert").style.visibility="visible";
	 return false;}
}


</script>


</html>