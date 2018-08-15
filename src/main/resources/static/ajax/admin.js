
$("#addcode").click(function () {
    $(this).attr("disabled","disabled");

    var data = {
        "type": $("#type").val(),
        "name":$("#name").val(),
        "description":$("#description").val()
    }

    $.ajax({

        url:"/codes/add/",

        type:"POST",

        contentType:"application/json;charset=utf-8",

        data:JSON.stringify(data),

        success: function (code) {
            console.log(code);
            window.location.href = "/admin/code/list";
        },
        error: function (e) {
            alert("Failed");
            console.log(e);
        }
    })

    var codeTable = "";


});