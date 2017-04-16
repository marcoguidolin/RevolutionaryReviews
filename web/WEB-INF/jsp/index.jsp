<%-- 
    Document   : index
    Created on : 24-mar-2017, 17.25.56
    Author     : matte
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Shopping List MVC">
        <meta name="author" content="Matteo Parlato">

        <title>Home | SoundZone</title>

        <!-- Bootstrap Core CSS -->
        <link href="/WebCommunity/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="/WebCommunity/resources/css/custom/customStyles.css" rel="stylesheet">

        <!-- JQuery Core -->
        <script src="/WebCommunity/resources/jquery-3.2.0.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>
        
        <!-- Carousel Script -->
        <script src="/WebCommunity/resources/js/jquery.flipster.min.js"></script>
        
        <!-- Carousel CSS -->
        <link rel="stylesheet" href="/WebCommunity/resources/css/flipster.min.css">
    
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <nav class="navbar navbar-fixed-top navbar-default navbar-inverse" role="navigation">
            <div class="container-fluid centered-content">
                
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">SoundZone</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="/WebCommunity/categories">Categorie</a></li>
                        <li><a href="/WebCommunity/events">Eventi</a></li>
                        <li><a href="/WebCommunity/artists">Artisti</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown"><b>Account</b> <span class="caret"></span></a>
                            <ul id="login-dp" class="dropdown-menu">
                                <li>
                                    <%
                                        if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == "")) {
                                    %>
                                    <div class="row">
                                        <div class="col-md-12">
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
                                    <%
                                        } else {
                                    %>
                                    <div class="row text-center">
                                        <div class="row">
                                            <img src="
                                                <c:choose>
                                                   <c:when test="${userinfo.avatar != null}">
                                                       data:image/png;base64,${userinfo.getAvatarString()}
                                                   </c:when>
                                                   <c:otherwise>
                                                       /WebCommunity/resources/user.png
                                                   </c:otherwise>
                                               </c:choose>
                                            " alt="user-picture" class="img-circle user-img-circle-small">
                                        </div>
                                        <div class="row">
                                            <h4>Ciao ${userinfo.username}</h4>
                                            <a href="/WebCommunity/profile"><b>Vai al tuo profilo</b></a>
                                        </div>
                                        <br>
                                        <div class="bottom">
                                            <a href="/WebCommunity/doLogout"><b>Disconnetti</b></a>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="body-container">
            <div class="container bs-docs-container transition-page">
                <div class="row">
                    <div class="page-header">
                        <h1><span class="popcolor">#Home</span> <small><!----></small></h1>
                    </div>
                </div>
                <div class="row">
                    <div id="coverflow">
                        <ul class="flip-items" style="margin-top: 20px;">
                            <li data-flip-title="Red">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Razzmatazz" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Deep Lilac" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Red">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Razzmatazz" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Deep Lilac" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Red">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Razzmatazz" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Deep Lilac" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Red">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                            <li data-flip-title="Razzmatazz" data-flip-category="Purples">
                                <img src="/WebCommunity/resources/user.png">
                            </li>
                        </ul>
                    </div>
                </div>
                <a href="/WebCommunity/createEvent">prova</a>
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Attenzione:</span>
                    Assicurati che sul database sia stato importato l'ultimo schema disponibile su Google Drive altrimenti potrebbero verificarsi degli errori inaspettati causa le immagini vengono ora memorizzate nel database. Se Glassfish restituisce l'errore HTTP 500 cerca nella pagina che stati tentando di visualizzare l'invocazione al metodo userinfo.getAvatarString() e rimuovila oppure inserisci un'immagine per l'utente tramite phpMyAdmin. Gli utenti "predefiniti" al 99% genereranno tale errore, possibilmente genera un nuovo profilo.
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#coverflow").flipster();
                $('div.transition-page').fadeIn(250).removeClass('transition-page');
            });
            
            $('.dropdown').on('show.bs.dropdown', function() {
              $(this).find('.dropdown-menu').first().stop(true, true).slideDown();
            });

            $('.dropdown').on('hide.bs.dropdown', function() {
              $(this).find('.dropdown-menu').first().stop(true, true).fadeOut(200);
            });
        </script>
    </body>    
</html>

