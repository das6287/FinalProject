package Model;

import java.util.*;
import java.io.*;
import java.sql.*;
import java.lang.Math;

/**
 * Interacts with the database, gets data from CoronaDB and makes calculations 
 * with it
 * 
 * @author Joshua Haines
 * @version 1.5
 * @date 5/5/19
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

            /*in = ClassLoader.getSystemResourceAsStream("db.properties");
            System.out.print("Test");
            resources = new PropertyResourceBundle(in);

            in.close(); */

            className = "org.apache.derby.jdbc.ClientDriver";
            //System.out.println(className);
            url = "jdbc:derby://localhost:1527/CoronaDB";
            //System.out.println(url);
            user = "app";
            password = "app";
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
    public static String getWorldGrowthRate(String date) {
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusWorld " +
                        "WHERE Date = '" + date + "'");
            
            if (rs.next() == true) { 
               double percentRounded = Math.round(rs.getDouble(5) *  1000)/1000.0;
               return String.valueOf(percentRounded);     
            }
                        
            stmt.close();

            con.close();
            
            return ("Date not found: " + date);
        }
        catch (Exception e) {
            System.out.println("catch3");
            System.out.println(e);
        }
        
        return ("Error receiving data");
    }
    
    /**
     * Calculates worldwide growth rate of deaths from coronavirus from previous day
     * 
     * @param date Date for the data
     * @return Percentage growth of deaths from previous day
     */
    public static String getWorldDeathRate(String date) {
        
        double percentIncrease = -1;
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();
            
            double dateDeaths = -1;
            double previousDeaths = -1;
            
            String previousDate = getPreviousDate(date);

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusWorld " +
                "WHERE Date = '" + date + "'");
            
            if (rs.next() == false) { 
                     return ("Data not found for: " + date);
            } else {
               dateDeaths = rs.getInt(4);
            }
            
            rs = stmt.executeQuery("SELECT * FROM CoronavirusWorld " +
                "WHERE Date = '" + previousDate + "'");
            
            if (rs.next() == false) { 
                     return ("Data not found for: " + date);
            } else {
               previousDeaths = rs.getInt(4);     
            }
            
            if(previousDeaths == 0) {
                return ("Growth rate undefined");
            }
            
            percentIncrease = ((dateDeaths/previousDeaths - 1) * 100);
      
            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch5");
            System.out.println(e);
        }
        
        double percentRounded = Math.round(percentIncrease *  1000)/1000.0;
        return String.valueOf(percentRounded);
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
    
    /**
     * Calculates growth rate of coronavirus in each country from previous day
     * 
     * @param date Date for the data
     * @param country Country for the data
     * @return Percentage growth of coronavirus from previous day in specified country
     */
    public static String getCountryGrowthRate(String date, String country) {
        
        double percentIncrease = -1;
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();
            
            double dateCases = -1;
            double previousCases = -1;
            
            String previousDate = getPreviousDate(date);

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
                "WHERE Date = '" + date + "' AND Country = '" + country + "'");
            
            if (rs.next() == false) { 
                     return ("Data not found for: " + country +
                        " on " +  date);
            } else {
               dateCases = rs.getInt(3);       
            }
            
            rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
                "WHERE Date = '" + previousDate + "' AND Country = '" + country + "'");
            
            if (rs.next() == false) { 
                     return ("Previous date not found for: " + country +
                        " on " +  date);
            } else {
               previousCases = rs.getInt(3);     
            }
            
            if(previousCases == 0) {
                return ("Growth rate is undefined");
            }
            
            percentIncrease = ((dateCases/previousCases - 1) * 100);
      
            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch5");
            System.out.println(e);
        }
        
        double percentRounded = Math.round(percentIncrease *  1000)/1000.0;
        return String.valueOf(percentRounded);
    }
    
    /**
     * Calculates growth rate of deaths from coronavirus in each country from previous day
     * 
     * @param date Date for the data
     * @param country Country for the data
     * @return Percentage growth of coronavirus from previous day in specified country
     */
    public static String getCountryDeathRate(String date, String country) {
        
        double percentIncrease = -1;
        
        try {
            Connection con = DriverManager.getConnection(url,user,password);  

            Statement stmt = con.createStatement();
            
            double dateDeaths = -1;
            double previousDeaths = -1;
            
            String previousDate = getPreviousDate(date);

            ResultSet rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
                "WHERE Date = '" + date + "' AND Country = '" + country + "'");
            
            if (rs.next() == false) { 
                     return ("Data not found for: " + country +
                        " on " +  date);
            } else {
               dateDeaths = rs.getInt(5);       
            }
            
            rs = stmt.executeQuery("SELECT * FROM CoronavirusCountries " +
                "WHERE Date = '" + previousDate + "' AND Country = '" + country + "'");
            
            if (rs.next() == false) { 
                     return ("Previous date not found for: " + country +
                        " on " +  date);
            } else {
               previousDeaths = rs.getInt(5);     
            }
            
            if(previousDeaths == 0) {
                return ("Percentage growth is undefined");
            }
            
            percentIncrease = ((dateDeaths/previousDeaths - 1) * 100);
      
            stmt.close();

            con.close();
        }
        catch (Exception e) {
            System.out.println("catch5");
            System.out.println(e);
        }
        
        double percentRounded = Math.round(percentIncrease *  1000)/1000.0;
        return String.valueOf(percentRounded);
    }
    
    /**
     * Determines previous date from an inputted date
     * 
     * @param date Inputted date
     * @return Previous date, in string form
     */
    private static String getPreviousDate(String date) {
        
        String [] dateArray = date.split("/");
        
        int month = Integer.parseInt(dateArray[0]);
        int day = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        String previousDate;

        if(day > 1) {
            previousDate = (dateArray[0] + "/" + Integer.toString(day - 1) +
                    "/" + dateArray[2]);
        } else if (month == 2 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11) {
            previousDate = (Integer.toString(month - 1) + "/31/" + dateArray[2]);
        } else if (month == 5 || month == 7 || month == 10 || month == 12) {
            previousDate = (Integer.toString(month - 1) + "/30/" + dateArray[2]);
        } else if (month == 3) {
            if (year % 4 == 0) {
                previousDate = ("2/29/" + dateArray[2]);
            } else {
                previousDate = ("2/28/" + dateArray[2]);
            }
        } else {
            previousDate = ("12/31/" + Integer.toString(year - 1));
        }
        
        return previousDate;
    }
    
}
