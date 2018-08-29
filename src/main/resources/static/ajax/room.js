$(function(){

  var data = {

        "name": "goldy",
        "sClass":{
            "id":1,
            "name":"jss3",
            "numberOfRoom":"2",
            "description":"junior 3"
        }

    };
  data.classRoomTeachers =[{"role":"teacher"},{"role":"assistance"}]
    console.log(data);

    $.ajax({
        url:"http://localhost:8000/class/room/create",
        method:"POST",
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