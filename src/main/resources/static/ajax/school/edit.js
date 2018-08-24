$(document).ready(function () {
    $("#createSchool").click(function(e) {
	school_id =	$("input[name='id']").val();
	_url = '/admin/v1/school/{idx}/view';
	url = _url.replace('{idx}',school_id);
	entityload(url,$('#editSchool'));
    });
});

