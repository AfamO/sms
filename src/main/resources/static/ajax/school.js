
$('#createOrUpdateSchool').click(function () {

    $(this).attr("disabled", "disabled");

    var data = {

        "schoolName" :  $('#schoolName').val(),
        "phoneNumber" :  $('#phoneNumber').val(),
        "motto" :  $('#motto').val(),
        "emailAddress" :  $('#emailAddress').val(),
        "history" :  $('#history').val(),
        "website" :  $('#website').val(),

        "addressDTO": {
            "address" : $('#address').val(),
            "city" : $('#city').val(),
            "localGovt" : $('#localGovt').val(),
            "state" : $('#state').val(),
             "country" : $('#country').val(),
        }

    };

    console.log("DATA: ",data);



    $.ajax(
        {
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

$(document).ready(function(){

    //DATATABLE IMPLEMENTATION STARTS HERE

    var $table = $('#schoolTable').DataTable({
        select: true,
        responsive: true,
        "draw":1,
        "pagingType": "simple_numbers",
        "bDestroy": true,
        "searching": true,
        "processing": true,
        // "serverSide": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "/school/list",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "code"},
            {"mData": "schoolName"},
            {"mData": "address.address"},
            {"mData": "emailAddress"},
            {"mData": "phoneNumber"},
            {"mData": "website"},
            {"mData": "motto"},
            {"mData": "operations"}
        ],
        "columnDefs" : [{
            "data" : "operations",
            "render" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/school/info/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/school/save/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
                    '</div>';
                var linkDelete = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                    '</div>';
                return linkView + " | " + linkEdit + " | " + linkDelete;
            },
            "targets" : 7
        }],

    });
});

// DATATABLE IMPLEMENTATION ENDS HERE

//Delete Method starts here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a school?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("Successfully deleted school");
                    window.location = id+"/delete/";
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

// var country = document.getElementById("country");
// //Create array of options to be added
// var array = ["Select Country","Algeria","Benin","Botswana","Audi"];
//
// //Create and append select list
// var selectList = document.createElement("select");
// selectList.setAttribute("id", "mySelect");
// country.appendChild(selectList);
//
// //Create and append the options
// for (var i = 0; i < array.length; i++) {
//     var option = document.createElement("option");
//     option.setAttribute("value", array[i]);
//     option.text = array[i];
//     selectList.appendChild(option);
// }