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

function init() {
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);
