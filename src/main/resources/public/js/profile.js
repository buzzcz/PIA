function toggleViews() {
	var w = $(window).width();
	var profilePicture = $('#profile-picture');
	if (w < 768) {
		var profileInfo = $('#personal-info');
		var collapsible = $('.collapsible-view.in').not(profileInfo);
		collapsible.collapse('hide');
		collapsible.first().one('hidden.bs.collapse', function () {
			profileInfo.collapse('show');
		});

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
		var collapsible = $('.collapsible-view.in');
		collapsible.collapse('hide');
		collapsible.first().one('hidden.bs.collapse', function () {
			view.collapse('show');
		});
	}
}

function init() {
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);
