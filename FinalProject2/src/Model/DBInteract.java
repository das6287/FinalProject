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
     * Gets data for the world coronavirus cases, recoveries, and deaths, from
     * the CoronavirusWorld table
     * 
     * @param date Date for the data
     * @param option 0 for # of cases, 1 for # of recoveries, 2 for # of deaths
     * @return World coronavirus cases, recoveries, or deaths on specified date
     */
    public static int getWorldData(String date, int option) {
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusWorld " +
                        "WHERE Date = '" + date + "'");
            
            if (rs.next() == false) { 
                     System.out.println("Date not found: " + date);
            } else if (option >= 0 && option <= 2) {
               return rs.getInt(option + 2);       
            } else {
               System.out.println("Invalid option");
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
    
    /**
     * Gets data for the world growth rate of coronavirus from the 
     * CoronavirusWorld table
     * 
     * @param date Date for the data
     * @return Growth percent of coronavirus from previous day
     */
    public static double getWorldGrowthRate(String date) {
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusWorld " +
                        "WHERE Date = '" + date + "'");
            
            if (rs.next() == false) { 
                     System.out.println("Date not found: " + date);
            } else {
               return rs.getDouble(5);       
            }

            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch3");
            System.out.println(e);
        }
        
        return -1;
    }
    
    /**
     * Gets data for a specified country's coronavirus cases, recoveries, and 
     * deaths, from the CoronavirusCountries table
     * 
     * @param date Date for the data
     * @param country Country for the data
     * @param option 0 for # of cases, 1 for # of recoveries, 2 for # of deaths
     * @return Country coronavirus cases, recoveries, or deaths on specified date
     */
    public static int getCountryData(String date, String country, int option) {
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
                "WHERE Date = '" + date + "' AND Country = '" + country + "'");
            
            if (rs.next() == false) { 
                     System.out.println("Data not found for: " + country +
                        " on " +  date);
            } else if (option >= 0 && option <= 2) {
               return rs.getInt(option + 3);       
            } else {
               System.out.println("Invalid option");
            }

            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch4");
            System.out.println(e);
        }
        
        return -1;
    }
    
//    /**
//     * Calculates growth rate of coronavirus in each country frmo previous day
//     * 
//     * @param date Date for the data
//     * @param country Country for the data
//     * @return Percentage growth of coronavirus from previous day in specified country
//     */
//    public static double getCountryGrowthRate(String date, String country) {
//        
//        try {
//            Connection con = DriverManager.getConnection(url,user,password);  
//
//            Statement stmt = con.createStatement();
//            
//            int dateCases = -1;
//            int previousCases = -1;
//            
//            String [] dateArray = date.split("/");
//            String previousDate;
//            
//            if(dateArray.)
//            
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
//                "WHERE Date = '" + date + "' AND Country = '" + country + "'");
//            
//            if (rs.next() == false) { 
//                     System.out.println("Data not found for: " + country +
//                        " on " +  date);
//            } else {
//               dateCases = rs.getInt(3);       
//            }
//            
//            rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
//                "WHERE Date = '" + date + "' AND Country = '" + country + "'");
//            
//            if (rs.next() == false) { 
//                     System.out.println("Data not found for: " + country +
//                        " on " +  date);
//            } else {
//               dateCases = rs.getInt(3);       
//            }
//
//            stmt.close();
//
//            con.close();
//        }
//        catch (Exception e) {
//            System.out.println("catch4");
//            System.out.println(e);
//        }
//        
//        return -1;
//    }
}