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

        <title>Eventi | SoundZone</title>

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
                                                    <label>Nickname</label>
                                                    <input type="text" class="form-control" name="nickname" placeholder="Nickname" required>
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
                                            Ciao ${userinfo.nickname}
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
                        <h1><span class="popcolor">#${categoria}</span> <small>${subtitle}</small></h1>
                        <ul class="list-inline" style="float: right; z-index: 2; top: -6px; position: relative;">
                            <%
                                if (!((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == ""))) {
                            %>  
                                <li>
                                    <button class="btn btn-default dropdown-toggle action-dropdown" type="button" data-toggle="modal" data-target="#insertEvent">
                                        Inserisci evento
                                    </button>
                                </li>
                            <%
                                }
                            %>
                            <li style="padding-right: 0px;">
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle action-dropdown" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            Ordina eventi
                                            <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                        <li><a href="/WebCommunity/orderEventsByDate">Ordina per data</a></li>
                                        <li><a href="/WebCommunity/orderEventsByTitle">Ordina per titolo</a></li>
                                        <li><a href="/WebCommunity/orderEventsByCategory">Ordina per categoria</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row" style="text-align: center !important;">
                    <div class="card-deck">
                        <c:forEach items="${listaEventi}" var="eventItem">
                            <div class="card" style="width: 20rem; float: left; margin: 25px 10px 10px 10px;">
                                <c:if test="${evento.getAverage() != 'NaN'}">
                                    <div class="rating"><h3>${evento.getAverage()}★</h3></div>
                                </c:if>
                                <div class="card-block">
                                    <h4 class="card-title text-center">${evento.titolo}</h4>
                                    <p class="card-text text-justify">
                                        ${fn:substring(evento.descrizione, 0, 85)}...
                                    </p>
                                </div>
                                <div class="card-footer">
                                    <center><a href="eventDetail?id=${evento.id}" class="btn">Guarda</a></center>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
                            
        <!-- Modal -->
        <div class="modal fade" id="insertEvent" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Inserisci evento</h4>
                    </div>
                    <form action="/WebCommunity/addEvent" method="POST" data-toggle="validator">
                        <div class="modal-body">
                            <%
                                if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == ""))
                                {
                            %>
                            <h2>Non sei ancora registrato.</h2>
                            <h4>Puoi registrarti <a href="/WebCommunity/registration" style="color: rgb(241, 26, 147)">qui →</a></h4>
                            <%
                                } else
                                {
                            %>
                            <input type="hidden" name="promotore" value="${userinfo.nickname}" required>
                            <div class="form-group">
                                <label>Titolo</label>
                                <input type="text" class="form-control" name="titolo" placeholder="Titolo" required>
                            </div>
                            <div class="form-group">
                                <label>Location</label>
                                <input type="text" class="form-control" name="luogo" placeholder="Luogo" required>
                            </div>
                            <div class="form-group">
                                <label>Data</label>
                                <input type="date" class="form-control" name="data" required>
                            </div>
                            <div class="form-group">
                                <label>Categoria</label>
                                <select class="form-control" name="categoria">
                                    <c:forEach items="${listaCategorie}" var="categoria">
                                        <option value="${categoria.id}">${categoria.nome}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Descrizione</label>
                                <input type="text" class="form-control" name="descrizione" placeholder="Descrizione" required></input>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn" data-dismiss="modal">Annulla</button>
                            <button type="submit" class="btn">Inserisci</button>
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

