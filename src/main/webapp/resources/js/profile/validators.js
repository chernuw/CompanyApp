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


        var addEmployeeForm = document.getElementById("employeeForm");
        if (addEmployeeForm != null) {
            addEmployeeForm.addEventListener('submit', function (event) {
                if (validE === false || addEmployeeForm.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                addEmployeeForm.classList.add('was-validated');
            });
        }

        $("#birthDate").datepicker({
            dateFormat: "yy-mm-dd",
            maxDate: "-14y",
            minDate: "-100y",
            showOn: "both",
            buttonText: "Calendar",
            changeMonth: true,
            changeYear: true
        });

        $("#hireDate").datepicker({
            dateFormat: "yy-mm-dd",
            maxDate: "+2m",
            minDate: "-20y",
            showOn: "both",
            buttonText: "Calendar",
            changeMonth: true,
            changeYear: true
        });

        $("#phone").mask("9 (999) 999-99-99");
    }
);