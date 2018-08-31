$('#createStudent').click(function () {
    $(this).attr("disabled", "disabled");
   // alert($('#name'));
    //Club implement


    var data = {

        "admissionNum" : $('#admissionNum').val(),
        "firstName" : $('#firstName').val(),
        "middleName" : $('#middleName').val(),
        "lastName" : $('#lastName').val(),
        "religion" : $('#religion').val(),
        "gender" : $("input[name=\'gender\']:checked").val(),
        "phoneNum" : $('#phoneNum').val(),
        "email" : $('#email').val(),
        "sportHouse" : $('#sportHouse').val(),
        "department" : $('#department').val(),

        "addressDTO": {
            "address" : $('#address').val(),
            "localGovt": $('#localGovt').val(),
            "stateOfOrigin" :$('#stateOfOrigin').val(),
            "country" : $('#country').val(),
            "state" : $('#state').val(),
            "nationality" : $('#nationality').val(),
        },



        "parentDTOS":[{
            "firstName": $('#firstName').val(),
            "middleName": $('#middleName').val(),
            "lastName": $('#lastName').val(),
            "Occupation": $('#Occupation').val(),
            "phoneNumber": $('#phoneNumber').val(),
            "address": $('#address').val(),
            "email": $('#email').val(),

        }],

        "clubDTOS":[{
            "name": $('#name').val()
        }
        ]

    };



    console.log("DATA:",data);

    $.ajax(
        {
            url: "http://localhost:8080/student/add",
            method: "POST",
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

// Club implement
$(document).ready(function () {
    $("#name").select2({
        width: 'resolve'
    })

})

//DataTable Implementation
$(document).ready(function () {

    var table = $('#studentTable').DataTable({
        select: true,
        responsive: true,
        // bInfo:true,
        "paging;Type": "simple_numbers",
        "searching": true,
        // "bDestroy": true,
        "serverSide": false,
        "paging": true,
        "ordering": false,
        "dom": 'blfrtip',
        "sAjaxSource": "/student/list",
        "sAjaxDataProp": "",
        "aoColumns": [
            { "mData": "admissionNum"},
            { "mData": "firstName"},
            { "mData": "middleName"},
            { "mData": "lastName"},
            { "mData": "entryClass"},
            { "mData": "department"},
            { "mData": "gender"},
            { "mData": "operations"},
        ],
        "columnDefs" : [{
            "data" : "operations",
            "render" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/student/view/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " href="/student/edit/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
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

//Delete Method
function deleteDialog(id){
    bootbox.dialog({
        message: "You are about to delete a Student ?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("successfully deleted user");
                    window.location = "/student/delete/" +id  ;
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


