package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DBInteract;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jpdys
 */
@WebServlet(urlPatterns = {"/RequestCoronaData"})
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RequestCorona</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestCorona at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
        String url = "/corona.jsp";
        
        String rate = request.getParameter("Rate");
        String age = request.getParameter("Age Range");
        String gender = request.getParameter("Gender");
        String month = request.getParameter("Month");
        String country = request.getParameter("Country");
        String race = request.getParameter("Race");
        
        if("United States".equals(country)) {
            String state = request.getParameter("State");
            //System.out.println(state);
            System.out.println("The "+rate+" of a(n) "+race+gender+" of age "+age+" from "+state+", "
            +country+" during the month of "+month+" is: ");
        }
        else {
            System.out.println("The "+rate+" of a(n) "+race+gender+" of age "+age+" from the country "
            +country+" during the month of "+month+" is: ");
        }
        try {
        Statement stmt = null;
        String query = "select "+month+" from testTable";
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(RequestCorona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext() 
            .getRequestDispatcher(url)
            .forward(request, response);
        
    }

    
    //public Object getServletContext() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

}
