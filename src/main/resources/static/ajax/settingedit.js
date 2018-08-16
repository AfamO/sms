$("document").ready(function(){

    var page = decodeURIComponent(window.location).split('/')

    var id = parseInt(page[page.length-1]);

    $.ajax({

        url:"http://localhost:8080/setting/get/"+id,
        method:"GET",
        contentType:"application/json; charset=utf-8",
        success:function(res){
            // console.log(res);
            // $("#name").field(res.name);
            fillHtml(res);
        },
        error:function(){
            console.log("error");
        }
    });


    $("#edit").click(function(){

        var data = {
            "id":id,
            "name": $("#sname").val(),
            "type": $("#type").val(),
            "description": $("#descrp").val(),
            "value": $("#setVal").val(),
            "enabled": $("#enable").val()
        }

        console.log(data);
        $.ajax(
            {
                url: "http://localhost:8080/setting/update",
                method: "POST",
                contentType: "application/json",
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
    })
});

function fillHtml(res){
    $("#sname").append(res.name);
    $("#descrp").append(res.description);
    $("#type").append(res.type);

    $("#setVal").append(res.value);
    $("#enable").append(res.enabled);
;
}