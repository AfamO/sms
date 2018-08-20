$(document).ready(function(){

    $("#createPermission").click(function(e) {

        e.preventDefault();

        /*
        *to use the function save
        * if our object has many to many mapping
        * the html would be <input type="text" name="prop" aobj="child" count=""/>
         */

        save({
            url:"http://localhost:8080/permission/add",
            form: "#Permission",
            fn:function(res){}
        });
    });
});

function save(obj){
    /*
    *obj is an object which contains
    * url,form_id,obj,callback
     */

    var data = {};

    var url = obj["url"] || "";
    var form = obj["form"];
    var dataProps = obj["props"] || null;
    var callback = obj["fn"] || "";
    var count = {}

    if(dataProps){

        for(var i in dataProps){
            if($.isArray(dataProps[i])){
                count[i] = [];
            }
            data[i] = dataProps[i];
        }
    }


    $(form).find("[name]").each(function(index,element){

        if($(element).attr("obj")){
            var op = $(element).attr("obj");

            data[op][$(element).attr("name")] = $(element).val();
            // console.log($(element).val());
        }

        else if($(element).attr("aobj")){

            var aop = $(element).attr("aobj");

            var aCount = $(element).attr("count");

            console.log(count[aop]);
            if(!count[aop].includes(aCount)){
                count[aop].push(aCount);

                data[aop].push({});
            }
            var index = parseInt(count[aop][aCount]);
            var props = $(element).attr("name");
            var values = $(element).val()
            data[aop][index][props] = values;
        }
        else
        {
            var prop = $(element).attr("name");
            var value = $(element).val();
            data[prop] = value;
        }
        // console.log(index,$(element).attr("obj"));
    });
    console.log(data);

    $.ajax({

        url: url,
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(res){
            alert("success");
            callback(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("error");
            console.log(xhr.status);
            console.log(thrownError);
        }
    })

}
// $('#createPermission').click(function () {
//     $(this).attr("disabled", "disabled");
//
//
//
//     var data = {
//
//         "name" : $('#name').val(),
//         "category" : $('#category').val(),
//         "description" : $('#description').val(),
//     };
//
//
//     console.log("DATA:",data);
//
//     $.ajax(
//         {
//             url: "http://localhost:8080/permission/add",
//             method: "POST",
//             contentType: "application/json; charset=utf-8",
//             data: JSON.stringify(data),
//             success: function (res) {
//                 alert("success");
//                 console.log(res);
//             },
//             error: function (xhr, ajaxOptions, thrownError) {
//                 alert("");
//                 console.log(xhr.status);
//                 console.log(thrownError);
//             }
//         });
// });
//
// $(document).ready(function(){
//
//     // DATATABLE IMPLEMENTATION STARTS HERE
//     var $table = $('#permisionsTable').DataTable({
//         language: {
//             processing: "<i class='fa fa-spinner fa-spin'/>"
//         },
//         dom: 'frtpl',
//         lengthMenu: [[5, 10, -1], [5, 10, "All"]],
//         pagingType: "simple_numbers",
//         searching: false,
//         rowId: "id",
//         processing: true,
//         serverSide: true,
//         responsive: true,
//         ordering: false,
//         columns: [
//             { data: "name" },
//             { data: "category" },
//             { data: "description"},
//             { data: "operations" }
//         ],
//         ajax: {
//             url: "/permission/list",
//             type: "GET"
//         },
//         "columnDefs" : [{
//             "targets" : 3,
//             "data" : "operations",
//             "render" : function (data, type, row) {
//                 var linkView = '<div class="btn-group">'+
//                     '<a type="button" class="btn btn-warning " href="/admin/permission/view'+row.id +'"><i class="fa fa-eye"></i></a>'+
//                     '</div>';
//                 var linkEdit = '<div class="btn-group">'+
//                     '<a type="button" class="btn btn-warning " href="/admin/permission/edit/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
//                     '</div>';
//                 var linkDelete = '<div class="btn-group">'+
//                     '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
//                     '</div>';
//                 return linkView + " | " + linkEdit + " | " + linkDelete;
//             }
//
//         }]
//     });
//
// });
//
//
//
// //Delete Method starts here
// function deleteDialog(id) {
//     bootbox.dialog({
//         message: "You are about to delete a permission?",
//         buttons: {
//             danger: {
//                 label: 'Delete',
//                 className: 'btn-danger',
//                 callback: function (result) {
//                     // console.log("successfully delete user");
//                     // window.location = "/class/delete/"+id;
//                     $.ajax({
//                         url:"http://localhost:8080/permission/delete/"+id,
//                         method:"POST",
//                         contentType:"application/json; charset=utf-8",
//                         success:function(res){
//
//                             window.location.reload(true);
//                         },
//                         error:function(res){
//                             console.log(res+ "error");
//                         }
//                     })
//                 }
//             },
//             cancel: {
//                 label: 'No',
//                 className: 'btn-success',
//                 callback: function (result) {
//                     result = false;
//                 }
//             }
//         },
//     });
//
// };