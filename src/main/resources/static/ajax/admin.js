
$("#addcode").click(function () {
    $(this).attr("disabled","disabled");

    var data = {
        "type": $("#type").val(),
        "name":$("#name").val(),
        "description":$("#description").val()
    };

    $.ajax({
        url:"http://localhost:8080/admin/v1/codes/add",

        type:'POST',

        contentType:'application/json;charset=utf-8',

        data: JSON.stringify(data),

        success:function (data) {
            alert("Success");
        },

        error: function () {
            alert("failed")
        }
    })



});