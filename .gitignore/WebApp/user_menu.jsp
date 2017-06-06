<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>用户菜单</title>

</head>

<body>

<div id="menu">
  <ul>
    <li><a href="user_index.jsp"  class="nodrop">首&nbsp;页</a></li>
    <li><a href="#"  class="drop">个人信息</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="user_person_1.jsp">查看信息</a></li>
            <li><a href="user_person_2.jsp">修改密码</a></li>
            <li><a href="user_person_3.jsp">修改邮箱</a></li>
          </ul>
        </div>
      </div>
    </li> 
    <li><a href="user_query.jsp"  class="nodrop">房间查询</a></li>
    <li><a href="#"  class="drop">房间预约</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="user_reserv_1.jsp">提交申请</a></li>
            <li><a href="ListUserRequest">申 请 中</a></li>
            <li><a href="ListUserReservation">已 预 约</a></li>
          </ul>
        </div>
      </div>
    </li>    
    <li><a href="LogOut" class="nodrop">退&nbsp;出</a></li>
  </ul> 
</div>
</body>




</html>