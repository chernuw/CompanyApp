$(document).ready(function () {

    $("#repeatPassword").on("change", function () {
        diffNewAndRepPass();
    });

    $("#newPassword").on("change", function () {
        diffNewAndRepPass();
        $("#passSucChangAlert").css('display', 'none');
    });

    $("#currentPassword").on("change", function () {
        $("#currentPassword").removeClass("is-invalid");
    });

    function diffNewAndRepPass() {
        if ($("#newPassword").val() !== $("#repeatPassword").val()) {
            $("#repeatPassword")
                .removeClass("is-valid")
                .addClass("is-invalid");
            $("#passDangAlert").css('display', 'block').text("New password and repeated new password did" +
                " not match.");
            return true;
        } else {
            $("#repeatPassword").removeClass("is-invalid");
            $("#passDangAlert")
                .css('display', 'none')
                .text("");
            return false;
        }
    }

    document.getElementById("changePasswordForm").addEventListener('submit', function (event) {
        event.preventDefault();
        if (document.getElementById("changePasswordForm").checkValidity() === false || diffNewAndRepPass() === true) {
            event.stopPropagation();
        } else {
            document.getElementById("changePasswordForm").classList.add('was-validated');
            $.post("UserChangePassword", {
                currentPassword: $("#currentPassword").val(),
                newPassword: $("#newPassword").val(),
                idUser: $("#idEmployeePassword").val()
            })
                .done(function (data) {
                    if (data.toString().match(/^FAIL: /)) {
                        var message = data.toString().substr(6);
                        $("#passDangAlert").css('display', 'block').text(message);
                        switch (message) {
                            case 'Invalid current password': {
                                document.getElementById("changePasswordForm").classList.remove('was-validated');
                                $("#currentPassword")
                                    .removeClass("is-valid")
                                    .addClass("is-invalid");
                                break;
                            }
                            default: {
                                alert(message);
                            }
                        }
                    } else {
                        $("#changePasswordForm")[0].reset();
                        $("#passSucChangAlert").css('display', 'block');
                    }
                })
                .fail(function (data) {
                    alert("Fail: " + data);
                });
        }
    });

    $("#changePasswordModal")
        .on('hidden.bs.modal', function () {
            $("#changePasswordForm")[0].reset();
        })
        .on('show.bs.modal', function () {
            document.getElementById("changePasswordForm").classList.remove('was-validated');
            $("#passDangAlert")
                .css('display', 'none')
                .text("");
            $("#passSucChangAlert").css('display', 'none');
        });

});
