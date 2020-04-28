package Model;

import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Joshua Haines
 */
public class DBInteract {

    private String className=null;
    private static String url=null;
    private static String user = null;
    private static String password = null;
    
    public DBInteract() {
        try {
            ResourceBundle resources;
            InputStream in = null;
            ResourceBundle newResources;

            in = ClassLoader.getSystemResourceAsStream("db.properties");

            resources = new PropertyResourceBundle(in);

            in.close();

            className = resources.getString("jdbc.driver");
            url = resources.getString("jdbc.url");
            System.out.println(url);
            user = resources.getString("jdbc.user");
            password = resources.getString("jdbc.password");
        }
        catch (Exception exp) {
            System.out.println("Couldn't load resources." + exp);
            System.exit(-1);
        }
        
        try {
            Class.forName(className);
        }
        catch (Exception e) {
            System.out.println("Failed to load driver.");
            return;
        }
    }
    
    /**
     * Gets integer from third column based on data from first column
     * 
     */
    public static int findRow(String column1) {
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM TestTable " +
                        "WHERE Column1 = '" + column1 + "'");
            
            if (rs.next() == false) { 
                     System.out.println("Data not found");
            } else {
               return rs.getInt(3);       
            }

            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch2");
            System.out.println(e);
        }
        
        return -1;
    }
}
