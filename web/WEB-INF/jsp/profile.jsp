<%-- 
    Document   : profile
    Created on : 25-mar-2017, 12.33.20
    Author     : matte
--%>

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

        <title>Profilo | NomeSito</title>

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
                    <a class="navbar-brand" href="/WebCommunity/">NomeSito</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="/WebCommunity/">Home</a></li>
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
                                        } else {
                                    %>
                                    <div class="row text-center">
                                        <div class="row">
                                            <img src="/WebCommunity/resources/user.jpg" alt="user-picture" class="img-circle user-img-circle-small">
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
            <div class="container bs-docs-container">
                <div class="row">
                    <div class="page-header">
                        <h1><span class="popcolor">#Profilo</span> <small>Gestisci il tuo profilo</small></h1>
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
                    <div class="col-xs-12 col-md-8">
                        <div class="row">
                            <div class="col-md-3">
                                <a href="#" data-toggle="modal" data-target="#changeProfilePicture"><img src="/WebCommunity/resources/camera.png" alt="user-picture" class="img-circle user-img-circle-camera"></a>
                                <img src="${userinfo.avatar}" alt="user-picture" class="img-circle user-img-circle-large"/>
                            </div>
                            <div class="col-md-7">
                                <h2>Le tue informazioni</h2>
                                Nickname: ${userinfo.username}
                                <br>
                                Email: ${userinfo.mail}
                                <br>
                                Nome: ${userinfo.nome}
                                <br>
                                Cognome: ${userinfo.cognome}
                                <br>
                            </div>
                        </div>
                                
                        <br>
                        <br>
                        <h2>I tuoi interessi</h2>
                        <ul>
                            <c:forEach items="${userinfo.categoriaList}" var="categoriesItem">
                                <li style="display:inline;">
                                    ${categoriesItem.nome}
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-xs-6 col-md-4">
                        <a href="#">Modifica password</a>
                        <br>
                        <a <a href="#" data-toggle="modal" data-target="#deleteProfile">Elimina il tuo account</a>
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
                                <input type="file" class="btn btn-default" name="file"/>
                            </center>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn btn-default">Cambia immagine</button>
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
                            Eliminando il tuo account verranno eliminati anche tutti i post pubblicati e gli interessi che stai seguendo. Sei sicuro di voler continuare?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn btn-default">Elimina</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </body>

    <!-- JQuery Core -->
    <script src="/WebCommunity/resources/jquery-3.1.1.slim.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>
</html>

