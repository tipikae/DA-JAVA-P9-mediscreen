$(document).ready(function() {
	// hide assessment results
	$('#assessmentById').hide();
	$('#assessmentByFamily').hide();
	
	// listener link click
	$('.assess').on('click', function(e) {
		let type = $(this).data('assesstype');
		if(type == 'id') {
			assessHealth('id', $(this).data('patid'));
		} else if(type == 'family') {
			assessHealth('family', $(this).data('family'));
		}
		
	});
});

function assessHealth(type, param) {
	let endpoint,
		elemYes,
		elemNo;
	switch(type) {
		case 'id':
			endpoint = '../assess/id/' + param;
			elemYes = '#assessmentById';
			elemNo = '#assessmentByFamily';
			break;
		case 'family':
			endpoint = '../assess/familyName/' + param;
			elemYes = '#assessmentByFamily';
			elemNo = '#assessmentById';
			break;
		default:
			return false;
	}
	
	$.get(endpoint).done(function(fragment) {
		$(elemYes).replaceWith(fragment);
		$(elemYes).show();
		$(elemNo).hide();
	});
	
	return false;
}
