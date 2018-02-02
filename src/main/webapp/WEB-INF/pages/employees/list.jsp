<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/docs.min.css">
    <link rel="stylesheet" href="resources/css/list.css">
    <link rel="stylesheet" href="resources/css/jquery-ui.css">
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="resources/js/jquery.maskedinput.js"></script>
    <script type="text/javascript" src="resources/js/popper.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/list/addDepartmentPosition.js"></script>
    <script type="text/javascript" src="resources/js/list/employeesTable.js"></script>
    <script type="text/javascript" src="resources/js/list/positionDepartmentList.js"></script>
    <script type="text/javascript" src="resources/js/validators.js"></script>
</head>
<body>

<header class="header-panel">
    <jsp:include page="/WEB-INF/pages/template/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <%--Left menu--%>
        <nav class="col-2 bg-light border">
            <div class="collapse bd-links">
                <div class="bd-toc-item">
                    <a class="bd-toc-link" data-toggle="modal" href="#addDepartmentModal">
                        <span class="text-success">&#10010;</span> Department
                    </a>
                </div>
                <div class="bd-toc-item">
                    <a class="bd-toc-link" data-toggle="modal" href="#addPositionModal">
                        <span class="text-success">&#10010;</span> Position
                    </a>
                </div>
            </div>
        </nav>
        <%-- Modal adding a department--%>
        <div class="modal fade" id="addDepartmentModal" tabindex="-1" role="dialog"
             aria-labelledby="addDepartmentModalTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addDepartmentModalTitle">Add department</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="DepartmentAdd" id="addDepartmentForm" method="post">
                        <div class="form-group">
                            <div class="modal-body">
                                <div class="form-group form-row">
                                    <label class="col-4 col-form-label" for="departmentName">Department name:</label>
                                    <input type="text" id="departmentName" name="departmentName" required
                                           placeholder="Department name" class="form-control col-8"
                                           maxlength="45">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%-- Modal adding a position--%>
        <div class="modal fade" id="addPositionModal" tabindex="-1" role="dialog"
             aria-labelledby="addPositionModalTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addPositionModalTitle">Add position</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="PositionAdd" id="addPositionForm" method="post">
                        <div class="form-group">
                            <div class="modal-body">
                                <div class="form-group form-row">
                                    <label class="col-4 col-form-label" for="positionTitle">Position title:</label>
                                    <input type="text" id="positionTitle" name="positionTitle" required
                                           placeholder="Position title" class="form-control col-8"
                                           maxlength="45">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%--Main section of page--%>
        <main role="main" class="col-10 pt-4 px-4 bd-content justify-content-center">
            <div class="row py-4">
                <div class="justify-content-start col-6">
                    <%--Button for showing modal adding employee--%>
                    <button type="button" id="profile_edit_button" data-toggle="modal"
                            data-target="#addProfileModal"
                            class="btn btn-success">
                        New...
                    </button>
                    <%--Modal adding employee--%>
                    <div id="add_profile_modal">
                        <div class="modal fade" id="addProfileModal" tabindex="-1" role="dialog"
                             aria-labelledby="addProfileTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addProfileTitle">Add profile</h5>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="EmployeeAdd" id="addEmployeeForm" method="post" novalidate
                                          class="needs-validation">
                                        <div class="form-group">
                                            <div class="modal-body">
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="firstName">First
                                                        Name:</label>
                                                    <input type="text" id="firstName" name="firstName" required
                                                           placeholder="First Name" class="form-control col-9"
                                                           maxlength="15">
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="middleName">Middle
                                                        Name:</label>
                                                    <input type="text" id="middleName" name="middleName"
                                                           placeholder="Middle Name" class="form-control col-9"
                                                           maxlength="20">
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="lastName">Last
                                                        Name:</label>
                                                    <input type="text" id="lastName" name="lastName" required
                                                           placeholder="Last Name" class="form-control col-9"
                                                           maxlength="20">
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="birthDate">Birth
                                                        Date:</label>
                                                    <input type="text" id="birthDate" name="birthDate" required
                                                           readonly class="form-control col-7"
                                                           placeholder="YYYY-MM-DD">
                                                </div>
                                                <hr>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="email">Email:</label>
                                                    <input type="email" id="email" name="email" required
                                                           maxlength="40" placeholder="Email"
                                                           class="form-control col-9">
                                                    <div class="invalid-feedback" id="email_message"></div>
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="phone">Phone:</label>
                                                    <input type="tel" id="phone" name="phone"
                                                           placeholder="8 (055) 555-55-55"
                                                           class="form-control col-9" required>
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label"
                                                           for="address">Address:</label>
                                                    <input type="text" id="address" name="address" required
                                                           maxlength="100" placeholder="Address"
                                                           class="form-control col-9">
                                                </div>
                                                <hr>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label"
                                                           for="department">Department:</label>
                                                    <select id="department" name="department"
                                                            class="form-control col-9"></select>
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label"
                                                           for="position">Position:</label>
                                                    <select id="position" name="position" required
                                                            class="form-control col-9"></select>
                                                </div>
                                                <div class="form-group form-row">
                                                    <label class="col-3 col-form-label" for="hireDate">Hire
                                                        Date:</label>
                                                    <input type="text" id="hireDate" name="hireDate"
                                                           class="form-control col-7"
                                                           placeholder="YYYY-MM-DD"
                                                           required readonly>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="reset" class="btn btn-secondary"
                                                        data-dismiss="modal">
                                                    Reset
                                                </button>
                                                <input type="submit" class="btn btn-primary" value="Save"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--Selections for oredering and count employees on page--%>
                <div class="justify-content-end btn-toolbar col-6" role="toolbar">
                    <div class="input-group col-7">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="orderByText">Order by: </span>
                        </div>
                        <select class="form-control" id="orderByOnPage" aria-describedby="orderByText"
                                onchange="buildEmployeesTable(1)">
                            <option selected value="id">Addition date</option>
                            <option value="lastName">Last Name</option>
                            <option value="department">Department</option>
                            <option value="position">Position</option>
                            <option value="hireDate">Work Experience</option>
                        </select>
                    </div>
                    <div class="input-group col-4">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="onPageText">On page: </span>
                        </div>
                        <select class="form-control" id="amountEmployeesOnPage" aria-describedby="onPageText"
                                onchange="buildEmployeesTable(1)">
                            <option>1</option>
                            <option selected>5</option>
                            <option>10</option>
                            <option>15</option>
                        </select>
                    </div>
                </div>
            </div>

            <label for="tbl">Amount employees: <span id="amountEmployees">${amountEmployees}</span></label>
            <div id="tbl" class="table-responsive"></div>
            <hr>
            <div id="pagination"></div>
        </main>
    </div>
</div>

<script type="text/javascript">buildEmployeesTable(1);</script>
<script type="text/javascript">addPositionsDepartmentsInModal();</script>
</body>
</html>
