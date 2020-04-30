package Model;

import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author joshuahaines
 */
public class DBInteractTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new DBInteract();
        
        System.out.println(DBInteract.getWorldData("4/15/2020", 0));
        System.out.println(DBInteract.getWorldData("4/15/2020", 1));
        System.out.println(DBInteract.getWorldGrowthRate("4/15/2020"));
        System.out.println(DBInteract.getWorldData("4/21/2020", 0));
        System.out.println(DBInteract.getCountryData("4/15/2020", "US", 0));
        System.out.println(DBInteract.getCountryGrowthRate("1/23/2020", "China"));
        System.out.println(DBInteract.getWorldDeathRate("4/15/2020"));
        System.out.println(DBInteract.getCountryDeathRate("4/1/2020","US"));
    }
    
}
