

//Edit Parent Method


$("#editparent").click(function () {
    $(this).attr("disabled", "disabled");
    var data = {
        "id":parseInt($('#parentId').val()),
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
            "address": $('#address').val(),
            "localGovt": $('#localGovt').val(),
            "stateOfOrigin": $('#stateOfOrigin').val(),
            "country": $('#country').val()

        }

    };


    console.log(data);

    $.ajax(
        {
        url:"http://localhost:8000/parent/update",

        method:"POST",

        contentType:"application/json;charset=utf-8",

        data: JSON.stringify(data),

        success:function () {

            alert("success");
        },
        error:function (xhr, ajaxOptions,thrownError) {

            alert("failed");
            window.location = "/parent/listparent";
            console.log(xhr.status);

            console.log(thrownError)
        }
    })


}
);