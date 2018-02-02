<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet" href="resources/css/header.css">
</head>
<nav class="navbar navbar-expand-lg custom-header">
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <p2 id="logo"><b>CoApp</b></p2>
    </div>
    <div class="btn-group">
        <button id="buttonDropMenu" type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false">
            <b>${sessionScope.userLogin}</b>
        </button>
        <div class="dropdown-menu dropdown-menu-right" id="drop_down_menu">
            <a class="dropdown-item" href="EmployeeProfile?id=${sessionScope.userId}">Profile</a>
            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                <a class="dropdown-item" href="EmployeeList">Admin section</a>
            </c:if>
            <div class="dropdown-divider"></div>
            <form action="Logout" method="post">
                <button type="submit" class="dropdown-item">Logout</button>
            </form>
        </div>
    </div>
</nav>


