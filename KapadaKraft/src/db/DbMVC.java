package db;

import model.ModelMVC;
import model.ProductModel;

import java.sql.*;

public class DbMVC {
	
	
    public static Connection getConnection(){
                              try {
              
                     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    String url = "jdbc:sqlserver://localhost:1433;" +
					   "databaseName=kapadakraft;user=sa;password=1narayan";
					Connection con = DriverManager.getConnection(url);
                    return con;
                  
            }
            catch (SQLException ex) {
                    while (ex != null) {
                            System.out.println ("SQL Exception:  " +

                                    ex.getMessage ());
                            ex = ex.getNextException ();
                    }
            }
            catch (java.lang.Exception ex) {
                    ex.printStackTrace ();
            }
							return null;
    }

    static ResultSet r=null;
	public static ModelMVC registerUser(ModelMVC m) {
			boolean more=false;
			PreparedStatement p=null;
			
			Connection con=getConnection();
			String query="select * from tbl_admin where username=? and password=?";
			
			try {
			    p=con.prepareStatement(query);
				p.setString(1,m.getUsername().toString());
				p.setString(2,m.getPassword().toString());
				r=p.executeQuery();
			    more=r.next();
			    System.out.println("sql="+query);
			    System.out.println("sql="+more);
				if(!more){
					System.out.println("user do not exists");
					m.setValid(false);
										
				}
				else if(more){
					System.out.println("welcome..");
					String uname=r.getString("username");
					String pword=r.getString("password");
					int role=r.getInt("role");
					m.setUsername(uname);
					m.setPassword(pword);
					m.setRole(role);
					m.setValid(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					r.close();
					p.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return m; 
	}
	
	public static void addCategory(ModelMVC catobj){
		//int i=0;
		Connection con=null;
		PreparedStatement pcat=null;
		String cat_query="insert into tbl_category values(?)";
		
		con=getConnection();
		try {
			pcat=con.prepareStatement(cat_query);
			pcat.setString(1, catobj.getCategory());
			pcat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				
				pcat.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static int addProduct(ProductModel pm) {
		Connection con=null;
		int i=0;
		System.out.println(".............."+pm.getPname());
		PreparedStatement product=null;
		String p_query="insert into tbl_cat_product values(?,?,?,?,?,?,?,?)";
		con=getConnection();
		try {
			product=con.prepareStatement(p_query);
			product.setInt(1, 1);
			product.setString(2, pm.getPname());
			product.setString(3, pm.getPtitle());
			product.setString(4, pm.getPdesc());
			product.setDouble(5, pm.getCost());
			product.setDate(6, pm.getDate());
			product.setInt(7, pm.getQuantity());
			product.setString(8, pm.getImage());
			i=product.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				
				product.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}



}
