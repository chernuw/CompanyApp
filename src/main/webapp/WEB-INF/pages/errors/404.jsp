<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageContext.errorData.statusCode} Error</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <br><br>
    <div class="col-12 bg-warning text-white status-code"><h3>HTTP Status ${pageContext.errorData.statusCode}</h3></div>
    <hr class="bg-warning">

    <div class="row col-12">
        <div class="bg-warning text-white">Message</div>
        <div class="col">${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</div>
    </div>

    <div class="row col-12">
        <div class="bg-warning text-white">Description</div>
        <div class="col">The origin server did not find a current representation for the target resource or is not
            willing to disclose that one exists.
        </div>
    </div>
    <hr class="bg-warning">
    <button class="btn btn-info" onclick="history.back()"><i class='fa fa-arrow-left'></i> Back</button>
</div>
</body>
</html>
