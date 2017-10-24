function toggleConversations(){
	var w = $(window).width();
	if (w < 768) {
		$('#conversations').removeClass('in');
	} else {
		$('#conversations').addClass('in');
	}

	conversationHeight();
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

	conversationHeight();
}

function conversationHeight() {
	var message = $('#message');
	var conversation = $('#messages');
	var parent = conversation.parent();
	conversation.height(0);
	conversation.height(parent.height() - message.height() - 60);
}

function init() {
	toggleConversations();
	$(window).resize(toggleConversations);
	messageHeight();
	$('#message').keyup(messageHeight);
}

$(init);