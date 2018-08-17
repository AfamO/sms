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
               var linkView = '<div class="btn-group">'+
                   '<a type="button" class="btn btn-warning " href="/admin/code/type/'+row.type +'"><i class="fa fa-eye"></i></a>'+
                   '</div>';
               return linkView ;
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
