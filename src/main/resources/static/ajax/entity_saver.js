
function ajax_submit(surl,form) {

    temp = form.serializeArray() ;
    dt= {}
    $(temp).each(function(index,element){
    	dt[element['name']]=element['value']
    });

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: surl,
        data: JSON.stringify(dt),
        dataType: 'json',
        timeout: 0,
        success: function (data) {
            swal({
            	  title: "Saved!",
            	  text: "Saved successfully",
            	  icon: "success",
            	});
            console.log("SUCCESS : ", data);

        },
        error: function (e) {
        	  swal({
            	  title: "Error!",
            	  text: e,
            	  icon: "warning",
            	});
           

           // console.log("ERROR : ", e);
   //disable button ?

        }
    });

}