<%@page import="model.ModelMVC" %> 
<div id="header">
this is header
<div id="username_display">
<% ModelMVC sessioncontent=(ModelMVC)session.getAttribute("session_user"); %>
<b>Welcome,</b><br><%=  sessioncontent.getUsername()+sessioncontent.getRole()%>
<a href="Logout">|Logout</a>
</div>
</div>