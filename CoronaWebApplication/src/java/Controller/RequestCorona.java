package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        
        new DBInteract();
        
        //Reintializes url to jsp url
        String url = "/corona.jsp";
        
        //Gets paramter values from index.html
        String rate = request.getParameter("Rate");
        String date = request.getParameter("Date");
        String country = request.getParameter("Country");
        
        System.out.println("The "+rate+" from "
        +country+" on "+date+" is: ");
       
        if("Infection Rate".equals(rate)) {
            //Gets the world growth rate from the database
            System.out.println(DBInteract.getWorldGrowthRate(date));
            //Gets the growth rate by country from the database
            System.out.println(DBInteract.getCountryGrowthRate(date, country));          
        }
        if("Fatality Rate".equals(rate)) {
            //Gets the world death rate from the database
            System.out.println(DBInteract.getWorldDeathRate(date));
            //Gets the death rate by country from the database
            System.out.println(DBInteract.getCountryDeathRate(date, country));            
        }
        
        //Forward request and response objects to specified URL
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

    
    //public Object getServletContext() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

}
