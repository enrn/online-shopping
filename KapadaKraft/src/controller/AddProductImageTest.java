package controller;

import java.io.File;

import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

import db.DbMVC;
import model.ProductModel;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProductImageTest")
public class AddProductImageTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddProductImageTest() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String p_cat=request.getParameter("pcategory");
		int i=0;
		DateFormat formater=new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date con_date = null;
		int pcost = 0,quantity=0;
		try{
			String imagefile=null;
			String itemName=null;
			boolean isMultipart=ServletFileUpload.isMultipartContent(request);
			if(!isMultipart){
				System.out.println("no imageupload");
			}else{
				FileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload((org.apache.commons.fileupload.FileItemFactory) factory);
				List items=null;
				try{
					items=upload.parseRequest(request);
					
				}catch(FileUploadException e){
					
				}
				Iterator itr=items.iterator();
				while(itr.hasNext()){
					FileItem item=(FileItem) itr.next();
					if(item.isFormField()){
						String name=item.getFieldName();
						String value=item.getString();
						if(name.equals("ImageFile"))
						{
						imagefile=value;
						}
						 
						}else
						{
							try
							{
							itemName = item.getName();
							File savedFile = new File(config.getServletContext().getRealPath(itemName));
							item.write(savedFile);
							}catch(Exception e){
								
							}
					}
				}
			}
		}catch(Exception e){
			
		}
		
		String pname=request.getParameter("pname");
    	String ptitle=request.getParameter("ptitle");
    	String pdescription=request.getParameter("pdesc");
    	String image=request.getParameter("image");
    	System.out.println(pname+ptitle+pdescription+image);
    	try{
    		 pcost=Integer.parseInt(request.getParameter("pcost"));
        	 quantity=Integer.parseInt(request.getParameter("quantity"));
    	}catch(NumberFormatException e){
    		System.out.println("noparsing:"+e.getMessage());
    	}
    	
    	try {
    		
			java.util.Date update=formater.parse(request.getParameter("uploaded_date"));
			con_date=new java.sql.Date(update.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
    	
    	System.out.println(con_date);System.out.println("c"+pcost);System.out.println("q"+quantity);
    	ProductModel pm=new ProductModel();
    	pm.setPname(pname);
    	pm.setPtitle(ptitle);
    	pm.setPdesc(pdescription);
    	pm.setCost(pcost);
		pm.setDate(con_date);
		pm.setQuantity(quantity);
		pm.setImage(image);
		i=DbMVC.addProduct(pm);
		if(i!=0){
			System.out.println("updated sucessfully.");
			//response.sendRedirect("AddProduct.jsp");
		}else{
			System.out.println("failure...");
		}
		
	}

}
