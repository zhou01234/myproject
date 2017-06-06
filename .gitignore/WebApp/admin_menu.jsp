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
    <li><a href="admin_index.jsp"  class="nodrop">首&nbsp;页</a></li>
    
    <li><a href="#"  class="drop">个人信息</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="admin_person_1.jsp">查看信息</a></li>
            <li><a href="admin_person_2.jsp">修改密码</a></li>
            <li><a href="admin_person_3.jsp">修改邮箱</a></li>
          </ul>
        </div>
      </div>
    </li> 
    
    <li><a href="#"  class="drop">房间管理</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="admin_room_1.jsp">房间查询</a></li>
            <li><a href="admin_room_2.jsp">房间添加</a></li>
          </ul>
        </div>
      </div>
    </li>   
    
    <li><a href="#"  class="drop">用户管理</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="admin_user_1.jsp">用户查询</a></li>
            <li><a href="admin_user_2.jsp">用户添加</a></li>
          </ul>
        </div>
      </div>
    </li>
    
    <li><a href="#"  class="drop">预约管理</a>
      <div class="dropdown_1column">
        <div class="col_1">
          <ul class="simple">
            <li><a href="admin_order_1.jsp">申请列表</a></li>
            <li><a href="admin_order_2.jsp">预约列表</a></li>
          </ul>
        </div>
      </div>
    </li>
     
    <li><a href="LogOut" class="nodrop">退&nbsp;出</a></li>
  </ul> 
</div>
</body>




</html>