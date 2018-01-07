function toggleViews() {
    var w = $(window).width();
    if (w < 768) {
        var posts = $('#posts');
        var collapsible = $('.collapsible-view.in').not(posts);
        collapsible.collapse('hide');
        collapsible.first().one('hidden.bs.collapse', function () {
            posts.collapse('show');
        });
    } else {
        $('.collapsible-view').collapse('show');
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
