$(document).ready(function () {

	var table = $("#table").DataTable({
		select : {
			style : 'single'
		},
		"language" : {
			"processing" : "<i class='fa fa-spinner fa-spin'/>"
		},
		dom : 'frtpl',
		"lengthMenu" : [ [ 5, 10, -1 ], [ 5, 10, "All" ] ],
		"pagingType" : "simple_numbers",
		"searching" : false,
		"processing" : true,
		"rowId" : "type",
		"serverSide" : true,
		"ordering" : false,
		"ajax" : {
			url : "/admin/v1/code/type",
			"type" : "GET",
		},
		"columns" :   [
			{"data" : "type"},{"data":null}
		  ],
		"columnDefs" : [{
       	 "targets" : 1,
           "render" : function (data, type, row) {
               kk='<a href="/admin/code/type/' + row.type + '" class="btn btn-white btn-sm"><i class="fa fa-eye"></i>  </a>'
               return kk ;
           }
          
       }]
});
});

