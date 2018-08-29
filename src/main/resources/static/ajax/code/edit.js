$(document).ready(function () {
	code_id =	$("input[name='id']").val();
	if(code_id){
		_url = '/admin/v1/code/{idx}';
		url = _url.replace('{idx}',code_id);
		entityload(url,$('#editCode'));
	}
	// setup submit
	
	 $("#editCode").submit(function (event) {
	        event.preventDefault();
	        _url = '/admin/v1/code'
	        ajax_submit(_url,$('#editCode'));
	    });

});


