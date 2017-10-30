function toggleViews() {
	var w = $(window).width();
	if (w < 768) {
		var conversations = $('#conversations');
		$('.collapsible-view').not(conversations).collapse('hide');
		conversations.collapse('show');
	} else {
		$('.collapsible-view').collapse('show');
	}

	messageHeight();
}

function messageHeight() {
	var message = $('#message');
	message.height(0);
	var scrollHeight = message.get(0).scrollHeight;
	if (scrollHeight) {
		if (scrollHeight < 150) {
			message.height(scrollHeight);
		} else {
			message.height(150);
		}
	}
}

function showView(viewToShow) {
	var view = $('#' + viewToShow);
	if (!view.hasClass('in')) {
		var collapsible = $('.collapsible-view');
		collapsible.collapse('hide');
		view.collapse('show');
		messageHeight();
	}
}

function sendMessage(e) {
	if (e.which === 13 && !e.shiftKey) {
		e.preventDefault();
		//	TODO: Send message.
	}
}

function init() {
	toggleViews();
	$(window).resize(toggleViews);
	messageHeight();
	var message = $('#message');
	message.keyup(messageHeight);
	message.keydown(function (e) {
		sendMessage(e);
	});
}

$(init);