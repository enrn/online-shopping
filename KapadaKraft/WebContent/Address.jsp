<%@page import="db.Dbconnection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%Connection con=null; %>
    <% String fname=request.getParameter("customer_fname");
   	   String lname=request.getParameter("customer_lname");
       String email=request.getParameter("email");
       String phone=request.getParameter("phone");
       out.print(fname+lname+email+phone);
       
       session.setAttribute("session_fname",fname);
       session.setAttribute("session_lname",lname);
       session.setAttribute("session_email",email);
       session.setAttribute("session_phone",phone);
    	con=Dbconnection.getConnection(); 
    	String getcountry="select * from tbl_country";
    	Statement s=con.createStatement();
    
    	ResultSet rs=s.executeQuery(getcountry); 
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Insert" method="post">
<table>
<tr><td>Country</td><td><select name="country_name"><option>--select country--</option>
<%while(rs.next())
	{ 
String value=rs.getString("country_name");%>
<option value="<%=value %>"><%=value %></option><%}rs.close();s.close();con.close();%>
</select></td></tr>
<tr><td>State</td><td><select name="state"><option>---State---</option>
									<option value="">state name</option>
				</select></td></tr>
<tr><td>City</td><td><select name="city"><option>---City---</option>
									<option value="">city name</option>
				</select></td></tr>
<tr><td>street</td><td><input type="text" name="street"></td></tr>
<tr><td>zip code</td><td><input type="text" name="zipcode"></td></tr>
<tr><td><a href="CustomerForm.jsp"><input type="button" name="<-back"></a></td>
<td><input type="submit" name="submit" value="next->"></td></tr>
</table>
</form>
</body>
</html>