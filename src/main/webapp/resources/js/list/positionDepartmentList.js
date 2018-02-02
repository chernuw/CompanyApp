//for select position and department in profile modal
function addPositionsDepartmentsInModal() {
    getDepartments($('#department'));
    getPositions($('#position'));
}

function getDepartments(select) {
    $.post("DepartmentList")
        .done(function (data) {
            JSON.parse(data).forEach(function (value) {
                select.append($('<option/>').text(value));
            });
        })
        .fail(function () {
            alert("fail");
        });
}

function getPositions(select) {
    $.post("PositionList")
        .done(function (data) {
            JSON.parse(data).forEach(function (value) {
                select.append($('<option/>').text(value));
            });
        })
        .fail(function () {
            alert("fail");
        });
}