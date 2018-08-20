function ajax_submit(surl, form) {

	temp = form.serializeArray();
	dt = {}
	$(temp).each(function(index, element) {
		dt[element['name']] = element['value']
	});

	// clear the form of old errors
	$(form).find(".form-group").removeClass("has-error");

	$.ajax({
		method : "POST",
		contentType : "application/json",
		url : surl,
		data : JSON.stringify(dt),
		dataType : 'json',
		timeout : 0,
		success : function(data) {
			swal({
				title : "Saved!",
				text : "Saved successfully",
				icon : "success",
			});
			console.log("SUCCESS : ", data);

		},
		error : function(e) {
			ee = e.responseJSON.error;
			if ($.type(ee) === "string") {
				swal({
					title : "Error!",
					text : ee,
					icon : "warning",
				});
			} else if (ee) {
				$(ee).each(
						function(index, element) {
							element.field
							$(form).find("[name='" + element.field + "']").closest(".form-group")
									.addClass("has-error");
						});
			}

		}
	});

}