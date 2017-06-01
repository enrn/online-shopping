

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//import db.Dbconnection;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dbconnection;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String fname,lname,email,phone,username,password,zipcode,street; 
	int countryid,stateid,cityid,customer_id;
	String query;  
	Connection conn;  
	PreparedStatement pstmt;  
	ResultSet res;  
	Dbconnection dbconn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");  
    	PrintWriter out = response.getWriter();  
    	try { 
    		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

           // String url = "jdbc:sqlserver://localhost:1433;" +
			 //  "databaseName=kapadakraft;user=sa;password=1narayan";
			  // conn = DriverManager.getConnection(url);
				dbconn=new Dbconnection();
    			fname=request.getParameter("customer_fname").toString();  
    			lname=request.getParameter("customer_lname").toString();  
    			email=request.getParameter("email").toString();
    			phone=request.getParameter("phone");
    			username=request.getParameter("username").toString(); 
    			password=request.getParameter("password").toString();
    			countryid=Integer.parseInt(request.getParameter("country_name"));
    			stateid=Integer.parseInt(request.getParameter("state_name"));
    			cityid=Integer.parseInt(request.getParameter("city_name"));
    			zipcode=request.getParameter("zipcode");
    			
				//store in session
    			
				
    			conn=Dbconnection.getConnection();  
    			query= "insert into tbl_customer_details(customer_id,customer_fname,customer_lname,email,phone)values(?,?,?,?,?)";
    			pstmt=conn.prepareStatement(query);  
    			pstmt.setInt(1, 6);
    			pstmt.setString(2, fname);
    			pstmt.setString(3, lname);
    			pstmt.setString(4, email);
    			pstmt.setString(5, phone);
    			pstmt.executeUpdate();  
    			pstmt.close();
    			
    			String queryid="select customer_id from tbl_customer_details where email=?";
    			PreparedStatement pid=conn.prepareStatement(queryid);
    			pid.setString(1, email);
    			ResultSet r=pid.executeQuery();
    			while(r.next()){
    				customer_id=r.getInt("customer_id");
    			}
    			
    			String queryaddress="insert into tbl_customer_address(address_id,customer_id,country_id,state_id,city_id,street,zipcode)values(?,?,?,?,?,?,?)";
    			PreparedStatement paddress=conn.prepareStatement(queryaddress);
    			paddress.setInt(1, 1);
    			paddress.setInt(2, customer_id);
    			paddress.setInt(3, countryid);
    			paddress.setInt(4, stateid);
    			paddress.setInt(5, cityid);
    			paddress.setString(6, street);
    			paddress.setString(7, zipcode);
    			paddress.executeUpdate();
    			paddress.close();
    			
    			conn.close();
    			} catch(Exception e){  

    			    System.out.println("Error..."+e.getMessage());  

    			}finally {  

    			  
    			} 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	

}
