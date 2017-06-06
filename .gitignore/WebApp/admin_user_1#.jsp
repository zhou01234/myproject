<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/query.css" rel="stylesheet" type="text/css" />
<title>用户管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;用户列表</h>
	<hr>
	<form name="query" method="post" action="ListUser" style="font-size:10px" >
		<table><tr>
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
	    <td><input type="submit" value="搜索"></td>
	    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td>
   			<a href="javascript:void(0);" onclick="topage('1')"><<</a>&nbsp;
   			<a href="javascript:void(0);" onclick="topage('${PAGE.currentpage-1}')"><</a>&nbsp;
   			<input type="text" name="cp" style="width:10%" 
   			value="${PAGE.currentpage}" onkeypress="if(event.keyCode==13) return topage(value);">/${PAGE.pages}&nbsp;
			<a href="javascript:void(0);" onclick="topage('${PAGE.currentpage+1}')">></a>&nbsp;
   			<a href="javascript:void(0);" onclick="topage('${PAGE.pages}')">>></a>	
   		</td>
   		</tr></table>
	</form>
	<br>
	
	<form name="operate" method="post" action="">
	    <table align="center" style="font-size:20px;width:80%;text-align:center" border="1">
	    	<tr>
	    		<th>用户名</th>
	    		<th>权限</th>
	    		<th>邮箱</th>
	    		<th>姓名</th>
	    		<th>操作</th>
	    	</tr>
	    	
	    	<c:forEach items="${LIST}" var="r" varStatus="status">
	    		<tr id="r_${status.index}">
	    			<td><input class='qf' id='r_${status.index}_1' disabled="disabled" value="${r.username}"></td>
					<td><input class='qf' id='r_${status.index}_2' style="display:;" 
							disabled="disabled" value="${r.rank}">
						<select class='qf' id='r_${status.index}_2#' style="display:none;">
							<option value="">-选择-</option>
	        				<option value="1">1</option>
	        				<option value="2">2</option>
	        				<option value="3">3</option>
	        				<option value="4">4</option>
	        				<option value="5">5</option>	            			
	        			</select>
	        		</td>
					<td><input class='qf' id='r_${status.index}_3' disabled="disabled" value="${r.email}"></td>
					<td><input class='qf' id='r_${status.index}_4' disabled="disabled" value="${r.name}"></td>
					<td>
						<input type="button" id="r_${status.index}_5" style="display:;"
							onclick="modify(${status.index});" value="修改"/>
						<input type="button" id="r_${status.index}_5#" style="display:none;"
							onclick="confirm(${status.index});" value="确定"/>
							
						<input type="button" id="r_${status.index}_6" style="display:;"
							onclick="remove0(${status.index});" value="删除"/>
					</td>
	    		</tr>
	    	</c:forEach>	    	
      	</table>
	</form>	
    <br>
    
   		
</div>
<jsp:include page="admin_menu.jsp" flush="true"/>
<%@ include file="admin_menu.jsp" %>
</body>

<script type="text/javascript">
 function topage(p) {
	var r=/^[1-9]\d*$/;
	
	if( (!r.test(p)) || (p<1) || (p>${PAGE['pages']}) ){ 
		//alert("wrong");
		document.forms['query']['cp'].value='${PAGE.currentpage}';
		return false;
		}
	
	document.forms['query']['cp'].value=p;
	document.forms['query'].submit();
 }

 
 function modify(id) {	
	document.getElementById("r_"+id+"_2").style.display = "none";
	document.getElementById("r_"+id+"_2#").style.display = "";
	document.getElementById("r_"+id+"_5").style.display = "none";
	document.getElementById("r_"+id+"_5#").style.display = "";
 }
 

 
 function confirm(id) {
	 var username=document.getElementById("r_"+id+"_1").value;
	 var rank=document.getElementById("r_"+id+"_2#").value;
	 var email=document.getElementById("r_"+id+"_3").value;
	 var name=document.getElementById("r_"+id+"_4").value;
	 if(rank=="") {
		 alert("请输入完整。");
		 return false;
	 }
	 
	 var Form=document.createElement("form");
	 document.body.appendChild(Form);
	 Form.name="Form";
	 Form.method = "post";
	 Form.action = "ModifyUser";
	 Form.style.display = "none";
	 var params={"username":username ,
		 		 "rank": rank,
		 		 "email":email ,
		 		 "name":name};
	    
	    for ( var k in params) {
	        var Input = document.createElement("input");
	        Input.name= k;
	        Input.value= params[k];
	        //alert(Input.value);
	        Form.appendChild(Input);
	    }
	    Form.submit();
	    document.body.removeChild(Form); 
 } 
 
 function remove0(id) {
	 var username=document.getElementById("r_"+id+"_1").value;
	 var Form=document.createElement("form");
	 document.body.appendChild(Form);
	 Form.name = "Form";
	 Form.method = "post";
	 Form.action = "RemoveUser";
	 Form.style.display = "none";
	 var params={"username":username};    
	    for ( var k in params) {
	        var Input = document.createElement("input");
	        Input.name= k;
	        Input.value= params[k];
	        //alert(Input.value);
	        Form.appendChild(Input);
	    }
	    Form.submit();
	    document.body.removeChild(Form); 
 }  

</script>


</html>