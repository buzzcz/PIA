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

	setHeights();
}

function personalInfoHeight(height) {
	var personalInfo = $('#personal-info');
	personalInfo.height(0);
	personalInfo.height(height);
}

function postsHeight(height) {
	var posts = $('#posts-scroll');
	posts.height(0);
	posts.height(height);
}

function friendsHeight(height) {
	var friends = $('#friends-scroll');
	friends.height(0);
	friends.height(height);
}

function setHeights() {
	var profileInfo = $('#profile-info');
	var parent = profileInfo.parent();
	var buttons = $('#buttons');
	var height = parent.height() - buttons.height() - profileInfo.height() - 65;

	personalInfoHeight(height);
	postsHeight(height);
	friendsHeight(height);
}

function showView(viewToShow) {
	var view = $('#' + viewToShow);
	$('.collapsible-view').removeClass('in');
	view.addClass('in');
}

function init() {
	// setHeights();
	// $(window).resize(setHeights);
	toggleViews();
	$(window).resize(toggleViews);
}

$(init);
