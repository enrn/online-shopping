<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/adminStyle.css">
<title>Product details</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<div id="dashboardcontent">
<center>
<h2>Insert product details</h2>
<form action="AddProduct" method="post" enctype="multipart/form-data">

product name:<input type="text" name="pname"><br>
product Title:<input type="text" name="ptitle"><br>
product Description:<textarea name="pdesc" rows="10" cols="25"></textarea><br>
product cost:<input type="text" name="pcost"><br>
Uploaded dtae:<input type="text" name="uploaded_date"><br>
image<input type="file" name="image">

Quantity:<input type="text" name="quantity"><br>

<input type="submit" name="submit">


</form>
</center>
</div>
</body>
</html>