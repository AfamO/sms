function save(obj){
    /*
    *obj is an object which contains
    * url,form_id,obj,callback
     */

    var data = {};

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
    console.log(data);

    $.ajax({

        url: url,
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function(res){
            alert("success");
            callback(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("error");
            console.log(xhr.status);
            console.log(thrownError);
        }
    })

}