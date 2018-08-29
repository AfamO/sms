$(document).ready(function () {
	codetype= $("#codeTable").attr("data-type");
	var table = $("#codeTable").DataTable({
		"language" : {
			"processing" : "<i class='fa fa-spinner fa-spin'/>"
		},
		dom : 'frtpl',
		"lengthMenu" : [ [ 5, 10, -1 ], [ 5, 10, "All" ] ],
		"pagingType" : "simple_numbers",
		"searching" : false,
		"processing" : true,
		"rowId" : "id",
		"serverSide" : true,
		"ordering" : false,
		"ajax" : {
			url : "/admin/v1/code",
			"type" : "GET",
			"data" : function(d) {
				d['type'] = codetype;
			}
		},
		"columns" :   [
			{"data" : "name"},
			{"data":"description"},
			{"data":null}
		  ],
		"columnDefs" : [{
       	 "targets" : 2,
       	"render" : function (data, type, row) {
       	   lbl = '';
       	   if (type === 'display' ) {
       		
       		 kk='<a href="/admin/code/'+row.id+'" class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>  </a>'
       		 return kk;
			}
			return data;
          }
          
       }]
});
});
