$(document).ready(function () {

        var validL = false;
        var validE = false;

        $("#email").change(function () {
            validEmail();
        });


        $("#login").change(function () {
            validLogin();
        });

        $("#updateProfileModal").on('show.bs.modal', function () {
            validEmail();
            validLogin();
        });

        function validLogin() {
            $.post("LoginValidator", {
                login: $("#login").val(),
                empId: $("#employeeId").val()
            }).done(function (data) {
                if (data.valid === true) {
                    $("#login_message").text("");
                    $("#login").removeClass("is-invalid");
                    validL = true;
                } else {
                    $("#login_message").text("Duplicate login, choose another");
                    $("#login").addClass("is-invalid");
                    validL = false;
                }
            });
        }

        function validEmail() {
            $.post("EmailValidator", {
                email: $("#email").val(),
                empId: $("#employeeId").val()
            }, function (data) {
                if (data.valid === true) {
                    $("#email_message").text("");
                    $("#email").removeClass("is-invalid");
                    validE = true;
                } else {
                    $("#email_message").text("Duplicate email, choose another");
                    $("#email").addClass("is-invalid");
                    validE = false;
                }
            });
        }

        var updateEmployeeForm = document.getElementById("updateEmployeeForm");
        if (updateEmployeeForm != null) {
            updateEmployeeForm.addEventListener('submit', function (event) {
                if (validL === false || validE === false || updateEmployeeForm.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                updateEmployeeForm.classList.add('was-validated');
            });
        }


        var employeeForm = document.getElementById("employeeForm");
        if (employeeForm != null) {
            employeeForm.addEventListener('submit', function (event) {
                if (validE === false || employeeForm.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                employeeForm.classList.add('was-validated');
            });
        }

        $("#birthDate").datepicker({
            dateFormat: "yy-mm-dd",
            maxDate: "-14y",
            minDate: "-100y",
            changeMonth: true,
            changeYear: true
        });

        $("#hireDate").datepicker({
            dateFormat: "yy-mm-dd",
            maxDate: "+2m",
            minDate: "-20y",
            changeMonth: true,
            changeYear: true
        });

        $("#phone").mask("9 (999) 999-99-99");
    }
);