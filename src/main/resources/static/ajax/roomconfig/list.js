$("document").ready(function(){



    var table = $('#classTable').DataTable({
        select: true,
        responsive: true,
        // "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
        "pagingType": "simple_numbers",
        "searching": true,
        "processing": true,
        "serverSide": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "/roomconfig/all",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "name"},
            {"mData": "description"},
            {"mData": "operations"}
        ],
        "columnDefs" : [{
            "data" : "operations",
            "mRender" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/roomconfig/view/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/roomconfig/'+row.id +'/edit"><i class="fa fa-pencil"></i></a>'+
                    '</div>';
                var linkDelete = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                    '</div>';

                return linkView + " | " + linkEdit + " | " + linkDelete;
            },
            "targets" : 2
        }]

    });



});

//Delete Method start here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a class?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("successfully delete user");
                    window.location = "/roomconfig/delete/"+id;
                }
            },
            cancel: {
                label: 'No',
                className: 'btn-success',
                callback: function (result) {
                    // $('.bootbox').modal('hide');
                    result = false;
                }
            }
        }
    });

}
//window["deleteDialog"] = deleteDialog;

