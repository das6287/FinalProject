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
            text-align: center;
        }
        h2 {
            color: black;
            font-size: 110%;
            text-align: center;
        }
        body {
            background-color: whitesmoke;
        }
        .back {
            width: 20%;
            float: right;
            padding-right: 10px;           
        }
        .results {
            width: 100%;
            text-align: center;
            font-size: 75%;
            padding-top: 15px;
        }
        .redText {
            color: firebrick;
            font-size: 115%;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="10">
        <title>Coronavirus Info</title>
    </head>
    <body>
        <h1>Covid-19 Coronavirus Information</h1>
        
        <h2>For information on how to protect yourself and others from 
                the coronavirus, visit the
        <a href="https://www.cdc.gov/coronavirus/2019-ncov/index.html" target="_blank">
            CDC Website.
        </a>
        </h2>
        
        <div class='results'>
            <h3>
                The World [insert rate variable] is <span class='redText'>[insert rate value]%</span> on [date].
            </h3>
            <h3>
                The [country] [rate variable] is <span class='redText'>[rate value]%</span> on [date].
            </h3>
        </div>
        <div class='back'>
            <form name="Main Page" action="index.html">
                <input type="submit" value="Back" name="Back Button" />
            </form>   
        </div>    
  </body>      
</html>
