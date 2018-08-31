$(document).ready(function () {
	var sid = $('#roleTable').attr("data-id");
    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#roleTable').DataTable({
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
            	 { data: "type"},
                { data: "name"},
                { data: "email" },
                { data: null }
            ],
            ajax: {
                url: "/admin/v1/school/" + sid + '/roles',
                type: "GET"
            },
            "columnDefs" : [{
            	 "targets" : 3,
                "data" : "operations",
                "render" : function (data, type, row) {
                	 lbl = '';
                 	   if (type === 'display' ) {
                 		
                 		  kk='<a href="/admin/school/'+sid+'/role/'+row.id+'" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>';
                    	 return kk;
          			}
          			return data;
                }
               
            }]
        });

});

