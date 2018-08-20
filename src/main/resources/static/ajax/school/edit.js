$(document).ready(function () {
	school_id =	$("input[name='id']").val();
	if(school_id){
		_url = '/admin/v1/school/{idx}/view';
		url = _url.replace('{idx}',school_id);
		entityload(url,$('#editSchool'));
	}
	
	 $("#editSchool").submit(function (event) {
	        event.preventDefault();
	        _url = '/admin/v1/school'
	        ajax_submit(_url,$('#editSchool'));
	    });

});

