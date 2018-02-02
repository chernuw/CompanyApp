function buildEmployeesTable(page) {
    var request = new XMLHttpRequest();
    var amountOnPage = document.getElementById("amountEmployeesOnPage").value;
    var amountEmpl = document.getElementById("amountEmployees").innerText;
    var orderField = document.getElementById("orderByOnPage").value;

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if ($("#emplTable")) {
                $("#emplTable").remove();
            }
            var table$ = $('<table class="table table-responsive-sm" id="emplTable"/>');
            $("#tbl").append(table$);
            var list = JSON.parse(this.responseText);

            var columns = [' ', 'Contact Info', 'Department', 'Position', 'In Company'];
            var headerTr$ = $('<tr/>');
            columns.forEach(function (item) {
                headerTr$.append($('<th/>').html(item));
            });
            table$.append(headerTr$);
            //filling table data
            for (var i = 0; i < list.length; i++) {
                var row$ = $('<tr/>');
                row$.append($('<td/>').html('<img src="EmployeePhoto?id=' + list[i].employeeId + '" name="emplPhoto" class="rounded"/>'));
                row$.append($('<td/>').html('<a href="EmployeeProfile?id=' + list[i].employeeId + '">'
                    + list[i].firstName + ' ' + list[i].lastName + '</a><br/>'
                    + '&#128222;' + list[i].phone + '<br/>'
                    + '&#128231;'+ list[i].email));
                row$.append($('<td/>').html(list[i].department));
                row$.append($('<td/>').html(list[i].position));
                row$.append($('<td/>').html(getWorkExperience(list[i].hireDate) + " years"));
                $(table$).append(row$);
            }

            //Pagination select
            if ($("#pagi")) {
                $("#pagi").remove();
            }
            var pagi$ = $('<ul class="pagination justify-content-center" id="pagi"/>');
            $("#pagination").append(pagi$);
            var amountOfPages = (amountEmpl % amountOnPage == 0) ? (amountEmpl / amountOnPage) : (amountEmpl / amountOnPage + 1);
            for (var j = 1; j <= amountOfPages; j++) {
                if (j === page) {
                    pagi$.append($("<li onclick='return false;' class='page-item active'/>")
                        .html("<span class='page-link'>" + j + "</span>"));
                } else {
                    pagi$.append($('<li class="page-item" onclick="buildEmployeesTable(' + j + ')">/')
                        .html('<span class="page-link">' + j + '</span>'));
                }
            }
        }
    };
    request.open("POST", "EmployeeListData?page=" + page
        + "&amountOnPage=" + amountOnPage
        + "&orderField=" + orderField, true);
    request.send();
}

function getWorkExperience(hireDate) {
    var minutes = 1000 * 60;
    var hours = minutes * 60;
    var days = hours * 24;
    var years = days * 365;
    return (Math.round(((Date.now() - Date.parse(hireDate)) / years) * 10)) / 10;
}
