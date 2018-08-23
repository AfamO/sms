$(document).ready(function () {

    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#staffTab').DataTable({
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
                { data: "staffId" },
                { data: "firstName"},
                { data: "lastName" },
                { data: "phoneNumber"},
                { data: "emailAddress" },
                { data: "operations" }
            ],
            ajax: {
                url: "/school/v1/staff",
                type: "GET"
            },
            "columnDefs" : [{
            	 "targets" : 5,
                "data" : "operations",
                "render" : function (data, type, row) {
                    var linkView = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/school/staff/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                        '</div>';
                    var linkEdit = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/school/staff/'+row.id +'/edit/"><i class="fa fa-pencil"></i></a>'+
                        '</div>';
                    var linkDelete = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                        '</div>';
                    return linkView + " | " + linkEdit + " | " + linkDelete;
                }
               
            }]
        });

});

