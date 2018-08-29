$(function(){
   $("#submit").click(function() {



       var data = {

           "name": $("#name").val(),
           "sClass":{
               "id":parseInt($("#class_id").val()),
               "name":$("#class_name").val(),
               "numberOfRoom":$("#class_nor").val(),
               "description":$("#class_descrp").val()
           },
           "classRoomTeachers":[
               {"role":"Assistance","refStaff_id":parseInt($("#asstch_id").val())},
               {"role":"Teacher","refStaff_id":parseInt($("#tch_id").val())}
           ]

       };

       console.log(data);

       $.ajax({
           url: "http://localhost:8000/class/roomconfig/create",
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