
package db;
import java.sql.*;
public class Dbconnection {
	Connection con=null;
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



       
}






