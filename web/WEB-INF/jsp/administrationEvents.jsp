<%-- 
    Document   : administrationEvents
    Created on : 9-apr-2017, 19.48.05
    Author     : matte
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Shopping List MVC">
        <meta name="author" content="Matteo Parlato">

        <title>Eventi | Amministrazione</title>

        <!-- Bootstrap Core CSS -->
        <link href="/WebCommunity/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="/WebCommunity/resources/css/custom/customAdministrationStyles.css" rel="stylesheet">

        <!-- JQuery Core -->
        <script src="/WebCommunity/resources/jquery-3.2.0.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="/WebCommunity/resources/js/bootstrap.min.js"></script>
        
        <!-- Bootstrap Validator -->
        <script src="/WebCommunity/resources/js/validator.js"></script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/WebCommunity/administration">Amministrazione</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/WebCommunity/doLogout">Esci</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li><a href="/WebCommunity/administration">Dashboard <span class="sr-only">(current)</span></a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="/WebCommunity/administrationUsers">Utenti</a></li>
                        <li><a href="/WebCommunity/administrationNewsletter">Newsletter</a></li>
                        <li><a href="/WebCommunity/administrationCategories">Categorie</a></li>
                        <li><a href="/WebCommunity/administrationArtists">Artisti</a></li>
                        <li class="active"><a href="#">Eventi</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Eventi</h1>
                    <a href="#" data-toggle="modal" data-target="#addEvent" class="btn btn-default" style="float: right;">Inserisci evento</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!--<th>ID</th>-->
                                <th>Titolo</th>
                                <th>Luogo</th>
                                <th>Data</th>
                                <th>Categoria</th>
                                <th>Descrizione</th>
                                <th>Commenti</th>
                                <th>Artisti</th>
                                <th>Immagine</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${eventsList}" var="eventItem">
                                <tr>
                                    <!--<td><%--${eventItem.id}--%></td>-->
                                    <td>${eventItem.titolo}</td>
                                    <td>${eventItem.luogo}</td>
                                    <td>${eventItem.data}</td>
                                    <td><a href="/WebCommunity/administrationCategories/">${eventItem.categoria.nome}</a></td>
                                    <td>${eventItem.descrizione}</td>
                                    <td>
                                        <ul class="list-unstyled">
                                            <c:forEach items="${eventItem.postList}" var="postItem">
                                                <li><a href="/WebCommunity/administrationUsers/">${postItem.postPK.membro}</a></li>
                                                <li>${postItem.commento}</li>
                                                <li>Voto: ${postItem.voto}</li>
                                                <li>
                                                    <center>
                                                        <a onclick="window.location.href='/WebCommunity/administrationRemoveUserPostEvent?eventID=${eventItem.id}&userID=${postItem.postPK.membro}'" class="btn btn-danger btn-xs">
                                                            Elimina
                                                        </a>
                                                    </center>
                                                </li>
                                                <hr>
                                            </c:forEach>
                                        </ul>
                                    </td>
                                    <td>
                                        <ul class="list-unstyled">
                                            <c:forEach items="${eventItem.artistaList}" var="artistItem">
                                                <li><a href="/WebCommunity/administrationArtists/">${artistItem.nome} ${artistItem.cognome}</a></li>
                                                <li>
                                                    <center>
                                                        <a onclick="window.location.href='/WebCommunity/administrationRemoveArtistEvent?eventID=${eventItem.id}&artistID=${artistItem.id}'" class="btn btn-danger btn-xs">
                                                            Elimina
                                                        </a>
                                                    </center>
                                                </li>
                                                <hr>
                                            </c:forEach>
                                            <a href="#" data-toggle="modal" data-target="#updateArtists" class="btn btn-default" onclick="document.getElementById('eventoID').value = '${eventItem.id}'" >
                                                <span class="glyphicon glyphicon-cloud" aria-hidden="true"></span>
                                            </a>
                                        </ul>
                                    </td>
                                    <td>
                                        <img src="
                                            <c:choose>
                                                <c:when test="${eventItem.immagine != null}">
                                                    data:image/png;base64,${eventItem.getImmagineString()}
                                                </c:when>
                                                <c:otherwise>
                                                    /WebCommunity/resources/event.png
                                                </c:otherwise>
                                            </c:choose>
                                        " alt="user-picture" class="img-circle"/>
                                    </td>
                                    <td>
                                        <a onclick="window.location.href='/WebCommunity/administrationRemoveEvent?id=${eventItem.id}'" class="btn btn-default" style="float: right;">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#changePicture" class="btn btn-default" onclick="document.getElementById('cameraFormID').value = '${eventItem.id}'" style="float: right;">
                                            <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#updateEvent" class="btn btn-default" onclick="document.getElementById('eventoID').value = '${eventItem.id}';document.getElementById('titolo').value = '${eventItem.titolo}';document.getElementById('luogo').value = '${eventItem.luogo}';document.getElementById('descrizione').value = '${eventItem.descrizione}'" style="float: right;">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </a>
                                    </td>    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="addEvent" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Aggiungi evento</h4>
                    </div>
                    <form action="/WebCommunity/administrationAddEvent" method="POST" data-toggle="validator">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Titolo</label>
                                <input type="text" class="form-control" name="titolo" placeholder="Titolo" required>
                            </div>
                            <div class="form-group">
                                <label>Luogo</label>
                                <input type="text" class="form-control" name="luogo" placeholder="Luogo" required>
                            </div>
                            <div class="form-group">
                                <label>Data</label>
                                <input type="date" class="form-control" name="data" required>
                            </div>
                            <div class="form-group">
                                <label>Categoria</label>
                                <select class="form-control" name="categoria">
                                    <c:forEach items="${categoriesList}" var="categoryItem">
                                        <option value="${categoryItem.id}">${categoryItem.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Descrizione</label>
                                <textarea type="text" class="form-control" name="descrizione" placeholder="Descrizione" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Inserisci</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="updateArtists" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modifica artisti</h4>
                    </div>
                    <form action="/WebCommunity/administrationUpdateArtists" method="POST" data-toggle="validator">
                        <input type="hidden" id="eventoID" name="eventoID" value="">
                        <div class="modal-body">
                            <c:forEach items="${artistsList}" var="artistItem">
                                <li style="display:inline;">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input type="checkbox" class="form-check-input" name="artists" value="${artistItem.id}">
                                            ${artistItem.nome} ${artistItem.cognome}
                                        </label>
                                    </div>
                                </li>
                            </c:forEach>
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
        <div class="modal fade" id="updateEvent" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modifica evento</h4>
                    </div>
                    <form action="/WebCommunity/administrationUpdateEvent" method="POST" data-toggle="validator">
                        <input type="hidden" id="eventoID" name="eventoID" value="">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Titolo</label>
                                <input type="text" class="form-control" id="titolo" name="titolo" placeholder="Titolo" required>
                            </div>
                            <div class="form-group">
                                <label>Luogo</label>
                                <input type="text" class="form-control" id="luogo" name="luogo" placeholder="Luogo" required>
                            </div>
                            <div class="form-group">
                                <label>Data</label>
                                <input type="date" class="form-control" id="data" name="data" required>
                                <div class="help-block">Questo campo deve essere riportato manualmente.</div>
                            </div>
                            <div class="form-group">
                                <label>Categoria</label>
                                <select class="form-control" id="categoria" name="categoria">
                                    <c:forEach items="${categoriesList}" var="categoryItem">
                                        <option value="${categoryItem.id}">${categoryItem.nome}</option>
                                    </c:forEach>
                                </select>
                                <div class="help-block">Questo campo deve essere riportato manualmente.</div>
                            </div>
                            <div class="form-group">
                                <label>Descrizione</label>
                                <textarea class="form-control" id="descrizione" name="descrizione" placeholder="Descrizione" required></textarea>
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
        <div class="modal fade" id="changePicture" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cambia immagine</h4>
                    </div>
                    <form method="POST" action="/WebCommunity/administrationUploadEventImage" enctype="multipart/form-data">
                        <input type="hidden" id="cameraFormID" name="eventID" value="" />
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
    </body>
</html>
