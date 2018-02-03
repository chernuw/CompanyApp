function fillPage() {
    var request = new XMLHttpRequest();
    var link = document.location.search.toString();
    var id = link.substr(link.lastIndexOf('id=') + 3);
    var roles = ["USER", "ADMIN"];
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var list = JSON.parse(this.responseText);
            $("#employee_photo").attr("src", "EmployeePhoto?id=" + id);
            $('#idEmployeePhoto').val(list.employeeId);
            $('#idEmployeePassword').val(list.employeeId);
            $('#employeeId').val(list.employeeId);
            $('#login').val(list.user.login);
            roles.forEach(function (value) {
                if (value === list.user.role) {
                    $("#role").append($('<option/>').text(value).attr("selected", "selected"));
                } else {
                    $("#role").append($('<option/>').text(value));
                }
            });
            $('#firstName').val(list.firstName);
            $('#middleName').val(list.middleName);
            $('#lastName').val(list.lastName);
            $('#birthDate').val(list.birthDate);
            $('#email').val(list.email);
            $('#phone').val(list.phone);
            $('#address').val(list.address);
            getDepartments($('#department'), list.department);
            getPositions($('#position'), list.position);
            $('#hireDate').val(list.hireDate);
            $('#employeeName').text(list.firstName + " " + list.middleName + " " + list.lastName);
            $('#birthDatePage').text(new Date(list.birthDate).toLocaleDateString());
            $('#emailPage').text(list.email);
            $('#phonePage').text(list.phone);
            $('#addressPage').text(list.address);
            $('#hireDatePage').text(new Date(list.hireDate).toLocaleDateString());
            $('#departmentPage').text(list.department);
            $('#positionPage').text(list.position);

        }
    };
    request.open("POST", "EmployeeProfileData?id=" + id, true);
    request.send();
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