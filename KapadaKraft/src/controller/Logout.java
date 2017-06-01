package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ModelMVC;
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		ModelMVC userinfo=new ModelMVC();
		userinfo.removeUsername();
		userinfo.removePassword();
		userinfo.removeRole();
		HttpSession session=request.getSession(false);
		session.removeAttribute("session_user");
		session.invalidate();
		response.sendRedirect("index.jsp");
		}
		catch(Exception e){
			System.out.println("you cannot log out..");
		}
		
	}

	

}
