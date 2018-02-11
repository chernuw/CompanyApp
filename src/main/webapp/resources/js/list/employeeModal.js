$(document).ready(function () {
    $("#profileModal")
        .on("hide.bs.modal", function () {
            document.getElementById("employeeForm").reset();
            $("#loginPasswordSection").removeAttr("hidden");
            $("#btnDeleteEmployee")
                .removeAttr("hidden")
                .removeAttr("onclick");
            $("#employeeId").val("");
            $("#department option").each(function () {
                $(this).removeAttr("selected");
            });
            $("#position option").each(function () {
                $(this).removeAttr("selected");
            });
        })
        .on("shown.bs.modal", function () {
            var id = $('#employeeId').val();
            if (id === "") {
                $("#loginPasswordSection").attr("hidden", "true");
                $("#login").attr("disabled", "disabled");
                $("#btnDeleteEmployee").attr("hidden", "true");
            } else {
                $("#login").removeAttr("disabled");
                $("#btnDeleteEmployee").attr("onclick", "deleteEmployee(" + id + ");");
            }
        });
});

function fillModal(id) {
    $.post("EmployeeProfileData", {
        id: id
    })
        .done(function (list) {
            var data = JSON.parse(list);
            $('#employeeId').val(data.employeeId);
            $('#idEmployeePassword').val(data.employeeId);
            $("#login").val(data.user.login);
            $('#firstName').val(data.firstName);
            $('#middleName').val(data.middleName);
            $('#lastName').val(data.lastName);
            $('#birthDate').val(data.birthDate);
            $('#email').val(data.email);
            $('#phone').val(data.phone);
            $('#address').val(data.address);
            selectDepartment(data.department);
            selectPosition(data.position);
            $('#hireDate').val(data.hireDate);
        })
        .fail(function (data) {
            alert("Fail: " + data);
        });
}

function selectDepartment(employeeDepartment) {
    $("#department option").each(function () {
        if ($(this).val() === employeeDepartment) {
            $(this).attr("selected", "selected");
        }
    });
}

function selectPosition(employeePosition) {
    $("#position option").each(function () {
        if ($(this).val() === employeePosition) {
            $(this).attr("selected", "selected");
        }
    });
}