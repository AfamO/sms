$("document").ready(function () {


    $("#submit").click(function () {

        var data = {

            "id":parseInt($("#class_id").val()),
            "name":$("#name").val(),
            "numberOfRoom":$("#nor").val()
        };
        console.log(data);
        $.ajax(
            {
                url: "http://localhost:8080/class/delete",
                method: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                success: function (res) {
                    alert("success");
                    console.log(res);
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert("error");
                    console.log(xhr.status);
                    console.log(thrownError);
                }
            });
    });
})