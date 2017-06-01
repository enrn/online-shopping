package controller;

import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbMVC;
import model.ProductModel;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddProduct() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String p_cat=request.getParameter("pcategory");
		int i=0;
		DateFormat formater=new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date con_date = null;
		int pcost = 0,quantity=0;
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
