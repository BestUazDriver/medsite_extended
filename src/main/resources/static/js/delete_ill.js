$(document).ready(function () {

    $("#button").click(function (event) {
        // Stop default form Submit.
        event.preventDefault();

        // Call Ajax Submit.

        ajaxSubmitForm1();

    });

});

function ajaxSubmitForm1() {

    // Get form
    var form = $('#delete_ill')[0];

    var data = new FormData(form);

    $("#button").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/add_ill/delete",
        data: data,

        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function (data, textStatus, jqXHR) {

            $("#result1").html(data);
            console.log("SUCCESS : ", data);
            $("#button").prop("disabled", false);
            $('#delete_ill')[0].reset();
        },
        error: function (jqXHR, textStatus, errorThrown) {

            $("#result1").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#button").prop("disabled", false);

        }
    });

}