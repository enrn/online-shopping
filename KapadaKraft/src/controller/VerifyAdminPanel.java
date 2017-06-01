package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DbMVC;
import db.DbMVC.*;
import db.Dbconnection;
import model.ModelMVC;

@WebServlet("/VerifyAdminPanel")
public class VerifyAdminPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyAdminPanel() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username").toString();
		String password=request.getParameter("password").toString();
		ModelMVC m=new ModelMVC();
		m.setUsername(username);
		m.setPassword(password);
		
		//String query="select username from tbl_admin where username=? and password=?";
		m=DbMVC.registerUser(m);
		System.out.println(username);
		System.out.println(password);
		
		if(m.isValid()){
			System.out.println("valid");
			HttpSession session=request.getSession(true);
			session.setAttribute("session_user", m);
			int role=m.getRole();
			if(role==1){
				response.sendRedirect("dashboard.jsp");
			}else if(role==2){
				response.sendRedirect("operator_dashboard.jsp");
			}
			
			
		}
		else
		{
			response.sendRedirect("index.jsp");
		}
		
	}

}
