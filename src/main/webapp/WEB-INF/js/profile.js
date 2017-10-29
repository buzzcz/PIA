function toggleViews(){
	var w = $(window).width();
	var profilePicture = $('#profile-picture');
	if (w < 768) {
		$('.collapsible-view').removeClass('in');
		$('#personal-info').addClass('in');

		profilePicture.removeClass('profile-picture');
		profilePicture.addClass('profile-picture-small');
	} else {
		$('.collapsible-view').addClass('in');

		profilePicture.removeClass('profile-picture-small');
		profilePicture.addClass('profile-picture');
	}
}

function showView(viewToShow) {
	var view = $('#' + viewToShow);
	$('.collapsible-view').removeClass('in');
	view.addClass('in');
}

function init() {
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);
