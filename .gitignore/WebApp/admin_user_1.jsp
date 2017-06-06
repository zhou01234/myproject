<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>用户管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;用户列表</h>
	<hr>
	<form id="queryForm" name="queryForm" method="post" action="ListUser">
	<table>
		<td>&nbsp;用户名：</td><td><input type="text" name="username" value='${username}'></td>
		<td>&nbsp;权限：</td><td>
			<select name="rank">
				<option value="">-选择-</option>
	        	<option value="1">1</option>
	        	<option value="2">2</option>
	        	<option value="3">3</option>
	        	<option value="4">4</option>
	        	<option value="5">5</option>	            			
	    	</select>
	    	<script>
	    		var option=document.forms["query"]["rank"].options;        	
	    		for(i=1;i<=5;i++) {  
	    			if(option[i].value==${rank}) { 
	        			option[i].selected = true;}
	   			 }   	 
	    	</script>
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

<c:if test="${flag==1}">
	<Script>alert("修改成功。");</Script>
</c:if>
<c:if test="${flag==2}">
	<Script>alert("删除成功。")</Script>
</c:if>

</html>