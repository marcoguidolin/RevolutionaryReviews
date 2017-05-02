<%-- 
    Document   : administrationCategories
    Created on : 9-apr-2017, 19.45.27
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

        <title>Categorie | Amministrazione</title>

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
                        <li class="active"><a href="#">Categorie</a></li>
                        <li><a href="/WebCommunity/administrationArtists">Artisti</a></li>
                        <li><a href="/WebCommunity/administrationEvents">Eventi</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">
                        Categorie
                        <a href="#" data-toggle="modal" data-target="#addCategory" class="btn btn-default" style="float: right;">Inserisci categoria</a>
                    </h1>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Immagine</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${categoriesList}" var="categoriesItem">
                                <tr>
                                    <td>${categoriesItem.id}</td>
                                    <td>${categoriesItem.nome}</td>
                                    <td width="64px">
                                        <img src="
                                            <c:choose>
                                                <c:when test="${categoriesItem.immagine != null}">
                                                    data:image/png;base64,${categoriesItem.getImmagineString()}
                                                </c:when>
                                                <c:otherwise>
                                                    /WebCommunity/resources/category.png
                                                </c:otherwise>
                                            </c:choose>
                                        " alt="user-picture" class="img-circle"/>
                                    </td>
                                    <td>
                                        <a onclick="window.location.href='/WebCommunity/administrationRemoveCategory?id=${categoriesItem.id}'" class="btn btn-default" style="float: right;">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#changePicture" class="btn btn-default" onclick="document.getElementById('cameraFormID').value = '${categoriesItem.id}'" style="float: right;">
                                            <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                                        </a>
                                        <a href="#" data-toggle="modal" data-target="#updateCategory" class="btn btn-default" onclick="document.getElementById('categoriaID').value = '${categoriesItem.id}';document.getElementById('nome').value = '${categoriesItem.nome}';" style="float: right;">
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
        <div class="modal fade" id="addCategory" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Aggiungi categoria</h4>
                    </div>
                    <form action="/WebCommunity/administrationAddCategory" method="POST" data-toggle="validator">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Nome</label>
                                <input type="text" class="form-control" name="nome" placeholder="Nome" required>
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
        <div class="modal fade" id="updateCategory" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modifica categoria</h4>
                    </div>
                    <form action="/WebCommunity/administrationUpdateCategory" method="POST" data-toggle="validator">
                        <input type="hidden" id="categoriaID" name="categoriaID" value="">
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Nome</label>
                                <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" required>
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
                    <form method="POST" action="/WebCommunity/administrationUploadCategoryImage" enctype="multipart/form-data">
                        <input type="hidden" id="cameraFormID" name="categoryID" value="" />
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
