

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dbconnection;

/**
 * Servlet implementation class VerifyAdmin
 */
@WebServlet("/VerifyAdmin")
public class VerifyAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	String query;  
	Connection conn;  
	PreparedStatement pstmt;  
	ResultSet res;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		username=request.getParameter("username").toString();
		password=request.getParameter("password").toString();
		System.out.println(username);
		System.out.println(password);
		conn=Dbconnection.getConnection();
		query="select * from tbl_admin";
		try {
			pstmt=conn.prepareStatement(query);
			//pstmt.setString(1, username);
			//pstmt.setString(2, password);
			res=pstmt.executeQuery();
			int rowcount=res.getMetaData().getColumnCount();
			String address = null;
			while(res.next()){
				String uname=res.getString("username");
				String pass=res.getString("password");
				//request.setAttribute(uname, address);
				if(uname.equals(username)){
					
					address = "/dashboard.jsp";
					//response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
					
					//RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dashboard.jsp");
			        //requestDispatcher.forward(request, response);
				}
			     else{
					//response.sendRedirect(request.getContextPath()+"/index.jsp");
			    	 address = "/dashboard.jsp";
				}
			
			/*if(rowcount==1){
				//System.out.println("dashboard page.page..");
				//RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WebContent/dashboard.jsp");
		        //requestDispatcher.forward(request, response);
				response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
				}
			else {
				
			}*/
		}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
	        requestDispatcher.forward(request, response);
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		
		
		
		
		doGet(request, response);
	}

}
