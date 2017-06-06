<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/query.css" rel="stylesheet" type="text/css" />
<title>预约管理</title>

</head>

<body>
<div id="main">
	<h style="font-family:黑体;font-size:30px;">&nbsp;已预约</h>
	<hr>
	
	<form name="operate" method="post" action="">
	    <table align='center' style="font-size:20px;width:80%;text-align:center;" border="1" >
	    	<tr>
	    		<th width="10%">日期</th>
	    		<th width="10%">时段</th>
	    		<th width="10%">楼名</th>
	    		<th width="10%">房间号</th>
	    		<th width="10%">缘由</th>
	    		<th width="10%">操作</th>
	    	</tr>
	    	
	    	<c:forEach items="${LIST}" var="r" varStatus="status">
	    		<tr id="r_${status.index}">
	    			<td><input class='qf' id='r_${status.index}_1' disabled="disabled" value="${r.date}"></td>
					<td id='r_${status.index}_2'><c:choose>
						<c:when test="${r.time==1}">08:00-09:00 </c:when>
						<c:when test="${r.time==2}">10:00-11:30</c:when>
						<c:when test="${r.time==3}">13:30-15:00</c:when>
						<c:when test="${r.time==4}">15:30-17:00</c:when>
						<c:when test="${r.time==5}">17:30-19:00</c:when>
						<c:when test="${r.time==6}">19:30-21:00</c:when>
					</c:choose></td>
					<script>document.getElementById("r_"+${status.index}+"_2").value=${r.time}</script>
	    			<td><input class='qf' id='r_${status.index}_3' disabled="disabled" value="${r.building}"></td>
					<td><input class='qf' id='r_${status.index}_4' disabled="disabled" value="${r.roomNum}"></td>
					
					
					<td><a href="javascript:alert('${r.reason}')">查看</a></td>					
					<td>
						<input type="button" onclick="cancle(${status.index});" value="取消"/>
					</td>
					
					
					
	    		</tr>
	    	</c:forEach>	    	
      	</table>
	</form>	
    <br>
    
   		
</div>
<jsp:include page="user_menu.jsp" flush="true"/>
<%@ include file="user_menu.jsp" %>
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
 
 function cancle(id) {
	 var date=document.getElementById("r_"+id+"_1").value;
	 var time=document.getElementById("r_"+id+"_2").value;
	 var building=document.getElementById("r_"+id+"_3").value;
	 var roomNum=document.getElementById("r_"+id+"_4").value;
	 
	 var Form=document.createElement("form");
	 document.body.appendChild(Form);
	 Form.name="Form";
	 Form.method = "post";
	 Form.action = "RemoveUserReservation";
	 Form.style.display = "none";
	 var params={"date":date,
				 "time":time,	 		 
		 		 "building":building ,
		 		 "roomNum": roomNum, 
		 		 };
	    
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