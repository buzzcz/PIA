function toggleConversations(){
	var w = $(window).width();
	if (w < 768) {
		$('#conversations').removeClass('in');
	} else {
		$('#conversations').addClass('in');
	}
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

function showConversations() {
	var conversations = $('#conversations');
	if (conversations.hasClass('in')) {
		conversations.removeClass('in');
		$('#messages').parent().addClass('in');
	} else {
		conversations.addClass('in');
		$('#messages').parent().removeClass('in');
	}
}

function sendMessage(e) {
	if (e.which == 13 && !e.shiftKey) {
		e.preventDefault();
		//	TODO: Send message.
	}
}

function init() {
	toggleConversations();
	$(window).resize(toggleConversations);
	messageHeight();
	var message = $('#message');
	message.keyup(messageHeight);
	message.keydown(function (e) {
		sendMessage(e);
	});
}

$(init);