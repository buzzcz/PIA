function toggleViews() {
	var w = $(window).width();
	if (w < 768) {
		var conversations = $('#conversations');
		var collapsible = $('.collapsible-view.in').not(conversations);
		collapsible.first().collapse('hide');
		collapsible.one('hidden.bs.collapse', function () {
			conversations.collapse('show');
		});
	} else {
		$('.collapsible-view').collapse('show');
	}
}

function init() {
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);