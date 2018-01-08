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

function init() {
	initDatepicker();
	datepickerWidth();
    errorsWidth();
	$(window).resize(datepickerWidth);
    $(window).resize(errorsWidth());
}

$(init);