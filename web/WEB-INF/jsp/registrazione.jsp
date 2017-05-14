<%-- 
    Document   : visualizza
    Created on : 12-mag-2017, 21.35.35
    Author     : tosos
--%>

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
        <li class="active"><a href="#">REGISTRATI<span class="sr-only">(current)</span></a></li><!--centralizzare-->
        
    </nav>
    <div class="sfondo" align="center">
        <img src="/RevolutionaryReviews/resources/logo.png" width="40%"/>
    </div>  
    
    <h2>Modulo di Registrazione</h2>
		<hr />
		<br>inserisci i dati e spedisci il modulo</br>
		<form action="" method="POST">
			Nickname:<input type="text" name="nick"><br />
			Password:<input type="password" name="pw"><br />
			Nome:<input type="text" name="nome"><br />
			Cognome:<input type="text" name="cog"><br />
                        Provincia:<input type="text" name="pro"><br />
			E-mail:<input type="text" name="email"><br />
                        icona:<input type="text" name="icon">https://i.ytimg.com/vi/g9Mvm_Ipp5g/hqdefault.jpg<br />
			<input class="button" type="submit" name="invio" value="Invia" />
			<input class="button" type="reset" name="cancella" value="Annulla" />
		<br>
			<a class="button" href="/RevolutionaryReviews/">Indietro</a>
		</br>
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