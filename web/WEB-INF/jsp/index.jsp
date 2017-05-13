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
        
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        
        <!-- Latest compiled and minified CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        
        
    </head>

    <body>


    <nav class="navbar navbar-inverse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Homepage<span class="sr-only">(current)</span></a></li>
           
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Eventi <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" style="color:#fff; background-color: #222;">
                    <li><a href="#" style="color:#00b0ff;">Eventi in scadenza</a></li>
                    <li><a href="#" style="color:#00b0ff;">Eventi passati</a></li>
                    <li><a href="#" style="color:#00b0ff;">Tutti gli eventi</a></li>
                </ul>
            </li>

            <li><a href="/RevolutionaryReviews/followers">Utenti</a></li>
            <li><a href="#">Chi siamo</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 25px;">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;"><img src="/RevolutionaryReviews/resources/utente.ico" width="56px" style="top: -15px; position: relative;"/></a>
                <ul class="dropdown-menu" style="color:#fff; background-color: #222;">
                    <li>
                        <div class="row">
                            <div class="col-md-12" style="width: 250px;">
                                <div class="row text-center">
                                    Accedi
                                </div>
                                <form class="form" method="POST" action="/WebCommunity/doLogin" id="login-nav">
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input type="text" class="form-control" name="username" placeholder="Username" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn  btn-block">Accedi</button>
                                    </div>
                                </form>
                            </div>
                            <div class="bottom text-center">
                                Non possiedi un account?
                                <br/>
                                <a href="/WebCommunity/registration"><b>Registrati</b></a>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>

    <div class="sfondo" align="center">
        <img src="/RevolutionaryReviews/resources/logo.png" width="40%"/>
    </div>
    
    <div class="row">
        <div class="col-md-4">
            <h3>Eventi in scadenza</h3>
            <div>
                <c:forEach items="${listEventiScadenza}" var="eventoSca">
                    <p> ${eventoSca.getTitolo()} </p>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-4">
            <h3>Eventi passati</h3>
            <c:forEach items="${listEventiPassati}" var="evento">
                <a href="/RevolutionaryReviews/dettagliEvento?id=${evento.id}">${evento.titolo}</a>
                </br>
            </c:forEach>
        </div>
        <div class="col-md-4">
            <h3> Eventi più votati </h3>
            <div>
            <ol>
                <c:forEach items="${listEVotati}" var="eventoVotato">
                    <li>${eventoVotato} </li>
                </c:forEach>
            </ol>
            </div>
        </div>
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