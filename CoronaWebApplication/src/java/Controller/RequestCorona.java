package Controller;

/**
 * RequestCorona.java
 * CMPSC 211
 * Purpose: To handle user information and database information between the 
 *          html and jsp files
 * 
 * @author John Dyson
 * @version 3.2 5/4/2020
 */

import Model.DBInteract;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author John Dyson
 */
@WebServlet(urlPatterns = {"/RequestCorona"})
public class RequestCorona extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Database data variables
        String data1 = null;        
        String data2 = null;
        
        //Reintializes url to jsp url
        String url = "/corona.jsp";
        
        new DBInteract();
        
        //Gets paramter values from index.html
        String rate = request.getParameter("Rate");
        String date = request.getParameter("Date");
        String country = request.getParameter("Country");
        // -TESTING ONLY-->
        //System.out.println("The "+rate+" in "
        //+country+" on "+date+" is: ");
        // <--TESTING ONLY-
        if("Growth Rate".equals(rate)) {
            //Gets the world growth rate from the database
            data1 = DBInteract.getWorldGrowthRate(date);
            //Gets the growth rate by country from the database
            data2 = DBInteract.getCountryGrowthRate(date, country);   
        }
        if("Fatality Rate".equals(rate)) {
            //Gets the world death rate from the database
            data1 = DBInteract.getWorldDeathRate(date);
            //Gets the death rate by country from the database
            data2 = DBInteract.getCountryDeathRate(date, country);
        }
        
        //Sets attribute values in jsp
        request.setAttribute("data1", data1);
        request.setAttribute("data2", data2);
        request.setAttribute("date", date);
        request.setAttribute("country", country);
        request.setAttribute("rate", rate);
        
        //Forward request and response objects to jsp
        getServletContext() 
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //doGet(request, response);
    }
}
