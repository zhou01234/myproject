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
	    		<th width=20%>楼名</th>
	    		<th>房间号</th>
	    		<th width=30%>状态(1-可用,0-停用)</th>
	    		<th width=10%>等级</th>
	    		<th width=30%>操作</th>
	    	</tr>
	    	
	    	<c:forEach items="${LIST}" var="r" varStatus="status">
	    		<tr id="r_${status.index}">
	    			<td><input class='qf' id='r_${status.index}_1' disabled="disabled" value="${r.building}"></td>
					<td><input class='qf' id='r_${status.index}_2' disabled="disabled" value="${r.roomNum}"></td>
					<td><input class='qf' id='r_${status.index}_3' style="display:;" 
							disabled="disabled" value="${r.available}">
						<select class='qf' id='r_${status.index}_3#' style="display:none;">
							<option value=""> -选择- </option>
	        				<option value="0"> 0-停用 </option>
	            			<option value="1"> 1-可用 </option>	            			
	        			</select>
	        		</td>
					<td><input class='qf' id='r_${status.index}_4' style="display:;" 
							disabled="disabled" value="${r.rank}">
						<select class='qf' id='r_${status.index}_4#' style="display:none;">
							<option value="">-选择-</option>
	        				<option value="2">&nbsp;&nbsp;2</option>
	            			<option value="3">&nbsp;&nbsp;3</option>
	            			<option value="4">&nbsp;&nbsp;4</option>
	            			<option value="5">&nbsp;&nbsp;5</option>	            			
	        			</select>
	        		</td>
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
 var old_building;
 var old_roomNum;
 function modify(id) {
	 old_building=document.getElementById("r_"+id+"_1").value;
	 old_roomNum=document.getElementById("r_"+id+"_2").value;
	 
	document.getElementById("r_"+id+"_1").name='v_building';
	document.getElementById("r_"+id+"_1").removeAttribute("disabled");
	document.getElementById("r_"+id+"_2").removeAttribute("disabled");
	document.getElementById("r_"+id+"_3").style.display = "none";
	document.getElementById("r_"+id+"_3#").style.display = "";
	document.getElementById("r_"+id+"_4").style.display = "none";
	document.getElementById("r_"+id+"_4#").style.display = "";
	document.getElementById("r_"+id+"_5").style.display = "none";
	document.getElementById("r_"+id+"_5#").style.display = "";
 }
 

 
 function confirm(id) {
	 var building=document.getElementById("r_"+id+"_1").value;
	 var roomNum=document.getElementById("r_"+id+"_2").value;
	 var available=document.getElementById("r_"+id+"_3#").value;
	 var rank=document.getElementById("r_"+id+"_4#").value;
	 
	 var r=/^[1-9]\d*$/;
	 if(!r.test(roomNum)) {
	 	alert("房间号为正整数。");
	 	return false;
	 }
	 if(available==""||rank=="") {
		 alert("请输入完整。");
		 return false;
	 }
	 
	 var Form1=document.createElement("form");
	 document.body.appendChild(Form1);
	 Form1.name="Form1";
	 Form1.method = "post";
	 Form1.action = "ModifyRoom";
	 Form1.style.display = "none";
	 var params={"old_building":old_building,
				 "old_roomNum":old_roomNum,
		 		 "building":building ,
		 		 "roomNum": roomNum,
		 		 "available":available ,
		 		 "rank":rank};
	    
	    for ( var k in params) {
	        var Input1 = document.createElement("input");
	        Input1.name= k;
	        Input1.value= params[k];
	        //alert(Input1.value);
	        Form1.appendChild(Input1);
	    }
	    Form1.submit();
	    document.body.removeChild(Form1); 
 } 
 
 function remove0(id) {
	 var building=document.getElementById("r_"+id+"_1").value;
	 var roomNum=document.getElementById("r_"+id+"_2").value;
	 var Form2=document.createElement("form");
	 document.body.appendChild(Form2);
	 Form2.name = "Form2";
	 Form2.method = "post";
	 Form2.action = "RemoveRoom";
	 Form2.style.display = "none";
	 var params={"building":building,
				 "roomNum":roomNum};
	    
	    for ( var k in params) {
	        var Input2 = document.createElement("input");
	        Input2.name= k;
	        Input2.value= params[k];
	        //alert(Input2.value);
	        Form2.appendChild(Input2);
	    }
	    Form2.submit();
	    document.body.removeChild(Form2); 
 }

</script>


</html>