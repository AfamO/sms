


//Save Parent Method goes here

$("#addparent").click(function () {
    $(this).attr("disabled", "disabled");
    var data = {
        "firstName": $('#firstName').val(),
        "middleName": $('#middleName').val(),
        "lastName": $('#lastName').val(),
        "phoneNumber": $('#phoneNumber').val(),
        "email": $('#email').val(),
        "nationality": $('#nationality').val(),
        "religion": $('#religion').val(),
        "gender": $("input[name='gender']:checked").val(),
        "maritalStatus": $('#maritalStatus').val(),


        "address": {
            "name": $('#name').val(),
            "localGovt": $('#localGovt').val(),
            "stateOfOrigin": $('#stateOfOrigin').val(),
            "country": $('#country').val()
        }

    };

    var url = "/parent/save"
    $.ajax(
        {

        url:url,
        type:'POST',
        contentType:"application/json;charset=utf-8",
        data: JSON.stringify(data),

        success:function () {
            alert("Success");
            window.location = "http//localhost:8000/parent/listparent";

        },

        error:function () {
            window.location = "http//localhost:8000/parent/listparent";

        }
    }
    );


});


//Save parent method ends here


//DataTable Method starts here

$(document).ready(function () {

    //Country method


    $.ajax({
        url:'/ajax/gistfile1.json',
        dataType:'json',

        success:function (res) {

            var options = "";
            var index = 0;
            for(i = 0 ; i < res.countries.length; i++){

                $('<option/>').attr('value',res.countries[i].country).text(res.countries[i].country).appendTo('#country');

                if (res.countries[i].country ==="Nigeria")

                {
                    index = i;
                    $('<option/>').attr('value',res.countries[i].country).text(res.countries[i].country).appendTo('#country').prop("selected",true);
                }


            }


            var t = res.countries[index].states;
            var options = "";
            for(var j=0;j<t.length;j++){

                options += '<option value=' + t[j] + '>'+ t[j] +'</option>';

                $('#stateOfOrigin').html(options);
            }

        },

        error:function (e) {
            console.log(e);
        }
    });

    //States method

    $('#country').change(function () {

        var country = $(this).find(":selected").text();

        $.ajax({
            url: '/ajax/gistfile1.json',
            dataType: 'json',

            success: function (res) {

                for (i = 0; i < res.countries.length; i++) {

                    if (res.countries[i].country === country) {

                        var options = "";

                        for (j = 0; j < res.countries[i].states.length; j++)

                            options += '<option value=' + res.countries[i].states[j] + '>' + res.countries[i].states[j] + '</option>';

                        $('#stateOfOrigin').html(options);
                    }

                }

            }

        });
    });



    $('#stateOfOrigin').change(function () {

        var state = $(this).find(":selected").text();

        $.ajax({
            url: '/ajax/localGovt.json',
            dataType: 'json',

            success: function (res) {

                for (i = 0; i < res.length; i++) {

                    if (res[i].state.name === state + " State" || res[i].state.name.substring(0,3) === state.substring(0,3)) {

                        var options = "";

                        for (j = 0; j < res[i].state.locals.length; j++) {

                            options += '<option value=' + res[i].state.locals[j].name + '>' + res[i].state.locals[j].name + '</option>';

                            $('#localGovt').html(options);
                        }


                    }




                }

            }

        });
    });


//    Data table for parents starts here

    var table = $('#parentTable').DataTable({
        select: true,
        responsive: true,
        "draw":1,
        "recordsTotal": 4,
        "recordsFiltered": 4,

        language: {
            searchPlaceholder: "Search records"
        },

        "pagingType": "simple_numbers",
        "searching": true,
        "processing": true,
        "paging" : true,
        "ordering" : false,
        "dom": 'blfrtip',
        "sAjaxSource": "/parent/all",
        "sAjaxDataProp": "",
        "aoColumns": [
            {"mData": "firstName"},
            {"mData":"lastName"},
            {"mData": "phoneNumber"},
            {"mData": "email"},
            {"mData": "address.name"},
            {"mData": "operations"}
        ],
        "columnDefs" : [{
            "data" : "operations",

            "mRender" : function (data, type, row) {
                var linkView = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick=view('+row.id+')><i class="fa fa-eye"></i></a>'+
                    '</div>';
                var linkEdit = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick=edit('+row.id+')><i class="fa fa-pencil"></i></a>'+
                    '</div>';
                var linkDelete = '<div class="btn-group">'+
                    '<a type="button" class="btn btn-warning " onclick="deleteDialog('+row.id+')"><i class="fa fa-trash"></i></a>'+
                    '</div>';
                return linkView + " | " + linkEdit + " | " + linkDelete;
            },
            "targets" : 5
        }]

    });
});

function view(id){
    window.location = "/parent/"+id+"/get"
}

function edit(id){
    window.location = "/parent/"+id+"/edit"
}

//Delete Method start here
function deleteDialog(id) {
    bootbox.dialog({
        message: "You are about to delete a parent ?",
        buttons: {
            danger: {
                label: 'Delete',
                className: 'btn-danger',
                callback: function (result) {
                    console.log("successfully delete user");
                    window.location = "/parent/"+id+"/delete/";
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
