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
        
        DBInteract coronaDB = new DBInteract();
        
        System.out.println(DBInteract.findRow("Row1"));
        System.out.println(DBInteract.findRow("Row2"));
        System.out.println(DBInteract.findRow("Row4"));
    }
    
}
