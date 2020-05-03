function uploadFile() {
    let files = $('#uploadFile').prop('files');
    let data = new FormData();
    data.append('file', files[0]);

    $.ajax({
        type: 'POST',
        url: "file/upload",
        data: data,
        cache: false,
        processData: false,
        contentType: false,
        success: function (ret) {
            alert(ret);
            $('#uploadFile').data("fileId", ret);
        }
    });
}

function uploadText() {
    let fileId = $('#uploadFile').data("fileId") == undefined ? null : $('#uploadFile').data("fileId");
    let textInfo = $('#uploadText').val();
    let textVO = {fileId: fileId, textInfo: textInfo}

    $.ajax({
        type: 'POST',
        url: "upload/text",
        data: JSON.stringify(textVO),
        dataType: 'application/json',
        contentType: 'application/json',
        success: function (ret) {
            let ad = JSON.stringify(ret);
            alert(ad);
        }
    });
}