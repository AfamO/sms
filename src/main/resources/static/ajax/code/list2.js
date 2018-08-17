$(document).ready(function () {

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
				var x = $("[name='codetype']").val();
				d['type'] = x;
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
             var linkView = '<div class="btn-group">'+
                 '<a type="button" class="btn btn-warning " href="/admin/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                 '</div>';
             var linkEdit = '<div class="btn-group">'+
                 '<a type="button" class="btn btn-warning " href="/admin/code/'+row.id +'/edit/"><i class="fa fa-pencil"></i></a>'+
                 '</div>';
             var linkDelete = '<div class="btn-group">'+
                 '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                 '</div>';
             return linkView + " | " + linkEdit + " | " + linkDelete;
         }
          
       }]
});
});
