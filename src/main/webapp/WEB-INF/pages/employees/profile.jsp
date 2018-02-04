<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee form</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/profile.css">
    <link rel="stylesheet" href="resources/css/jquery-ui.css">
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="resources/js/jquery.maskedinput.js"></script>
    <script type="text/javascript" src="resources/js/popper.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/profile/fillingPage.js"></script>
    <script type="text/javascript" src="resources/js/profile/changePassword.js"></script>
    <script type="text/javascript" src="resources/js/profile/photoActions.js"></script>
    <script type="text/javascript" src="resources/js/profile/validators.js"></script>
</head>
<body>
<header class="header-panel">
    <jsp:include page="/WEB-INF/pages/template/header.jsp"/>
</header>
<div class="container col-9 justify-content-center">
    <div class="row">
        <%--Left column with photo--%>
        <div class="photo_column col-4">
            <div class="page_block page_photo">
                <aside aria-label="Photo">
                    <%--Click on photo - show modal for editing photo--%>
                    <a data-toggle="modal" href="#photoModal">
                        <img class="rounded col" id="employee_photo" src=""/>
                    </a>
                        <%--Photo modal--%>
                    <div id="photo_actions">
                        <div class="modal fade" id="photoModal" tabindex="-1" role="dialog"
                             aria-labelledby="photoUploadTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="photoUploadTitle">Edit photo</h5>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div>
                                        <form name="photoUploadForm" id="photoUploadForm" method="post"
                                              enctype="multipart/form-data">
                                            <input type="hidden" name="idEmployeePhoto" id="idEmployeePhoto">
                                            <div class="form-group">
                                                <div class="modal-body">
                                                    <label for="photoInput">Choose photo</label>
                                                    <input type="file" class="form-control-file" id="photoInput"
                                                           name="photoInput">
                                                    <div id="progressNumber"></div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">
                                                        Close
                                                    </button>
                                                    <button type="button" class="btn btn-danger"
                                                            onclick="deletePhoto()">
                                                        Delete
                                                    </button>
                                                    <button type="button" class="btn btn-primary"
                                                            onclick="upload()">
                                                        Save
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
                <aside aria-label="Profile_actions">
                    <div class="page_actions clearfix">
                        <%--Show profile edite modal--%>
                        <button type="button" id="profile_edit_button" data-toggle="modal"
                                data-target="#profileModal" class="btn col-12">
                            Edit
                        </button>
                            <%--Modal for changing password. Calling from profile editing modal--%>
                        <div id="password_change_modal">
                            <div class="modal fade" id="changePasswordModal" tabindex="1" role="dialog"
                                 aria-labelledby="changePasswordTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="changePasswordTitle">Change password</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form method="post" id="changePasswordForm" class="" novalidate>
                                            <input type="hidden" name="idEmployeePassword" id="idEmployeePassword">
                                            <div class="form-group">
                                                <div class="modal-body">
                                                    <div class="alert alert-danger" id="passDangAlert"
                                                         role="alert">
                                                    </div>
                                                    <div class="alert alert-success" id="passSucChangAlert"
                                                         role="alert">
                                                        Password successfully updated
                                                    </div>
                                                    <div class="form-group form-row">
                                                        <label class="col-5 col-form-label" for="currentPassword">
                                                            Current password:
                                                        </label>
                                                        <div class="col-7">
                                                            <input type="password" id="currentPassword"
                                                                   name="oldPassword" placeholder="Current password"
                                                                   class="form-control" minlength="4" maxlength="30"
                                                                   required>
                                                            <div class="invalid-feedback">
                                                                Enter current password
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group form-row">
                                                        <label class="col-5 col-form-label" for="newPassword">
                                                            New password:
                                                        </label>
                                                        <div class="col-7">
                                                            <input type="password" id="newPassword" name="newPassword"
                                                                   placeholder="New password" class="form-control"
                                                                   minlength="4" maxlength="30" required>
                                                            <div class="invalid-feedback">
                                                                Minimum password length 4 symbols
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group form-row">
                                                        <label class="col-5 col-form-label" for="repeatPassword">
                                                            Repeat new password
                                                        </label>
                                                        <div class="col-7">
                                                            <input type="password" id="repeatPassword"
                                                                   name="repeatPassword" placeholder="Repeat password"
                                                                   class="form-control" minlength="4" maxlength="30"
                                                                   required>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            Close
                                                        </button>
                                                        <input type="submit" class="btn btn-primary" value="Save">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <%--Modal for edit profile--%>
                        <div id="edit_profile_modal">
                            <div class="modal fade" id="profileModal" tabindex="-1" role="dialog"
                                 aria-labelledby="editProfileTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editProfileTitle">Edit profile</h5>
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="EmployeeSave" id="updateEmployeeForm" method="post"
                                              class="needs-validation" novalidate>
                                            <input type="hidden" name="employeeId" id="employeeId">
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
                                                        <label class="col-3 col-form-label" for="login">Login:</label>
                                                        <input type="text" id="login" name="login" required
                                                               maxlength="40" placeholder="Login"
                                                               class="form-control col-9">
                                                        <div class="invalid-feedback" id="login_message"></div>
                                                    </div>
                                                    <div class="form-group form-row">
                                                        <label class="col-3 col-form-label" for="passwordChangeButton">
                                                            Password:
                                                        </label>
                                                        <%--button for calling modal change password--%>
                                                        <button class="btn btn-info col-9" id="passwordChangeButton"
                                                                type="button" data-toggle="modal"
                                                                data-target="#changePasswordModal"
                                                                data-dismiss="modal">
                                                            Change password
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">
                                                        Close
                                                    </button>
                                                    <button type="reset" class="btn btn-secondary">
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
                </aside>
            </div>
        </div>
            <%--right column on page with employee information--%>
        <div id="data_column" class="data_column col-8">
            <div class="page_block">
                <div id="page_info_wrap" class="page_info_wrap">
                    <h5 class="employee_name" id="employeeName"></h5>
                    <div class="profile_info_block clearfix">
                        <div class="profile_info_row row clearfix">
                            <div class="profile_info_label col-4">Birth Date:</div>
                            <div class="labeled col-8" id="birthDatePage"></div>
                        </div>
                    </div>
                    <div class="profile_info_block clearfix">
                        <div class="profile_info_header_wrap">
                            <span class="profile_info_header">Contact information</span>
                        </div>
                        <div class="profile_info">
                            <div class="profile_info_row row clearfix">
                                <div class="profile_info_label col-4">Email:</div>
                                <div class="labeled col-8" id="emailPage"></div>
                            </div>
                            <div class="profile_info_row row clearfix">
                                <div class="profile_info_label col-4">Phone:</div>
                                <div class="labeled col-8" id="phonePage"></div>
                            </div>
                            <div class="profile_info_row row  clearfix">
                                <div class="profile_info_label col-4">Address:</div>
                                <div class="labeled col-8" id="addressPage"></div>
                            </div>
                        </div>
                    </div>
                    <div class="profile_info_block clearfix">
                        <div class="profile_info_header_wrap">
                            <span class="profile_info_header">Career</span>
                        </div>
                        <div class="profile_info">
                            <div class="profile_info_row row clearfix">
                                <div class="profile_info_label col-4">Hire Date:</div>
                                <div class="labeled col-8" id="hireDatePage">
                                </div>
                            </div>
                            <div class="profile_info_row row clearfix">
                                <div class="profile_info_label col-4">Department:</div>
                                <div class="labeled col-8" id="departmentPage"></div>
                            </div>
                            <div class="profile_info_row row clearfix">
                                <div class="profile_info_label col-4">Position:</div>
                                <div class="labeled col-8" id="positionPage"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">fillPage();</script>
</body>
</html>
