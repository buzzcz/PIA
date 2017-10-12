function initUpload() {
	var profilePicture = $('#profile-picture');
	var selectedFile = $('#selected-file');
	profilePicture.change(function () {
		var files = profilePicture.get(0).files;
		if (files.length > 0) {
			selectedFile.get(0).value = files[0].name;
		}
	});
}

$(initUpload);