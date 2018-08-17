$(document).ready(function () {

    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#schoolTable').DataTable({
            select: {
                style: 'single'
            },
            language: {
                processing: "<i class='fa fa-spinner fa-spin'/>"
            },
            dom: 'frtpl',
            lengthMenu: [[5, 10, -1], [5, 10, "All"]],
            pagingType: "simple_numbers",
            searching: false,
            rowId: "id",
            processing: true,
            serverSide: true,
            responsive: true,
            ordering: false,
            columns: [
                { data: "code" },
                { data: "name"},
                { data: "emailAddress" },
                { data: "phoneNumber"},
                { data: "website" },
                { data: "operations" }
            ],
            ajax: {
                url: "/admin/v1/school",
                type: "GET"
            },
            "columnDefs" : [{
            	 "targets" : 5,
                "data" : "operations",
                "render" : function (data, type, row) {
                    var linkView = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/school/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                        '</div>';
                    var linkEdit = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/admin/school/'+row.id +'/edit/"><i class="fa fa-pencil"></i></a>'+
                        '</div>';
                    var linkDelete = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                        '</div>';
                    return linkView + " | " + linkEdit + " | " + linkDelete;
                }
               
            }]
        });

});

// DATATABLE IMPLEMENTATION ENDS HERE

// Delete Method starts here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a school?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("Successfully deleted school");
                    window.location = id + "/delete/";
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
