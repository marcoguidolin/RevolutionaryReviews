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
