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

        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Homepage<span class="sr-only">(current)</span></a></li>
            <li><a href="#">Eventi</a></li>
            <li><a href="#">Utenti</a></li>
            <li><a href="#">Chi siamo</a></li>   
            <li style="float: right;"><a href="#" style="padding: 0;"><img src="/RevolutionaryReviews/resources/utente.ico" width="56px"/></a></li>
        </ul>
    </nav>
    <div align="center">
        <img src="/RevolutionaryReviews/resources/logo.png" width="600px"/>
    </div>  

    <h3>Eventi principali</h3>
    <h3>Eventi in scadenza</h3>
    <h3>Eventi passati</h3>
    <c:forEach items="${listEventiPassati}" var="evento">
        <p>${evento.titolo}</p>
    </c:forEach>

</body>
        <div>
            
/*
            
            <c:forEach items="${recUt}" var="recUtItem">
                <p>${recUtItem.getVotoEvento()}</p>
                <p>${recUtItem.getCommento()}</p>
             </c:forEach>
            
            
            

            <c:forEach items="${listEventiPassati}" var="evento">
                <a href="/RevolutionaryReviews/dettagliEvento?id=${evento.id}">${evento.titolo}</a>
            </c:forEach>
           
            <ul>
                <c:forEach items="${listFollowers}" var="follower">
                    <li>${follower.nickname}</li>
                </c:forEach>
            </ul>
*/
          
            
        </div>
    </body>
</html>
