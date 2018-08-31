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
                { data: "jobPosition" },
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
                    kk='<a href="/school/staff/'+row.id+'/edit" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>'
                    kk += '<a href="/school/staff/'+row.id+'/users" class="btn btn-white btn-sm"><i class="fa fa-user"></i>  </a>'
                    kk += '<a href="/school/staff/'+row.id+'/qualification" class="btn btn-white btn-sm"><i class="fa fa-user-secret"></i>  </a>'
                    return kk;
                }
               
            }]
        });

});

