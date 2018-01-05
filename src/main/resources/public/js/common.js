function toggleMenu(){
	var w = $(window).width();
	if (w < 768) {
		$('.collapsible-menu').removeClass('in');
	} else {
		$('.collapsible-menu').addClass('in');
	}
}

function submitForm(form, path) {
    var formData = {};
    var a = $(form).serializeArray();
    $.each(a, function () {
        if (formData[this.name]) {
            if (!formData[this.name].push) {
                formData[this.name] = [formData[this.name]];
            }
            formData[this.name].push(this.value || '');
        } else {
            formData[this.name] = this.value || '';
        }
    });
	$.ajax({
		type: 'POST',
		url: path,
		data: JSON.stringify(formData),
		dataType: 'json',
		contentType: 'application/json'
	});
}

function init() {
	toggleMenu();
	$(window).resize(toggleMenu);
}

$(init);