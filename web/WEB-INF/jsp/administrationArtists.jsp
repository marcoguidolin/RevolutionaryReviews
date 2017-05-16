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


        <title>Artisti | Amministrazione</title>

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
                        <li class="active"><a href="#">Artisti</a></li>
                        <li><a href="/WebCommunity/administrationEvents">Eventi</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Artisti</h1>
                    <table class="table">
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                    <c:forEach items="${listaArtisti}" var="artisti">
                        <tr>
                            <td>${artisti.id}</td>
                            <td>${artisti.nome}</td>
                            <td>${artisti.cognome}</td>
                            <td>
                                <a onclick="window.location.href='/WebCommunity/doRemoveArtistAdmin?id=${artisti.id}'" class="btn btn-default" style="float: right;">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>