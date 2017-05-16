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
                    <h1 class="page-header">
                        Eventi
                        <a href="#" data-toggle="modal" data-target="#addEvent" class="btn btn-default" style="float: right;">Inserisci evento</a>
                    </h1>
                    <table class="table table-striped" style="width: 2000px !important; max-width: 2000px !important;">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Titolo</th>
                                <th>Location</th>
                                <th>Utente creatore</th>
                                <th>Data</th>
                                <th>Categoria</th>
                                <th>Descrizione</th>
                                <th>Programma</th>
                                <th>Artisti</th>
                                <th>Azioni</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaEventi}" var="evento">
                                <tr>
                                    <td>${evento.id}</td>
                                    <td><a href="/WebCommunity/eventDetail?id=${evento.id}">${evento.titolo}</a></td>
                                    <td>${evento.luogo}</td>
                                    <td>${evento.promotore.username}</td>
                                    <td>${evento.data}</td>
                                    <td><a href="/WebCommunity/administrationCategories/">${evento.categoria.nome}</a></td>
                                    <td width="500px">${evento.descrizione}</td>
                                    <td>
                                        <ul class="list-unstyled">
                                            <c:choose>
                                                <c:when test="${empty eventp.artistaList}">
                                                    Nessun artista partecipante
                                                    <br><br>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${evento.listaArtisti}" var="artista">
                                                        <li>
                                                            <a href="/WebCommunity/administrationArtists/">${artista.nome} ${artista.cognome}</a>
                                                            <a onclick="window.location.href='/WebCommunity/administrationRemoveArtistEvent?eventID=${evento.id}&artistID=${artista.id}'" class="btn btn-default btn-xs" style="float: right;">
                                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                            </a>
                                                        </li>
                                                        <hr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <center>
                                                <a href="#" data-toggle="modal" data-target="#updateArtists" class="btn btn-default btn-xs" onclick="document.getElementById('eventoID').value = '${evento.id}'" >
                                                    Aggiungi
                                                </a>
                                            </center>
                                        </ul>
                                    </td>
                                    <td>
                                        <a onclick="window.location.href='/WebCommunity/administrationRemoveEvent?id=${evento.id}'" class="btn btn-default" style="float: right;">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#changePicture" class="btn btn-default" onclick="document.getElementById('cameraFormID').value = '${evento.id}'" style="float: right;">
                                            <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#updateEvent" class="btn btn-default" onclick="document.getElementById('eventoID').value = '${evento.id}';document.getElementById('titolo').value = '${evento.titolo}';document.getElementById('luogo').value = '${evento.location}';document.getElementById('descrizione').value = '${evento.descrizione}'" style="float: right;">
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
                                <label>Location</label>
                                <input type="text" class="form-control" name="luogo" placeholder="Luogo" required>
                            </div>
                            <div class="form-group">
                                <label>Utente Creatore</label>
                                <input type="text" class="form-control" name="promotore" placeholder="Promotore" required>
                            </div>
                            <div class="form-group">
                                <label>Data</label>
                                <input type="date" class="form-control" name="data" required>
                            </div>
                            <div class="form-group">
                                <label>Categoria</label>
                                <select class="form-control" name="categoria">
                                    <c:forEach items="${listaCategorie}" var="categoriaDett">
                                        <option value="${categoriaDett.id}">${categoriaDett.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Descrizione</label>
                                <textarea type="text" class="form-control" name="descrizione" placeholder="Descrizione" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Programma</label>
                                <textarea type="text" class="form-control" name="programma" placeholder="Programma" required></textarea>
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
                            <c:forEach items="${listaArtisti}" var="artista">
                                <li style="display:inline;">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input type="checkbox" class="form-check-input" name="artists" value="${artista.id}">
                                            ${artista.nome} ${artista.cognome}
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
                                    <c:forEach items="${listaCategorie}" var="categoriaDett">
                                        <option value="${categoriaDett.id}">${categoriaDett.nome}</option>
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
    </body>
</html>
