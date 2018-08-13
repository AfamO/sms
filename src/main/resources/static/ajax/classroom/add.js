$("document").ready(function(){

    $("#submit").click(function() {
        var data = {
            "name": $("#name").val(),
            "description":$("#descrp").val()
            // ,
            // "staff":[
            //     {"id":3},
            //     {"id":3}
            // ]

        };



        data.classConfig = {
            "id": parseInt($("#class").val())
        };




        $.ajax({
            url: "http://localhost:8080/classroom/add",
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