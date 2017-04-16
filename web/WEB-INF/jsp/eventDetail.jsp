<%-- 
    Document   : index
    Created on : 24-mar-2017, 17.25.56
    Author     : guglielmo
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

        <title>Categorie | SoundZone</title>

        
        
        <!-- Bootstrap Core CSS -->
        <link href="/WebCommunity/resources/css/bootstrap.min.css" rel="stylesheet">
        

        <!-- Custom CSS -->
        <link href="/WebCommunity/resources/css/custom/customStyles.css" rel="stylesheet">
        
        <!-- JQuery Core -->
        <script src="/WebCommunity/resources/jquery-3.2.0.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>
        
        <!-- StarRating -->
        <link href="/WebCommunity/resources/css/bootstrap-rating.css" media="all" rel="stylesheet" type="text/css" />
        
        <!-- Rating Core -->
        <script src="/WebCommunity/resources/js/bootstrap-rating.js" type="text/javascript"></script>

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
                        <li class="active"><a href="/WebCommunity/events">Eventi</a></li>
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
                        <h1><span class="popcolor">#Evento</span> <small><!----></small></h1>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${eventDetail.titolo}</h3>
                        </div>
                        <div class="panel-body">
                            Luogo: ${eventDetail.luogo}<br>
                            Data: ${eventDetail.data}<br>
                            Descrizione: ${eventDetail.descrizione}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <%
                        if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == "")) {
                    %>
                    <h2>Per rilasciare un commento devi essere un utente registrato.</h2>
                    <h4>Se non possiedi un account puoi registrarti <a href="/WebCommunity/registration" style="color: rgb(241, 26, 147)">qui →</a></h4>
                    <br/>
                    <%
                        } else {
                    %>
                    <form class="form" method="GET" action="/WebCommunity/commento">
                        <div class="form-group">
                            <label>Inserisci il tuo commento</label>
                            <input type="text" class="form-control" name="comm" placeholder="Inserisci il tuo commento qui" required>
                            <input type="hidden" name="evento" value="${eventDetail.id}" />
                        </div>
                        <div class="form-group">
                            <input type="hidden" class="rating" value="3" name="voto"/>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn " style="padding-left: 50px; padding-right: 50px;">Commenta</button>
                        </div>
                    </form>
                    <%
                        }
                    %>
                </div>
                <div class="row">
                    <div class="page-header">
                        <h3><span class="popcolor">#Commenti</span> <small><!----></small></h3>
                    </div>
                </div>
                <div class="row">
                    <c:choose>
                        <c:when test="${empty postList}">
                            <center><h3>Nessun commento pubblicato. Sii tu il primo!</h3><center>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${postList}" var="post">
                                <div class="media" style="margin-bottom: 20px;">
                                    <div class="media-left">
                                        <c:choose>
                                            <c:when test="${empty post.membro1.avatar}">
                                                <img class="media-object img-circle user-img-circle-xsmall" src="http://webcommunityproject.altervista.org/-1661776617" alt="Event picture">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="media-object img-circle user-img-circle-xsmall" src="${post.membro1.avatar}" alt="Event picture">
                                            
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="media-body">
                                        <h4 class="media-heading">${post.membro1.username}</h4>
                                        <p>${post.commento}</p>
                                        <input type="hidden" class="rating" data-readonly value="${post.voto}"/>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
                
        <script>
            $(document).ready(function () {
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

