<%@page import="POJO.Eventi"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RevolutionaryReviews</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>

    <body>
        
        
    <nav class="navbar navbar-inverse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Homepage<span class="sr-only">(current)</span></a></li>
            <li><a href="#">Eventi</a></li>
            <li><a href="#">Utenti</a></li>
            <li><a href="#">Chi siamo</a></li>   
            <li style="float: right;"><a href="#" style="padding: 0;"><img src="/RevolutionaryReviews/resources/utente.ico" width="56px"/></a></li>
        </ul>
    </nav>
    
    
    <div class="sfondo" align="center">
        <img src="/RevolutionaryReviews/resources/logo.png" width="40%"/>
    </div>  
    
    <h3>Eventi in scadenza</h3>
        <div>
            <c:forEach items="${listEventiScadenza}" var="eventoSca">
                <p> ${eventoSca.getTitolo()} </p>
            </c:forEach>
        </div>
    
    
    <h3>Eventi passati</h3>

    <c:forEach items="${listEventiPassati}" var="evento">
        <a href="/RevolutionaryReviews/dettagliEvento?id=${evento.id}">${evento.titolo}</a>
    </c:forEach>
        
    <h3> Eventi più votati </h3>
    <div>
    <ol>
        <c:forEach items="${listEVotati}" var="eventoVotato">
            <li>${eventoVotato} </li>
        </c:forEach>
    </ol>
    </div>
      
</body>
</html>



<style>
    
    .navbar {
        margin-bottom: 0;
    }
    
    .sfondo {
        height: 400px;
        background-image: url('http://mclarenwalltowall.com/static/images/map/header_bg.jpg');
        background-size: cover;
    }
    
</style>