<%-- 
    Document   : registration
    Created on : 24-mar-2017, 11.29.17
    Author     : FSEVERI\guglielmo3238
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
                        <li><a href="/WebCommunity">Home</a></li>
                        <li><a href="categories">Categorie</a></li>
                        <li><a href="artists">Artisti</a></li>
                        <li><a href="events?category=0">Eventi</a></li>
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
                                                    <button type="submit" class="btn btn-primary btn-block">Accedi</button>
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
                                            <img src="/WebCommunity/resources/user.jpg" alt="user-picture" class="img-circle user-img-circle-small">
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
            <div class="container bs-docs-container">
                <div class="row">
                    <div class="page-header">
                        <h1><span class="popcolor">Registrati</span> <small>Entra a far parte della community!</small></h1>
                    </div>
                    <div class="row">
                        <%
                            if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == ""))
                            {
                        %>
                        <div class="col-xs-12 col-md-7">
                            <div class="row">
                                <form class="form" method="POST" action="/WebCommunity/doRegistration" id="login-nav">
                                    <div class="form-group">
                                        <label>Nome</label>
                                        <input type="text" class="form-control" name="name" placeholder="Nome" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Cognome</label>
                                        <input type="text" class="form-control" name="surname" placeholder="Cognome" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input type="text" class="form-control" name="username" placeholder="Username" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Ripetere password</label>
                                        <input type="password" class="form-control" name="passwordCheck" placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Mail</label>
                                        <input type="mail" class="form-control" name="mail" placeholder="Mail" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Categorie</label>
                                        <ul>
                                            <c:forEach items="${categoriaList}" var="catItem">
                                                <li style="display:inline;">
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input type="checkbox" class="form-check-input" name="cat" value="${catItem.id}">
                                                            ${catItem.nome}
                                                        </label>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary" style="padding-left: 50px; padding-right: 50px;">Registrati</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-xs-6 col-md-4 error">
                            <div class="row">
                                <c:if test="${error}">
                                    <h4>Attenzione</h4>
                                    <p>${messageError}</p>
                                </c:if>
                            </div>
                        </div>
                        <%
                        } else
                        {
                        %>
                    </div>
                    <h2>Ti sei già registrato.</h2>
                    <h4>Puoi gestire il tuo profilo <a href="/WebCommunity/profile" style="color: rgb(241, 26, 147)">qui →</a></h4>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>

    <!-- JQuery Core -->
    <script src="/WebCommunity/resources/jquery-3.1.1.slim.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>
</html>

