<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>首页</title>

</head>

<body>
<div id="main">
	<h style="font-family:宋体;font-size:30px;">&nbsp;&nbsp;
		你好，${NAME}(${USERNAME})</h>
	<hr>
	<table border="0" style="color:red;font-family:黑体;font-size:20px;">
	<th>
	&nbsp;通知：<br> &nbsp;&nbsp;	
	</th>
	<c:forEach items="${MESSAGE}" var="ms" varStatus="status">
		<tr>
		<th>${status.index+1}.</th>
		<th>${ms}</th>
		</tr>
	</c:forEach>
	</table>
		
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>




</html>