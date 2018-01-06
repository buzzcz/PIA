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

function errorsWidth() {
    $('.errors').width($('#email').outerWidth() * 2);
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
    errorsWidth();
	$(window).resize(datepickerWidth);
    $(window).resize(errorsWidth());
}

$(init);