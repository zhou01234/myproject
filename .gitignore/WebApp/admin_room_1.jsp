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
	<h style="font-family:黑体;font-size:30px;">&nbsp;房间列表</h>
	<hr>
	<form name="query" method="post" action="ListRoom" style="font-size:10px" >
		<table><tr>
		<td>&nbsp;楼名：</td><td><input type="text" name="building" value="${building}"></td>
		<td>&nbsp;房间号：</td><td><input type="text" name="roomNum" value="${roomNum}"></td>
		<td>&nbsp;状态：</td><td>
			<select name="available">
				<option value=""> -选择- </option>
	        	<option value="0"> 0-停用 </option>
	        	<option value="1"> 1-可用 </option>	            			
	        </select>
	        <script>
	    		var option1=document.forms["query"]["available"].options;
	    		for(i=1;i<=2;i++) {  
	    			if(option1[i].value==${available}) { 
	        			option1[i].selected = true;}
	    		}
	    	</script>
		</td>
		
		<td>&nbsp;等级：</td><td>
			<select name="rank">
				<option value="">-选择-</option>
	        	<option value="2">2</option>
	        	<option value="3">3</option>
	        	<option value="4">4</option>
	        	<option value="5">5</option>	            			
	    	</select>   
	    	<script>
	    		var option2=document.forms["query"]["rank"].options;        	
	    		for(i=1;i<=4;i++) {  
	    			if(option2[i].value==${rank}) { 
	        			option2[i].selected = true;}
	   			 }   	 
	    	</script>
	    </td>
		<td>&nbsp;&nbsp;</td>
	    <td><input type="submit" value="搜索"></td>
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