<%@page import="POJO.Eventi"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>

    <body>
        <div>
            <h2> Eventi Passati </h2>
            <div>
            <c:forEach items="${listEventiPassati}" var="evento">
                <a href="/RevolutionaryReviews/dettagliEvento?id=${evento.id}">${evento.titolo}</a>
            </c:forEach>
            </div>
            
            </br> 
            </br>
            
            <h2> Eventi in Scadenza </h2>
            <div>
            <c:forEach items="${listEventiScadenza}" var="eventoSca">
                <p> ${eventoSca.titolo} </p>
            </c:forEach>
            </div>
            
            <h2> Lista Utenti </h2>
            <div>
            <ul>
                <c:forEach items="${listFollowers}" var="follower">
                    <li>${follower.nickname}</li>
                </c:forEach>
            </ul>
            </div>
            
            </br>
            </br>
            
            <h2> Eventi più votati </h2>
            <div>
            <ol>
                <c:forEach items="${listEVotati}" var="eventoVotato">
                    <li>${eventoVotato.titolo} </li>
                </c:forEach>
            </ol>
            </div>
            
        </div>
    </body>
</html>
