<%-- 
    Document   : profile
    Created on : 25-mar-2017, 12.33.20
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

        <title>Profilo | SoundZone</title>

        <!-- Bootstrap Core CSS -->
        <link href="/WebCommunity/resources/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- Custom CSS -->
        <link href="/WebCommunity/resources/css/custom/customStyles.css" rel="stylesheet">
        
        <!-- JQuery Core -->
        <script src="/WebCommunity/resources/jquery-3.2.0.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>

        <!-- Bootstrap Validator -->
        <script src="/WebCommunity/resources/js/validator.js"></script>
        
        <!-- Rating Core -->
        <script src="/WebCommunity/resources/js/bootstrap-rating.js" type="text/javascript"></script>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
    </head>
    
    <body class="background-container">
        <nav class="navbar navbar-fixed-top navbar-default navbar-inverse supreme-container" role="navigation">
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
                                                    <button type="submit" class="btn btn-block">Accedi</button>
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
        <div class="body-container supreme-container">
            <div class="container bs-docs-container transition-page">
                <div class="row">
                    <div class="page-header">
                        <h1><span class="popcolor">#Profilo</span> <small>Gestisci il tuo profilo</small></h1>
                        <%
                            if (!(session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == "")) {
                        %>
                        <div class="dropdown" style="float: right; z-index: 2; top: -6px;">
                            <button class="btn btn-default dropdown-toggle action-dropdown" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                    <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                <li><a href="#" data-toggle="modal" data-target="#changePassword">Modifica password</a></li>
                                <li><a href="#" data-toggle="modal" data-target="#changeProfilePicture">Modifica immagine</a></li>
                                <li><a href="#" data-toggle="modal" data-target="#changePersonalInformations">Modifica informazioni</a></li>
                                <li><a href="#" data-toggle="modal" data-target="#deleteProfile">Elimina il tuo account</a></li>
                            </ul>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="row">
                    <%
                        if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == "")) {
                    %>
                    <h2>Per poter visualizzare il contenuto di questa pagina devi prima effettuare l'accesso.</h2>
                    <h4>Se non possiedi un account puoi registrarti <a href="/WebCommunity/registration" style="color: rgb(241, 26, 147)">qui →</a></h4>
                    <%
                        } else {
                    %>
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-md-3 col-xs-12 text-center" style="margin-bottom: 25px;">
                                <a href="#" data-toggle="modal" data-target="#changeProfilePicture"><img src="/WebCommunity/resources/camera.png" alt="user-picture" class="img-circle user-img-circle-camera"></a>
                                <img src="
                                    <c:choose>
                                        <c:when test="${userinfo.avatar != null}">
                                            data:image/png;base64,${userinfo.getAvatarString()}
                                        </c:when>
                                        <c:otherwise>
                                            /WebCommunity/resources/user.png
                                        </c:otherwise>
                                    </c:choose>
                                " alt="user-picture" class="img-circle user-img-circle-large"/>
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <h2 class="popcolor">#Le tue informazioni</h2>
                                    <h4><span class="glyphicon glyphicon-sunglasses" aria-hidden="true" style="margin-right: 10px;"></span>${userinfo.username}</h4>
                                    <h5><span class="glyphicon glyphicon-envelope" aria-hidden="true" style="margin-right: 10px;"></span> ${userinfo.mail}</h5>
                                    <h5><span class="glyphicon glyphicon-user" aria-hidden="true" style="margin-right: 10px;"></span> ${userinfo.nome} ${userinfo.cognome}</h5>
                                    <h5></h5>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <br>
                                    <br>
                                    <h2 class="popcolor">#I tuoi interessi</h2>
                                    
                                    <c:choose>
                                        <c:when test="${empty userinfo.categoriaList}">
                                            <h4>Non stai seguendo interessi.</h4>
                                            <button type="button" class="btn" data-toggle="modal" data-target="#updateInterests">Aggiungi interessi</button>
                                        </c:when>
                                        <c:otherwise>
                                            <ul class="list-inline">
                                                <c:forEach items="${userinfo.categoriaList}" var="categoriesItem">
                                                    <li>
                                                        <div class="btn-group" role="group" style="margin-bottom: 5px;">
                                                            <a href="/WebCommunity/events?category=${categoriesItem.id}&name=${categoriesItem.nome}"  class="btn btn-default" style="color: black !important;">${categoriesItem.nome}</a>
                                                            <a href="/WebCommunity/deleteInterest?id=${categoriesItem.id}" class="btn btn-default" style="height: 32px;"> <span class="glyphicon glyphicon-remove-circle" aria-hidden="true" style="line-height: 18px;"></span></a>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="row">
                                    <br>
                                    <br>
                                    <h2 class="popcolor">#I tuoi commenti</h2>
                                    <c:choose>
                                        <c:when test="${empty userinfo.postList}">
                                            <h4>Non hai pubblicato commenti.</h4>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${userinfo.postList}" var="postItem">
                                                <div class="row">
                                                    <div class="col-md-10 col-xs-10">
                                                        <div class="media" style="margin-bottom: 20px;">
                                                            <div class="media-left">
                                                                <img class="media-object img-circle user-img-circle-xsmall" src="
                                                                    <c:choose>
                                                                       <c:when test="${postItem.evento1.immagine != null}">
                                                                           data:image/png;base64,${postItem.evento1.getImmagineString()}
                                                                       </c:when>
                                                                       <c:otherwise>
                                                                           /WebCommunity/resources/event.png
                                                                       </c:otherwise>
                                                                   </c:choose>
                                                                " alt="Event picture"/>
                                                            </div>
                                                            <div class="media-body">
                                                                <h4 class="media-heading">${postItem.evento1.titolo}</h4>
                                                                <p>${postItem.commento}</p>
                                                                <input type="hidden" class="rating" data-readonly value="${postItem.voto}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 col-xs-2">
                                                        <a href="/WebCommunity/deletePost?id=${postItem.evento1.id}" class="btn btn-circle" style="margin-top: 25px; float: right;"><span class="glyphicon glyphicon-trash" aria-hidden="true" style="line-height: 18px;"></span></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="row">
                                    <br>
                                    <br>
                                    <h2 class="popcolor">#I tuoi eventi</h2>
                                    <ul class="list-inline">
                                        Da implementare
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
                
        <!-- Modal -->
        <div class="modal fade" id="changeProfilePicture" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cambia immagine profilo</h4>
                    </div>
                    <form method="POST" action="/WebCommunity/uploadFile" enctype="multipart/form-data">
                        <div class="modal-body">
                            <center>    
                                Seleziona l'immagine da caricare poi fai click su Cambia immagine
                                <br><br>
                                <input type="file" class="btn" name="file"/>
                                <br>
                                <div class="progress progress-striped active" id="uploadState" style="width: 70%; visibility: collapse;">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"/>
                                </div>
                            </center>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn" onclick="document.getElementById('uploadState').style.visibility = 'visible'">
                                Cambia immagine
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="changePassword" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modifica password</h4>
                    </div>
                    <form action="/WebCommunity/doChangePassword" method="POST" data-toggle="validator">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Password</label>
                                <div class="form-inline row">
                                    <div class="form-group col-sm-6">
                                        <input type="password" data-minlength="6" class="form-control" id="inputPassword" name="password" placeholder="Password" required>
                                        <div class="help-block">La password deve essere lunaga almeno 6 caratteri.</div>
                                    </div>
                                    <div class="form-group col-sm-6">
                                        <input type="password" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Le password inserite non corrispondono" placeholder="Conferma" required>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Aggiorna</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="changePersonalInformations" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modifica informazioni personali</h4>
                    </div>
                    <form action="/WebCommunity/doChangePersonalInformations" method="POST" data-toggle="validator">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Nome</label>
                                <input type="text" class="form-control" name="name" value="${userinfo.nome}" placeholder="Nome" required>
                            </div>
                            <div class="form-group">
                                <label>Cognome</label>
                                <input type="text" class="form-control" name="surname" value="${userinfo.cognome}" placeholder="Cognome" required>
                            </div>
                            <div class="form-group">
                                <label>Mail</label>
                                <input type="email" class="form-control" name="mail" value="${userinfo.mail}" placeholder="Mail" data-error="La mail specificata non è valida. Assicurati che sia nel formato: example@example.com" required>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Aggiorna</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="deleteProfile" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Elimina account</h4>
                    </div>
                    <form action="/WebCommunity/doRemove" method="GET">
                        <div class="modal-body">
                            Eliminando il tuo account verranno eliminati anche tutti i post pubblicati e gli eventi da te creati. Sei sicuro di voler continuare?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Elimina</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="updateInterests" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Aggiorna interessi</h4>
                    </div>
                    <form action="/WebCommunity/doInterests" method="POST">
                        <div class="modal-body">
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
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Aggiorna</button>
                        </div>
                    </form>
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

