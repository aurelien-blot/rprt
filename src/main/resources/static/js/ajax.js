
function callAjax(method, callback, url, dataParams, args){

	$.ajax({
		url : generateUrl(url),
		type : method,
		data : dataParams
	}).done(callback);
}
