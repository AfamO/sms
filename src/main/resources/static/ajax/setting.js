$("#create").click(function(){

    var data = {
        "name": $("#sname").val(),
        "type": $("#type").val(),
        "description": $("#descrp").val(),
        "value": $("#setVal").val(),
        "enabled": $("#enable").val()
    }

    console.log("data: ", data);

    $.ajax({

        url: "http://localhost:8080/setting/create",
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(res){
            alert("success");
            console.log(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("error");
            console.log(xhr.status);
            console.log(thrownError);
        }
    })
});

$(document).ready(function(){



    var table = $('#settingsTable').DataTable({
        select: true,
        responsive: true,
        "draw":1,
        "pagingType": "simple_numbers",
        "bDestroy": true,
        "searching": true,
        "processing": true,
        // "serverSide": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "/setting/all",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "name"},
            {"mData": "type"},
            {"mData": "description"},
            {"mData": "value"},
            {"mData": "enabled"},
            {"mData": "operations"}
        ],
        "columnDefs" : [{
            "data" : "operations",
            "render" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/admin/setting/view/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/admin/setting/edit/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
                    '</div>';
                var linkDelete = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                    '</div>';
                return linkView + " | " + linkEdit + " | " + linkDelete;
            },
            "targets" : 5
        }],

    });
});



//Delete Method starts here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a school?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    // console.log("successfully delete user");
                    // window.location = "/class/delete/"+id;
                    $.ajax({
                        url:"http://localhost:8080/setting/delete/"+id,
                        method:"POST",
                        contentType:"application/json; charset=utf-8",
                        success:function(res){

                            window.location.reload(true);
                        },
                        error:function(res){
                            console.log(res+ "error");
                        }
                    })
                }
            },
            cancel: {
                label: 'No',
                className: 'btn-success',
                callback: function (result) {
                    result = false;
                }
            }
        },
    });

};