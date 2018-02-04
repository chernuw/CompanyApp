function buildEmployeesTable(page) {

    $.post("EmployeesAmount")
        .done(function (data) {
            $("#amountEmployees").text(data.amount);
        })
        .fail(function () {
            alert("Failed to get the number of employees")
        });

    var amountOnPage = document.getElementById("amountEmployeesOnPage").value;
    var orderField = document.getElementById("orderByOnPage").value;

    $.post("EmployeeListData", {
        page: page,
        amountOnPage: amountOnPage,
        orderField: orderField
    })
        .done(function (data) {
            $("#employeeTableHead").empty();
            $("#employeeTableBody").empty();
            //table head
            var columns = [' ', 'Contact Info', 'Department', 'Position', 'In Company'];
            columns.forEach(function (value) {
                $('<th/>').html(value).appendTo("#employeeTableHead");
            });
            //table body
            var list = JSON.parse(data);
            $.each(list, function (i, item) {
                $('<tr>').append(
                    $('<td>')
                        .append($('<img>')
                            .attr("src", "EmployeePhoto?id=" + item.employeeId)
                            .attr("name", "emplPhoto")
                            .addClass("rounded")),
                    $('<td>')
                        .append(
                            $('<a>')
                                .attr("href", "javascript:void(0);")
                                .attr("onclick", "fillModal(" + item.employeeId + "); " +
                                    "$('#profileModal').modal('show');")
                                .text(item.firstName + ' ' + item.lastName),
                            $('<div>').html('&#128222;' + " " + item.phone),
                            $('<div>').html('&#128231;' + " " + item.email)
                        ),
                    $('<td>').text(item.department),
                    $('<td>').text(item.position),
                    $('<td>').text(getWorkExperience(item.hireDate) + " years")
                ).appendTo('#employeeTableBody');
            });

            //pagination
            $("#pagi").empty();
            var amountEmpl = document.getElementById("amountEmployees").innerText;
            var amountOfPages = (amountEmpl % amountOnPage === 0) ? (amountEmpl / amountOnPage) : (amountEmpl / amountOnPage + 1);
            for (var j = 1; j <= amountOfPages; j++) {
                if (j === page) {
                    $("#pagi")
                        .append($('<li>')
                            .attr("onclick", "return false;")
                            .addClass("page-item active")
                            .append($('<span>')
                                .addClass("page-link")
                                .text(j)));
                } else {
                    $("#pagi")
                        .append($('<li>')
                            .attr("onclick", "buildEmployeesTable(" + j + ");")
                            .addClass("page-item")
                            .append($('<span>')
                                .addClass("page-link")
                                .text(j)));
                }
            }
        });
}

function getWorkExperience(hireDate) {
    var minutes = 1000 * 60;
    var hours = minutes * 60;
    var days = hours * 24;
    var years = days * 365;
    return (Math.round(((Date.now() - Date.parse(hireDate)) / years) * 10)) / 10;
}
