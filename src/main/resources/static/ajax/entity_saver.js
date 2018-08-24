function ajax_submit(surl, form) {

	temp = form.serializeArray();
	dt = {}
	$(temp).each(function(index, element) {
		dt[element['name']] = element['value']
	});

	// clear the form of old errors
	$(form).find(".form-group").removeClass("has-error");
	$(form).find("label.error").remove();

	$.ajax({
		method : "POST",
		contentType : "application/json",
		url : surl,
		data : JSON.stringify(dt),
		dataType : 'json',
		timeout : 0,
		success : function(data) {
			toastr.success('Saved', "Saved Successfully")
			console.log("SUCCESS : ", data);

		},
		error : function(e) {
			ee = e.responseJSON.error;
			if ($.type(ee) === "string") {
				
				toastr.error('Error', ee)
			} else if (ee) {
				$(ee).each(
						function(index, element) {
							msg = '<label  class="error" for="'+element.message+'">'+element.message+'</label>'
							field = $(form).find("[name='" + element.field + "']");
							field.after(msg)
							field.closest(".form-group").addClass("has-error");
							
							
						});
			}

		}
	});

}