$(document).ready(function () {
	code_id =	$("input[name='id']").val();
	_url = '/admin/v1/code/{idx}';
	url = _url.replace('{idx}',code_id);
	entityload(url,$('#editCode'));
});

