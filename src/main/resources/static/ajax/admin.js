
$("#addcode").click(function () {
    $(this).attr("disabled", "disabled");

    var data = {
        "type": $("#type").val(),
        "name": $("#name").val(),
        "description": $("#description").val()
    };

    $.ajax({
        url: "http://localhost:8080/codes/add",

        type: 'POST',

        contentType: 'application/json;charset=utf-8',

        data: JSON.stringify(data),

        success: function (data) {
            alert("Success");
        },

        error: function () {
            window.location = "/admin/codes/list";
        }

    });

});


$(document).ready(function () {

    alert("I got here");
    var table = $('#table').DataTable({
        select: true,
        responsive: true,
        "draw":1,

        language: {
            searchPlaceholder: "Search records"
        },
        "lengthMenu" : [ [ 5, 10, -1 ], [ 5, 10, "All" ] ],
        "pagingType": "simple_numbers",
        "searching": true,
        "processing": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "http//localhost:8080/codes/alltype",
        "aoColumns": [
            {"mData": "name"},
            {"mData":"description"}
        ],
        "columnDefs" : [{
            "data" : "operations",

            "mRender" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick=view('+row.id+')><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick=edit('+row.id+')><i class="fa fa-pencil"></i></a>'+
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

function view(id){
    alert("I am code of "+id);
}

function edit(id){
    alert(("I am code View of " + id);
}

//Delete Method start here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a code ?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("successfully delete user");
                    alert("You have deleted the code of id"+id);
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
        },
    });

};
