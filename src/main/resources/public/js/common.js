function toggleMenu(){
	var w = $(window).width();
	if (w < 768) {
		$('.collapsible-menu').removeClass('in');
	} else {
		$('.collapsible-menu').addClass('in');
	}
}

function showView(viewToShow) {
    var view = $('#' + viewToShow);
    if (!view.hasClass('in')) {
        var collapsible = $('.collapsible-view.in');
        collapsible.collapse('hide');
        collapsible.first().one('hidden.bs.collapse', function () {
            view.collapse('show');
        });
    }
}

function init() {
	toggleMenu();
	$(window).resize(toggleMenu);
}

$(init);