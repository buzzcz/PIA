function initUpload() {
	var profilePicture = $('#profile-picture');
	var selectedFile = $('#selected-file');
	profilePicture.change(function () {
		console.log(profilePicture);
		var files = profilePicture.get(0).files;
		if (files.length > 0) {
			selectedFile.get(0).textContent = files[0].name;
		}
	});
}

$(initUpload);