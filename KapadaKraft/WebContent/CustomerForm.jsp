<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="db.Dbconnection"%>
	<%@page import="java.sql.*"%>
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
    	String getstate="select * from tbl_state";
    	String getcity="select * from tbl_city";
    	Statement s=con.createStatement();
    	Statement state=con.createStatement();
    	Statement city=con.createStatement();
    
    	ResultSet rs=s.executeQuery(getcountry); 
    	ResultSet rstate=state.executeQuery(getstate); 
    	ResultSet rcity=city.executeQuery(getcity); 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>customer Form</title>
</head>
<body>
<fieldset>
<h2>customer details</h2>

<form action="Insert" method="post" name="cform">
<table>
<tr><td>First Name</td><td><input type="text" name="customer_fname"></td></tr>
<tr><td>Last Name</td><td><input type="text" name="customer_lname"></td></tr>
<tr><td>email</td><td><input type="text" name="email"></td></tr>
<tr><td>phone</td><td><input type="text" name="phone"></td></tr>
<tr><td>Country</td><td><select name="country_name"><option>--select country--</option>
<%while(rs.next())
	{ 
String value=rs.getString("country_name");
int countryid=rs.getInt("country_id");%>
<option value="<%=countryid %>"><%=value %></option><%}rs.close();s.close();%>
</select></td></tr>
<tr><td>State</td><td><select name="state_name"><option>---State---</option>
									<%while(rstate.next())
	{ 
String values=rstate.getString("state_name");
int stateid=rstate.getInt("state_id");%>
<option value="<%=stateid %>"><%=values %></option><%}rstate.close();state.close();%>
</select></td></tr>
<tr><td>City</td><td><select name="city_name"><option>---State---</option>
<%while(rcity.next())
	{ 
String valuec=rcity.getString("city_name");
int cityid=rcity.getInt("city_id");%>
<option value="<%=cityid %>"><%=valuec %></option><%}rcity.close();city.close();con.close();%>
</select></td></tr>
<tr><td>street</td><td><input type="text" name="street"></td></tr>
<tr><td>zip code</td><td><input type="text" name="zipcode"></td></tr>
<tr><td>Username</td><td><input type="text" name="username"></td></tr>
<tr><td>Password</td><td><input type="password" name="password"></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="cpassword"></td></tr>
<tr><td></td><td><input type="submit" name="submit" value="submit"></td></tr>

</table>
</form>

</fieldset>


</body>
</html>