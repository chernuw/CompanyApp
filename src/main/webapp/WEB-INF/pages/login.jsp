<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/signin.css">
</head>
<body>
<div class="container">
    <form action="Authenticate" method="post" class="form-signin">
        <h2 class="form-signin-heading">Sign in</h2>
        <input name="login" placeholder="Login" required autofocus class="form-control">
        <input type="password" name="password" placeholder="Password" required class="form-control">
        <c:if test="${message ne null}">
            <div class="alert alert-danger" role="alert">${message}</div>
        </c:if>
        <input type="submit" value="Sign in" class="btn btn-lg btn-primary btn-block">
    </form>
</div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="resources/js/jquery-3.2.1.slim.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
