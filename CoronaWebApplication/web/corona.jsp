<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : newjsp
    Purpose    : Output selected database information
    Created on : Apr 23, 2020, 10:08:25 AM
    Author     : John Dyson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        h1 {
            color: firebrick;
            font-size: 235%;
            text-align: center;
            font-family: timesnewroman;
        }
        h2 {
            color: black;
            font-size: 110%;
            text-align: center;
            font-family: timesnewroman;
        }
        body {
            background-color: whitesmoke;
        }
        .back {
            width: 25%;
            float: right;
            padding-right: 30px; 
            padding-top: 5px;
        }
        .results {
            width: 100%;
            text-align: center;
            font-size: 90%;
            padding-top: 15px;
        }
        .redText {
            color: firebrick;
            font-size: 120%;
        }
        .backbutton {
            border-radius: 12px;
            border-color: gray;
            font-family: timesnewroman;
            font-size: 105%;
            width: 15%
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                The World ${rate} is <span class='redText'>${data1}%</span> on ${date}.
            </h3>
            <h3>
                The ${country} ${rate} is <span class='redText'>${data2}%</span> on ${date}.
            </h3>
        </div>
        <div class='back'>
            <form name="Main Page" action="index.html">
                <input type="submit" value="Back" name="Back Button" class="backbutton"/>
            </form>   
        </div>    
  </body>      
</html>
