<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/adminStyle.css">
<title>Add Category</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<div id="dashboardcontent">
<center>
<h2>Add the category for product</h2>
<form action="AddCategory" method="get">
<table>
<tr><td>Category name:</td><td><input type="text" name="cat_name"></td></tr>
<tr><td></td><td><input type="submit" name="submit"></td></tr>

</table>
</form>
</center>
</div>
</body>
</html>