<%-- 
    Document   : fileview
    Created on : Mar 11, 2022, 2:10:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Submitted File</h2>
        <table>
            <tr>
                <td>OriginalFileName:</td>
                <td>${file.originalFilename}</td>
            </tr>
            <tr>
                <td>Type:</td>
                <td>${file.contentType}</td>
            </tr>
</table>
    </body>
</html>
