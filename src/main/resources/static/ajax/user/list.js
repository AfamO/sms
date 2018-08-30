$(document).ready(function () {
	var sid = $('#userTable').attr("data-id");
    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#userTable').DataTable({
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
                { data: "loginId" },
                { data: "type"},
                { data: "role.name" },
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
           	 "targets" : 3,
               "data" : "role",
               "render" : function (data, type, row) {
            	   if (type === 'display' && row.role != null) {
   					kk=  "<a  data-toggle='tooltip' title='Open' data-placement='right' href='/admin/school/"+sid+"/role/"+ row.role.id+ "'>" + data +"</a>";
   					return kk;
   				}
   				return data;
               }
              
           },
           {
             	 "targets" : 4,
                 "data" : "operations",
                 "render" : function (data, type, row) {
              	   lbl = '';
              	   if (type === 'display' ) {
              		
              		 kk='<a href="/admin/school/'+sid+'/user/'+row.id+'" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>'
//              		 kk += '<a href="/admin/school/'+row.id+'/users" class="btn btn-white btn-sm"><i class="fa fa-users"></i>  </a>'
              		 return kk;
     				}
     				return data;
                 }
                
             }],
            ajax: {
                url: "/admin/v1/school/" + sid + '/users',
                type: "GET"
            }
        });

});

