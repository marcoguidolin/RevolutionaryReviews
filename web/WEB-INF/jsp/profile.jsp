<%-- 
    Document   : profile
    Created on : 14-mag-2017, 16.02.00
    Author     : matte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                        if ((session.getAttribute("userinfo") == null) || (session.getAttribute("userinfo") == "")) {
        %>
        <h2>Per poter visualizzare il contenuto di questa pagina devi prima effettuare l'accesso.</h2>
        <h4>Se non possiedi un account puoi registrarti <a href="/RevolutionaryReviews/registrazione" style="color: rgb(241, 26, 147)">qui →</a></h4>
        <%
            } else {
        %>
        <h2 class="popcolor">Informazioni utente</h2>
        <h4><span class="glyphicon glyphicon-sunglasses" aria-hidden="true" style="margin-right: 10px;"></span>${userinfo.nickname}</h4>
        <h5><span class="glyphicon glyphicon-user" aria-hidden="true" style="margin-right: 10px;"></span> ${userinfo.nome} ${userinfo.cognome}</h5>
        <h5><span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="margin-right: 10px;"></span> ${userinfo.provincia}</h5>
        <a href="/RevolutionaryReviews/logout">Logout</a>
        <%
            }
        %>
    </body>
</html>
