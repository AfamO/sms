$('#createOrUpdateSchool').click(function () {
    $(this).attr("disabled", "disabled");
    var data = {
        "schoolName": $('#schoolName').val(),
        "phoneNumber": $('#phoneNumber').val(),
        "motto": $('#motto').val(),
        "emailAddress": $('#emailAddress').val(),
        "history": $('#history').val(),
        "website": $('#website').val(),
        "addressDTO": {
            "address": $('#address').val(),
            "city": $('#city').val(),
            "localGovt": $('#localGovt').val(),
            "state": $('#state').val(),
            "country": $('#country').val(),
        }
    };
    console.log("DATA: ", data);

    $.ajax({
        url: "http://localhost:8080/school/save",
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (res) {
            alert("success");
            console.log(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("error");
            console.log(xhr.status);
            console.log(thrownError);
        }
    });
});

$(document).ready(function () {

    // DATATABLE IMPLEMENTATION STARTS HERE
    var $table = $('#schoolTable').DataTable({
            select: {
                style: 'single'
            },
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
                { data: "code" },
                { data: "name"},
                { data: "address.address",
					"defaultContent" : "NA" },
                { data: "emailAddress" },
                { data: "phoneNumber"},
                { data: "website" },
                { data: "motto" },
                { data: "operations" }
            ],
            ajax: {
                url: "/admin/v1/school",
                type: "GET"
            },
            "columnDefs" : [{
            	 "targets" : 7,
                "data" : "operations",
                "render" : function (data, type, row) {
                    var linkView = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/school/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                        '</div>';
                    var linkEdit = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " href="/school/save/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
                        '</div>';
                    var linkDelete = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                        '</div>';
                    return linkView + " | " + linkEdit + " | " + linkDelete;
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
