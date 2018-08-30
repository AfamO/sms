$(document).ready(function () {

    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#schoolTable').DataTable({
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
                { data: "code" },
                { data: "name"},
                { data: "emailAddress" },
                { data: "phoneNumber"},
                { data: "website" },
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
             	 "targets" : 6,
                 "data" : "operations",
                 "render" : function (data, type, row) {
              	   lbl = '';
              	   if (type === 'display' ) {
              		  
              		 kk='<a href="/admin/school/'+row.id+'/edit" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>'
              		 kk += '<a href="/admin/school/'+row.id+'/users" class="btn btn-white btn-sm"><i class="fa fa-user"></i>  </a>'
              		 kk += '<a href="/admin/school/'+row.id+'/roles" class="btn btn-white btn-sm"><i class="fa fa-user-secret"></i>  </a>'
              		 return kk;
     				}
     				return data;
                 }
                
             }],
            ajax: {
                url: "/admin/v1/school",
                type: "GET"
            }
        });

});

