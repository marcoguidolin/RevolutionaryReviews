<%-- 
    Document   : index
    Created on : 24-mar-2017, 17.25.56
    Author     : matte
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <nav class="navbar navbar-default navbar-inverse" role="navigation">
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
        <!-- /.carousel -->
        <div class="body-container">
            <div class="container bs-docs-container transition-page">
                <div class="row">
                    <div class="page-header">
                        <h1><span class="popcolor">#Home</span> <small><!----></small></h1>
                    </div>
                </div>
                <div class="row">
                    <div class="carousel slide" id="myCarousel">
                        <div class="carousel-inner">
                        <c:set var="isSet" value="${0}"></c:set>
                            <c:if test="${fn:length(eventsList) eq 0}">
                                <div class="item active">
                                    <img class="img-responsive" style="margin-bottom: 25px;" src="/WebCommunity/resources/event.png" alt="Event picture"/>
                                    <div class="container">
                                        <div class="carousel-caption">
                                            <h1 style="text-shadow: 0px 0px 30px rgba(0, 0, 0, 1);">Nessun evento disponibile</h1>
                                            <p>Cambia zona nelle impostazioni del tuo account oppure guarda tutti gli eventi.</p>
                                            <p><a class="btn btn-large btn-primary" href="/WebCommunity/events">Guarda tutti gli eventi</a></p>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:forEach items="${eventsList}" var="eventItem">
                                <div
                                    <c:choose>
                                        <c:when test="${isSet == 0}">
                                         class="item active"
                                         <c:set var="isSet" value="${1}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            class="item"
                                        </c:otherwise>
                                    </c:choose>
                                      >
                                    <img class="img-responsive" style="margin-bottom: 25px;" src="
                                         <c:choose>
                                             <c:when test="${eventItem.immagine != null}">
                                                 data:image/png;base64,${eventItem.getImmagineString()}
                                             </c:when>
                                             <c:otherwise>
                                                 /WebCommunity/resources/event.png
                                             </c:otherwise>
                                         </c:choose>
                                         " alt="Event picture"/>
                                    <div class="container">
                                        <div class="carousel-caption">
                                            <h1 style="text-shadow: 0px 0px 30px rgba(0, 0, 0, 1);">${eventItem.titolo}</h1>
                                            <p>${eventItem.descrizione}</p>
                                            <p><a class="btn btn-large btn-primary" href="/WebCommunity/eventDetail?id=${eventItem.id}">Guarda</a></p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                            <span class="icon-prev"></span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                            <span class="icon-next"></span>
                        </a>  
                    </div>
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

