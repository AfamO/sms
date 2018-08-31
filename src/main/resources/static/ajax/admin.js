//
// $("#addcode").click(function () {
//     $(this).attr("disabled", "disabled");
//
//     var data = {
//         "type": $("#type").val(),
//         "name": $("#name").val(),
//         "description": $("#description").val()
//     };
//
//     $.ajax({
//         url: "http://localhost:8080/codes/add",
//
//         type: 'POST',
//
//         contentType: 'application/json;charset=utf-8',
//
//         data: JSON.stringify(data),
//
//         success: function (data) {
//             alert("Success");
//         },
//
//         error: function () {
//             window.location = "/admin/codes/list";
//         }
//
//     });
//
// });



$(document).ready(function(){

    alert("i got here");

    $("#createOrUpdateCode").click(function(e) {

        e.preventDefault();

        /*

        *to use the function save
        * if our object has many to many mapping
        * the html would be <input type="text" name="nameOfOnModel" aobj="child" count=""/>

         */

        save({
            url:"http://localhost:8080/admin/v1/add",
            form: "#addCode",
            fn:function(){
                window.location.href = "/admin/v1/code/";
            }
        });
    });
});



function save(obj){
    /*
    *obj is an object which contains
    * url,form_id,obj,callback
     */

    var data = {};

    /*
        date = {

            Normal data

            firstName:"Muhammad",
            middleName:"Mobolaji"
            lastName:"Habib"

            Optional Arguments(qualifications, referee,Adress,others)

            address:{
                    country:"Nigeria",
                    city:"Lagos",
                    state:"Lagos",
                    name:"Number 8, Joel Ogunnaike street, Ikeja GRA,Ikeja"
                },

            qualifications:[
                    {
                        institutionName:"FUTA",
                        institutionType:"University",
                        qualification:"FirstDegree",
                        aquired:"2017"
                    },
                    {
                        institutionName:"FUTA",
                        institutionType:"University",
                        qualification:"Master Degree",
                        aquired:"2017"
                    },
                    {
                        institutionName:"FUTA",
                        institutionType:"University",
                        qualification:"Doctorate Degree",
                        aquired:"2017"
                    }
                ],
            referee:{
                    name:"Ayoola Adebimpe",
                    address:"Number 8, Joel Ogunnaike street, Ikeja GRA,Ikeja"
                }


            }
    */

    var url = obj["url"] || "";
    var form = obj["form"];
    var dataProps = obj["props"] || null;
    var callback = obj["fn"] || "";
    var count = {}

    if(dataProps){

        for(var i in dataProps){
            if($.isArray(dataProps[i])){
                count[i] = [];
            }
            data[i] = dataProps[i];
        }
    }


    $(form).find("[name]").each(function(index,element){

        if($(element).attr("obj")){
            var op = $(element).attr("obj");

            data[op][$(element).attr("name")] = $(element).val();
            // console.log($(element).val());
        }

        else if($(element).attr("aobj")){

            var aop = $(element).attr("aobj");

            var aCount = $(element).attr("count");

            console.log(count[aop]);

            if(!count[aop].includes(aCount)){
                count[aop].push(aCount);

                data[aop].push({});
            }
            var index = parseInt(count[aop][aCount]);
            var props = $(element).attr("name");
            var values = $(element).val()
            data[aop][index][props] = values;
        }
        else
        {
            var prop = $(element).attr("name");
            var value = $(element).val();
            data[prop] = value;
        }
        // console.log(index,$(element).attr("obj"));
    });

    $.ajax({

        url: url,
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(res){
            alert("success");

        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("error");
            console.log(xhr.status);
            console.log(thrownError);
        }
    })
    console.log(data);


}