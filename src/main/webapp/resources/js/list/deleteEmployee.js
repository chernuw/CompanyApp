function deleteEmployee(id) {
    $.post("EmployeeDelete", {
        employeeId: id
    })
        .done(function () {
            buildEmployeesTable(1);
        })
        .fail(function () {
            alert("Failed delete");
        });
}