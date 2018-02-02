function upload() {
    var employeePhoto = $("#photoInput").prop('files')[0];
    if (employeePhoto === undefined) {
        alert("No file");
    } else {
        uploadFile(employeePhoto);
    }
}

function deletePhoto() {
    var idEmployee = $('#idEmployeePhoto').val();
    $.post("EmployeePhotoDelete", {idEmployee: idEmployee})
        .done(function (data) {
            var photo = $('#employee_photo');
            photo.fadeOut(800, function () {
                photo.attr("src", "EmployeePhoto?id=" + $('#idEmployeePhoto').val() + "&" + new Date().getTime());
                photo.fadeIn().delay(2000);
            });
            $('#photoModal').modal('hide');
        })
        .fail(function (data) {
            alert("fail");
        });
}

function uploadFile(employeePhoto) {
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    fd.append("idEmployee", $('#idEmployeePhoto').val());
    fd.append("uploadingPhoto", employeePhoto);
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "EmployeePhotoUpload", true);
    xhr.send(fd);
}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        document.getElementById('progressNumber').innerHTML = "Uploaded: " + percentComplete.toString() + '%';
    }
    else {
        document.getElementById('progressNumber').innerHTML = 'Unable to compute';
    }
}

function uploadComplete(evt) {
    var photo = $('#employee_photo');
    photo.fadeOut(800, function () {
        photo.attr("src", "EmployeePhoto?id=" + $('#idEmployeePhoto').val() + "&" + new Date().getTime());
        photo.fadeIn().delay(2000);
    });
    document.getElementById('progressNumber').innerHTML = "";
    $('#photoUploadForm')[0].reset();
    $('#photoModal').modal('hide');
}

function uploadFailed(evt) {
    alert("There was an error attempting to upload the file.");
}

function uploadCanceled(evt) {
    alert("The upload has been canceled by the user or the browser dropped the connection.");
}