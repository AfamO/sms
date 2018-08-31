$(document).ready(function () {

    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#studentTable').DataTable({
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
            { data: "status" },
            { data: "admissionNum" },
            { data: "firstName" },
            { data: "MiddleName"},
            { data: "lastName"},
            { data: "gender" },
            { data: "class"},
            { data: "department" },
            { data: null }
        ],
        "columnDefs" : [{
            "targets" : 0,
            "data" : "status",
            "render" : function (data, type, row) {
                lbl = '';
                if (type === 'display' ) {
                    switch (data) {
                        case 'ENABLED':
                            lbl = 'label-primary';
                            break;
                        case 'DISABLED':
                            lbl = 'label-danger';
                            break;
                        case 'LOCKED':
                            lbl = 'label-warning';
                            break;
                        default:
                            lbl = 'label-info';
                    }
                    kk =  '<span class="label '+lbl+'">'+data+'</span>';
                    return kk;
                }
                return data;
            }

        },
            {
                "targets" : 8,
                "data" : "operations",
                "render" : function (data, type, row) {
                    lbl = '';
                    if (type === 'display' ) {

                        kk='<a href="/school/student/'+row.id+'/edit" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>'
                        kk += '<a href="/school/student/'+row.id+'/users" class="btn btn-white btn-sm"><i class="fa fa-user"></i>  </a>'
                        kk += '<a href="/school/student/'+row.id+'/roles" class="btn btn-white btn-sm"><i class="fa fa-user-secret"></i>  </a>'
                        return kk;
                    }
                    return data;
                }

            }],
        ajax: {
            url: "/school/v1/student",
            type: "GET"
        }
    });

});

