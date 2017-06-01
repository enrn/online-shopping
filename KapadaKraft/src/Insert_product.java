import java.io.IOException;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.MultipartConfig;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.Part;  
import db.Dbconnection;
  
@WebServlet("/Insert_product")  
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB  
public class Insert_product extends HttpServlet {  
  
    private static final long serialVersionUID = -1623656324694499109L;  
    private Connection conn;  
  
    public Insert_product() {  
        conn = Dbconnection.getConnection();  
    }  
  
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
          
        // gets values of text fields  
    	//int id=Integer.parseInt(request.getParameter("pid"));
    	String pname=request.getParameter("pname");
    	String ptype=request.getParameter("ptype");
    	String ptitle=request.getParameter("ptitle");
    	String pdescription=request.getParameter("pdescription");
    	//String image=request.getParameter("image");
    	String pcost=request.getParameter("pcost");
    	//int quantity=Integer.parseInt(request.getParameter("qunatity"));  
  
        InputStream inputStream = null;  
  
        // obtains the upload file part in this multipart request  
        Part filePart = request.getPart("image");  
        if (filePart != null) {  
            // debug messages  
            System.out.println(filePart.getName());  
            System.out.println(filePart.getSize());  
            System.out.println(filePart.getContentType());  
  
            // obtains input stream of the upload file  
            inputStream = filePart.getInputStream();  
        }  
  
        String message = null; // message will be sent back to client  
  
        try {  
            // constructs SQL statement  
        	//String sql1="SET IDENTITY_INSERT tbl_product_details ON";
        	// PreparedStatement statement1 = conn.prepareStatement(sql1);
        	// statement1.executeUpdate();
            String sql = "INSERT INTO tbl_product ( pname, ptype,ptitle,pdescription,image,pcost,quantity) values (?,?,?,?,?,?,?)";  
            PreparedStatement statement = conn.prepareStatement(sql);  
            //statement.setInt(1,id);
            statement.setString(1, pname); 
            statement.setString(2, ptype); 
            statement.setString(3, ptitle); 
            statement.setString(4, pdescription); 
            statement.setString(6, pcost); 
            statement.setInt(7, 2);
            if (inputStream != null) {  
                // fetches input stream of the upload file for the blob column  
                statement.setBlob(5, inputStream);  
            }  
  
            // sends the statement to the database server  
            int row = statement.executeUpdate();  
            if (row > 0) {  
                message = "Image is uploaded successfully into the Database";  
            }  
        } catch (SQLException ex) {  
            message = "ERROR: " + ex.getMessage();  
            ex.printStackTrace();  
        }  
        // sets the message in request scope  
        request.setAttribute("Message", message);  
  
        // forwards to the message page  
        getServletContext().getRequestDispatcher("/InsertProduct.jsp").forward(  
                request, response);  
    }  
} 