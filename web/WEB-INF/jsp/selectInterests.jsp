<%-- 
    Document   : selectInterests
    Created on : 30-mar-2017, 20.06.00
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

        <title>Interessi | SoundZone</title>

        <!-- Bootstrap Core CSS -->
        <link href="/WebCommunity/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="/WebCommunity/resources/css/custom/customStyles.css" rel="stylesheet">
        
        <!-- JQuery Core -->
        <script src="/WebCommunity/resources/jquery-3.2.0.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>

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
                    <a class="navbar-brand" href="/WebCommunity/">SoundZone</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="/WebCommunity/">Home</a></li>
                        <li><a href="/WebCommunity/categories">Categorie</a></li>
                        <li><a href="/WebCommunity/artists">Artisti</a></li>
                        <li><a href="/WebCommunity/events?category=0">Eventi</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown"><b>Account</b> <span class="caret"></span></a>
                            <ul id="login-dp" class="dropdown-menu">
                                <li>
                                    <%
                                        if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == ""))
                                        {
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
                                    } else
                                    {
                                    %>
                                    <div class="row text-center">
                                        <div class="row">
                                            <img src="${userinfo.avatar}" alt="user-picture" class="img-circle user-img-circle-small">
                                        </div>
                                        <div class="row">
                                            Ciao ${userinfo.username}
                                            <br>
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
                        <h1><span class="popcolor">Hai quasi fatto...</span> <small><!-- --></small></h1>
                    </div>
                    <div class="row">
                        <%
                            if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == ""))
                            {
                        %>
                        <h2>Per poter visualizzare il contenuto di questa pagina devi prima effettuare l'accesso.</h2>
                        <h4>Se non possiedi un account puoi registrarti <a href="/WebCommunity/registration" style="color: rgb(241, 26, 147)">qui →</a></h4>
                        <%
                        } else
                        {
                        %>
                    </div>
                    <div class="col-xs-12 col-md-8 col-md-offset-2">
                        <div class="row">
                            <form action="/WebCommunity/doInterests" method="POST">
                                <div class="form-group">
                                    <p>Seleziona le categorie di eventi a cui sei interessato:</p>
                                    <ul>
                                        <c:forEach items="${categoriesList}" var="categoriesItem">
                                            <li style="display:inline;">
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="checkbox" class="form-check-input" name="categories" value="${categoriesItem.id}">
                                                        ${categoriesItem.nome}
                                                    </label>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <button type="button" onclick="location.href='./profile'" class="btn " style="padding-left: 50px; padding-right: 50px; margin-top: 25px; float: left;">Ignora</button>
                                    <button type="submit" class="btn" style="padding-left: 50px; padding-right: 50px; margin-top: 25px; float: right;">Continua</button>
                                </div>                                    
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
                
        <script>
            $(document).ready(function () {
                $('div.transition-page').fadeIn(250).removeClass('transition-page');
            });
        </script>
    </body>
</html>
