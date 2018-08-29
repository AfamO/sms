$("document").ready(function(){

    $("#submit").click(function () {

        var data ={
            "name":$("#name").val(),
             "description":$("#descrp").val(),
            "sessionStart":$("#ss").val(),
            "sessionEnd":$("#se").val()
        };

        console.log(data);
        $.ajax(
            {
                url: "http://localhost:8080/classconfig/create",
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
});