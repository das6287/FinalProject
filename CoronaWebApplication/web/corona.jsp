<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : newjsp
    Created on : Apr 23, 2020, 10:08:25 AM
    Author     : jpdys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        h1 {
            color: firebrick;
            font-size: 230%;
        }
        body {
            background-color: whitesmoke;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="10">
        <title>Coronavirus Info</title>
    </head>
    
    <body>
        <center><h1>Covid-19 Coronavirus Information</h1></center>
        <form name="Main Page" action="index.html">
            <input type="submit" value="Back" name="Back Button" />
        </form>
    </body>
    
    <%    
        String rate = request.getParameter("Rate");
        
        String age = request.getParameter("Age Range");
        String gender = request.getParameter("Gender");
        String month = request.getParameter("Month");
        String country = request.getParameter("Country");
        
        String state = request.getParameter("State");
        
        String race = request.getParameter("Race");
        //out.println(age + " " + gender + " " + month + " " + country
        //+ " " + state + " " + race);
        %>
        
</html>
