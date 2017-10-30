function toggleViews() {
	var w = $(window).width();
	var profilePicture = $('#profile-picture');
	if (w < 768) {
		var profileInfo = $('#personal-info');
		$('.collapsible-view').not(profileInfo).collapse('hide');
		profileInfo.collapse('show');

		profilePicture.removeClass('profile-picture');
		profilePicture.addClass('profile-picture-small');
	} else {
		$('.collapsible-view').collapse('show');

		profilePicture.removeClass('profile-picture-small');
		profilePicture.addClass('profile-picture');
	}
}

function showView(viewToShow) {
	var view = $('#' + viewToShow);
	if (!view.hasClass('in')) {
		$('.collapsible-view').collapse('hide');
		view.collapse('show');
	}
}

function init() {
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);
