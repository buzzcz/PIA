function initDatepicker() {
	$('.input-group.date').datepicker({
		format: "dd.mm.yyyy",
		endDate: "today",
		maxViewMode: 2,
		autoclose: true
	});
}

function datepickerWidth() {
    $('.input-group.date').width($('#email').outerWidth());
}

function initUpload() {
    var profilePicture = $('#picture');
	var selectedFile = $('#selected-file');
	profilePicture.change(function () {
		var files = profilePicture.get(0).files;
		if (files.length > 0) {
			selectedFile.get(0).value = files[0].name;
		}
	});
}

function init() {
	initDatepicker();
	initUpload();
	datepickerWidth();
	$(window).resize(datepickerWidth);
}

$(init);