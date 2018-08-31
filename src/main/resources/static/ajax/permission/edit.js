$(document).ready(function () {
    $("#createPermission").click(function(e) {
        school_id = $("input[name='id']").val();
        _url = "http://localhost:8080/permission/add",
        url = _url.replace('{idx}', permission_id);
        entityload(url, $('#Permission'));
    });
});