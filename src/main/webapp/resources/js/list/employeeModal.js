$(document).ready(function () {
    $("#profileModal")
        .on("hide.bs.modal", function () {
            document.getElementById("employeeForm").reset();
            $("#loginPasswordSection").removeAttr("hidden");
            $("#btnDeleteEmployee")
                .removeAttr("hidden")
                .removeAttr("onclick");
            $("#employeeId").val("");
        })
        .on("shown.bs.modal", function () {
            var id = $('#employeeId').val();
            if (id === "") {
                $("#loginPasswordSection").attr("hidden", "true");
                $("#btnDeleteEmployee").attr("hidden", "true");
            } else {
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
            getDepartments($('#department'), data.department);
            getPositions($('#position'), data.position);
            $('#hireDate').val(data.hireDate);
        })
        .fail(function (data) {
            alert("Fail: " + data);
        });
}


function getDepartments(select, employeeDepartment) {
    $.post("DepartmentList")
        .done(function (data) {
            JSON.parse(data).forEach(function (value) {
                if (value === employeeDepartment) {
                    select.append($('<option/>').text(value).attr("selected", "selected"));
                } else {
                    select.append($('<option/>').text(value));
                }
            });
        })
        .fail(function () {
            alert("fail");
        });
}

function getPositions(select, employeePosition) {
    $.post("PositionList")
        .done(function (data) {
            JSON.parse(data).forEach(function (value) {
                if (value === employeePosition) {
                    select.append($('<option/>').text(value).attr("selected", "selected"));
                } else {
                    select.append($('<option/>').text(value));
                }
            });
        })
        .fail(function () {
            alert("fail");
        });
}