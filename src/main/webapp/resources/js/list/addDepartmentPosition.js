$(document).ready(function () {
    document.getElementById("addDepartmentForm").addEventListener('submit', function (event) {
        event.preventDefault();

        $.post("DepartmentAdd", {
            departmentName: $("#departmentName").val()
        })
            .done(function () {
                document.getElementById("addDepartmentForm").reset();
                alert("Department adding");
            })
            .fail(function () {
                alert("Fail on adding department");
            });
    });
    document.getElementById("addPositionForm").addEventListener('submit', function (event) {
        event.preventDefault();

        $.post("PositionAdd", {
            positionTitle: $("#positionTitle").val()
        })
            .done(function () {
                document.getElementById("addPositionForm").reset();
                alert("Position adding");
            })
            .fail(function () {
                alert("Fail on adding position");
            });
    });
});