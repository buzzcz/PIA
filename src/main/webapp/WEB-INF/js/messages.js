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
		var collapsible = $('.collapsible-view.in');
		collapsible.collapse('hide');
		collapsible.first().one('hidden.bs.collapse', function () {
			view.collapse('show');
			messageHeight();
		});
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