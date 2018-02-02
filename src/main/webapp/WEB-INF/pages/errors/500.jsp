<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="bg-warning text-white">Type</div>
        <div class="col">Exception Report</div>
    </div>
    <br>

    <div class="row col-12">
        <div>
            <div class="bg-warning text-white">Message</div>
        </div>
        <div class="col">
            ${pageContext.exception.message}
        </div>
    </div>
    <br>
    <div class="row col-12">
        <div class="bg-warning text-white">
            Description
        </div>
        <div class="col">
            The server encountered an unexpected condition that prevented it from fulfilling the request.
        </div>
    </div>
    <br>

    <div class="row col-12">
        <div>
            <div class="bg-warning text-white">Exception</div>
        </div>
        <div class="col">
            ${pageContext.out.flush(); pageContext.exception.printStackTrace(pageContext.response.writer)}
        </div>
    </div>

    <hr class="bg-warning">
    <button class="btn btn-info" onclick="history.back()"><i class='fa fa-arrow-left'></i> Back</button>
</div>

</body>
</html>
