$('#createPermission').click(function () {
    $(this).attr("disabled", "disabled");



    var data = {

        "name" : $('#name').val(),
        "category" : $('#category').val(),
        "description" : $('#description').val(),
    };


    console.log("DATA:",data);

    $.ajax(
        {
            url: "http://localhost:8080/permission/add",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (res) {
                alert("success");
                console.log(res);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("");
                console.log(xhr.status);
                console.log(thrownError);
            }
        });
});

$(document).ready(function(){



    var table = $('#permisionsTable').DataTable({
        select: true,
        responsive: true,
        lengthMenu: [[5, 10, -1], [5, 10, "All"]],
        "draw":1,
        "pagingType": "simple_numbers",
        "searching": true,
        "processing": true,
        "serverSide": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "/permission/list",
        "aoColumns": [
            {"mData": "name"},
            {"mData": "description"},
            {"mData": "operations"}
        ],
        "columnDefs" : [{
            "data" : "operations",
            "render" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/admin/permission/view/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/admin/permission/edit/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
                    '</div>';
                var linkDelete = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                    '</div>';
                return linkView + " | " + linkEdit + " | " + linkDelete;
            },
            "targets" : 3
        }],

    });
});



//Delete Method starts here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a permission?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    // console.log("successfully delete user");
                    // window.location = "/class/delete/"+id;
                    $.ajax({
                        url:"http://localhost:8080/permission/delete/"+id,
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