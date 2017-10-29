function toggleViews(){
	var w = $(window).width();
	if (w < 768) {
		$('.collapsible-view').removeClass('in');
		$('#conversations').addClass('in');
	} else {
		$('.collapsible-view').addClass('in');
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
	$('.collapsible-view').removeClass('in');
	view.addClass('in');
	messageHeight();
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