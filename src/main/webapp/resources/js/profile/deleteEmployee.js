function deleteEmployee() {
    var idEmp = $('#idEmployeeUpdate').val();
    $.post("EmployeeDelete", {
        idEmployee: idEmp
    })
        .done(function () {
        window.location.replace("EmployeeList");
    })
        .fail(function (data) {
            alert("Fail: " + data);
        });
}