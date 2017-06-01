<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="model.ModelMVC" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/adminStyle.css">
<title>Dashboard</title>
</head>
<body>
<div id="header">
this is header
<div id="username_display">
<% ModelMVC sessioncontent=(ModelMVC)session.getAttribute("session_user"); %>
<b>Welcome,</b><br><%=  sessioncontent.getUsername()+sessioncontent.getRole()%>
<a href="Logout">|Logout</a>
</div>
</div>
<div id="dashboardcontent">

<div id="menu"><a href="Product_form.jsp">Update Product Catlog</a></div>
<div id="menu"><a href="AddCategory.jsp">ADD Category</a></div>
<div id="menu"><a href="">Manage User</a></div>
</div>

</body>
</html>