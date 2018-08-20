$(document).ready(function () {
    school_id =	$("input[name='id']").val();
    _url = '/admin/permission/{idx}/edit';
    url = _url.replace('{idx}',permission_id);
    entityload(url,$('#editPermission'));
});