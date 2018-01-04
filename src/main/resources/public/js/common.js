function toggleMenu(){
	var w = $(window).width();
	if (w < 768) {
		$('.collapsible-menu').removeClass('in');
	} else {
		$('.collapsible-menu').addClass('in');
	}
}

function init() {
	toggleMenu();
	$(window).resize(toggleMenu);
}

$(init);