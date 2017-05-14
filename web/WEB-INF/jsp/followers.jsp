<%@page import="POJO.Followers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Followers</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Homepage<span class="sr-only">(current)</span></a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        Eventi <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" style="color:#fff; background-color: #222;">
                        <li><a href="#" style="color:#9E9E9E;">Eventi in scadenza</a></li>
                        <li><a href="#" style="color:#9E9E9E;">Eventi passati</a></li>
                        <li><a href="#" style="color:#9E9E9E;">Tutti gli eventi</a>
                        <ul class="submenu">
                            <li><a href="#" style="color:#9E9E9E;">Oridnati alfabeticamente</a></li>
                            <li><a href="#" style="color:#9E9E9E;">Ordinati per categoria</a></li>
                            <li><a href="#" style="color:#9E9E9E;">CREA un evento</a></li>
                        </ul> 
                    </ul>
                </li>

                <li><a href="/RevolutionaryReviews/followers">Followers</a></li>
                <li><a href="#">Chi siamo</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 25px;">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;"><img src="/RevolutionaryReviews/resources/utente.ico" width="56px" style="top: -15px; position: relative;"/></a>
                    <ul class="dropdown-menu" style="color:#fff; background-color: #222;">
                    <div class="row">
                        <div class="col-md-12" style="width: 250px;">
                            <div class="row text-center">
                                Accedi
                            </div>
                            <form class="form" method="POST" action="/RevolutionaryReviews/doLogin" id="login-nav">
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
                    </div>
                    <div class="bottom text-center">
                        Non possiedi un account?
                        <br/>
                        <a href="/RevolutionaryReviews/registrazione.jsp" style="color:#9E9E9E;"><b>Registrati</b></a>
                    </div>
                    </ul>
                </li>
            </ul>
        </nav>
            <table class="table table-striped">
                <tr>
                    <td><h4>Nome</h4></td>
                    <td><h4>Cognome</h4></td>
                    <td><h4>Nickname</h4></td>
                    <td><h4>Provincia</h4></td>
                </tr>
                <c:forEach items="${followers}" var="followers">
                    <tr>
                        <td>${followers.getNome()}</td>
                        <td>${followers.getCognome()}</td>
                        <td>${followers.getNickname()}</td>
                        <td>${followers.getProvincia()}</td>
                    </tr>
                </c:forEach>
            </table>
   
    </body>
</html>
