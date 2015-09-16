/**
 *
 */
function fileupload(postHandler) {
    $('input[type="file"]').fileupload({
        dataType : 'json',
        add : function(e, data) {
            data.context = $('<p/>').text('Uploading...').appendTo(document.body);
            data.submit();
        },
        done : function(e, data) {
            data.context.text('Upload finished.');
            var self = $(e.target);
            postHandler(e, data);
        },
        progressall : function(e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css('width', progress + '%').text(progress + '%');
        }
    });
}
