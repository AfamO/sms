var counter = 0;
var refereesCount = 0;
$('#addStaff').click(function () {
    $(this).attr("disabled", "disabled");



   // var data = $("#staff").serialize();
    //console.log(data);
    // var data = {};
    // var dat = {
    //     staffDto : {
    //         "":"",
    //         addressDto: {
    //             "" : ""
    //         }
    //     },
    //
    //     refereeDTOS:{
    //
    //         addressDTO: {
    //           "name" : $('#refAddress').val()
    //         }
    //     }
    // }
    // data.staffId = $('#staffId').val();
    // data.title = $('#title').val();
    // data.firstName = $('#firstName').val();
    // data.middleName = $('#middleName').val();
    // data.lastName = $('#lastName').val();
    // data.religion = $('#religion').val();
    // data.gender = $('.gender:checked').val();
    // data.maritalStatus = $('#maritalStatus').val();
    // data.nationality = $('#nationality').val();
    // data.stateOfOrigin = $('#stateOfOrigin').val();
    // data.phoneNumber = $('#phoneNumber').val();
    // data.emailAddress = $('#emailAddress').val();
    // data.dateOfBirth = $('#dateOfBirth').val();
    //
    // // STAFF ADDRESS
    // data.addressDTO.name = $('#staffAddress').val();
    // data.addressDTO.city = $('#city').val();
    // data.addressDTO.country = $('#country').val();
    // data.addressDTO.localGovt = $('#localGovt').val();
    // data.addressDTO.state = $('#state').val();
   var datas = {
           "staffId": $('#staffId').val(),
           "title": $('#title').val(),
           "firstName": $('#firstName').val(),
           "middleName": $('#middleName').val(),
           "lastName": $('#lastName').val(),
           "religion": $('#religion').val(),
           "gender": $('.gender:checked').val(),
           "maritalStatus": $('#maritalStatus').val(),
           "nationality": $('#nationality').val(),
           "stateOfOrigin": $('#stateOfOrigin').val(),
           "phoneNumber": $('#phoneNumber').val(),
           "emailAddress": $('#emailAddress').val(),
           "dateOfBirth": $('#dateOfBirth').val(),
       addressDTO: {
            "name" : $('#staffAddress').val(),
            "city" : $('#city').val(),
            "country" : $('#country').val(),
            "localGovt" : $('#localGovt').val(),
            "state" : $('#state').val(),
       }
   }


    // QUALIFICATION
    datas.qualifications = [];
    if(counter > 0){
        for (var i = 0; i < counter; i++){
            datas.qualifications.push({
               "schoolName" : $('#schoolName'+i).val(),
                "institutionType": $('#institutionType'+i).val(),
                "entryDate": $('#entryDate'+i).val(),
                "graduationDate": $('#graduationDate'+i).val()
            });
        }
    }else {
        datas.qualifications.push(
            {
                "schoolName": $('#schoolName').val(),
                "institutionType": $('#institutionType').val(),
                "entryDate": $('#entryDate').val(),
                "graduationDate": $('#graduationDate').val()
            }
        );
    }

    // REFEREE
    datas.refereeDTOS = [];
    if(refereesCount > 0){
        for(var i = 0; i < refereesCount; i++){
            datas.refereeDTOS.push({
             "name" : $('#name').val(),
             "occupation" : $('#occupation').val(),
             "phoneNumber" : $('#phoneNumber').val(),
            });
            datas.refereeDTOS.addressDTO.push({
                "name": $('#refAddress').val()
            });

        }
    }else {
        datas.refereeDTOS.push({
            "name" : $('#name').val(),
            "occupation" : $('#occupation').val(),
            "phoneNumber" : $('#phoneNumber').val(),
            addressDTO : {
                "name" : $('#refAddress').val(),
            }
        });


    }

    datas.jobPosition = $('#jobPosition').val();
    datas.venueAssigned = $('#venueAssigned').val();
    datas.dateOfEmployment = $('#dateOfEmployment').val();



    console.log(datas);

    $.ajax(
        {
            url: "http://localhost:8080/staff/save",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(datas),
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
//TODO Later (assign an id to add button )
function add(res){
    if(res == ""){
        counter++;
    }else if(res == ""){
        refereesCount++;
    }
}

//  (HIDE PAYROLL) AND DATATABLE
$(document).ready(function(){
    $('#payroll').hide();//Hide payRoll on load of add staff page

    //DataTable implementation start here..

           var table = $('#staffTable').DataTable({
               select: true,
               responsive: true,
               "aaSorting": [[0, "desc"]],
               // "lengthMenu" : [ [ 5, 10, -1 ], [ 5, 10, "All" ] ],
               "pagingType": "simple_numbers",
               "draw":1,
               "recordsTotal": 4,
               "recordsFiltered": 4,
               "searching": true,
               "processing": true,
               // "serverSide": false,
               "paging" : true,
               "ordering" : false,
               "dom": 'blfrtip',
               "sAjaxSource": "/staff/all",
               "sAjaxDataProp": "",
               "aoColumns": [
                   {"mData": "staffId"},
                   {"mData": "firstName"},
                   {"mData": "middleName"},
                   {"mData": "lastName"},
                   {"mData": "phoneNumber"},
                   {"mData": "emailAddress"},
                   {"mData": "operations"},
               ],
               "columnDefs" : [{
                    "data" : "operations",
                    "render" : function (data, type, row) {
                        var linkView = '<div class="btn-group">'+
                                '<a type="button" class="btn btn-warning " href="/view/'+row.id +'"><i class="fa fa-eye"></i></a>'+
                                '</div>';
                        var linkEdit = '<div class="btn-group">'+
                            '<a type="button" class="btn btn-warning " href="/edit/'+row.id +'"><i class="fa fa-pencil"></i></a>'+
                            '</div>';
                        var linkDelete = '<div class="btn-group">'+
                        '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                        '</div>';
                        return linkView + " | " + linkEdit + " | " + linkDelete;
                    },
                   "targets" : 6
               }],

           });
    });

$(function () {
    $('.payroll').on('click', function () {
        $('#payroll').show();
    })
})

// PAYROLL  AND DATATABLE IMPLEMENTATION END HERE

//Delete Method start here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a staff ?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                   console.log("successfully delete user");
                    window.location = id+"/delete/";
                }
            },
            cancel: {
                label: 'No',
                className: 'btn-success',
                callback: function (result) {
                    // $('.bootbox').modal('hide');
                    result = false;
                }
            }
        },
    });

};
