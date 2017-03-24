<%-- 
    Document   : membriList
    Created on : 23-mar-2017, 13.33.45
    Author     : FSEVERI\parlato2889
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <ul>
        <c:forEach items="${membriList}" var="membriItem">
            <li>${membriItem.nickname}</li>
        </c:forEach>
        </ul>
    </body>
</html>
