<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/feed.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <title>User's Profile</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top container-fluid row">
    <div class="col-xs-9 col-sm-4">
        <a class="btn btn-link" id="logo">Kiv<span class="logo-border">Book</span></a>
    </div>
    <div class="visible-xs col-xs-3 pull-right top-padding">
        <button class="btn btn-default glyphicon glyphicon-menu-hamburger" data-toggle="collapse"
                data-target=".collapsible-menu" aria-expanded="true" aria-controls="menu log-out-form"
                title="Menu"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 top-padding collapsible-menu" id="menu">
        <!-- TODO: Add some sort of menu. -->
        <button class="btn btn-default glyphicon glyphicon-th-list" title="Posts"></button>
        <button class="btn btn-default glyphicon glyphicon-comment" title="Messages"></button>
        <button class="btn btn-default glyphicon glyphicon-search" title="Search"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 collapsible-menu" id="log-out-form">
        <a class="btn btn-link" id="logged-user"><img id="user-picture" src="../../../../static/img/profile.png"
                                                      alt="Profile Picture">
            Logged User</a>
        <button class="btn btn-default" name="log-out" onclick="window.location.href='/logout'" title="Log Out">Log
            Out
        </button>
    </div>
</nav>
<div class="container-fluid content full-height">
    <div class="row full-height">
        <div class="col-xs-12" id="profile-info">
            <!-- TODO: Make the line not to break under the picture. -->
            <h1><img class="profile-picture" id="profile-picture" src="../../../../static/img/profile.png"
                     alt="User's Profile Picture"> FirstName LastName</h1>
        </div>
        <div class="visible-xs col-xs-12 bottom-padding" id="buttons">
            <button class="btn btn-default glyphicon glyphicon-briefcase" id="personal-info-toggle"
                    onclick="showView('personal-info')" title="Personal Info"></button>
            <button class="btn btn-default glyphicon glyphicon-th-list" id="posts-toggle"
                    onclick="showView('posts')" title="Posts"></button>
            <button class="btn btn-default glyphicon glyphicon-user" id="friends-toggle"
                    onclick="showView('friends')" title="Friends"></button>
        </div>
        <div class="collapse in col-xs-12 col-sm-4 collapsible-view" id="personal-info">
            <h2 class="bottom-border">Personal info:</h2>
            <table>
                <tr>
                    <td><h4>First Name:</h4></td>
                    <td class="info-row"><h4>FirstName</h4></td>
                </tr>
                <tr>
                    <td><h4>Last Name:</h4></td>
                    <td class="info-row"><h4>LastName</h4></td>
                </tr>
                <tr>
                    <td><h4>Birthday:</h4></td>
                    <td class="info-row"><h4>Birthday</h4></td>
                </tr>
                <tr>
                    <td><h4>Age:</h4></td>
                    <td class="info-row"><h4>Age</h4></td>
                </tr>
                <tr>
                    <td><h4>Email:</h4></td>
                    <td class="info-row"><h4>Email</h4></td>
                </tr>
            </table>
            <div class="top-padding">
                <button class="btn btn-default" name="add-friend" disabled>Already Friends <span
                        class="glyphicon glyphicon-ok" title="Friendship Status"></span></button>
            </div>
            <div class="top-padding">
                <button class="btn btn-default" name="add-friend">New Post <span
                        class="glyphicon glyphicon-edit" title="New Post"></span></button>
            </div>
            <div class="top-padding">
                <button class="btn btn-default" name="add-friend">New Message <span
                        class="glyphicon glyphicon-send" title="New Message"></span></button>
            </div>
        </div>
        <div class="collapse in col-xs-12 col-sm-4 collapsible-view" id="posts">
            <h2 class="bottom-border">Posts:</h2>
            <div class="well well-sm">
                <div>
                    <a class="btn btn-link friend-link post">Who posted</a>
                    <h4>Text</h4>
                </div>
                <div>
                    <button class="btn btn-default" title="Like"><span class="glyphicon glyphicon-star-empty"></span>
                        <span class="badge">0</span></button>
                    <button class="btn btn-default" title="Comment"><span class="glyphicon glyphicon-list-alt"></span>
                        Comments <span class="badge">2</span></button>
                </div>
            </div>
            <div class="well well-sm">
                <div>
                    <a class="btn btn-link friend-link post">Who posted</a>
                    <h4>Text</h4>
                    <img class="post-picture" src="../../../../static/img/post.png" alt="Post Picture">
                </div>
                <div>
                    <button class="btn btn-default" title="Like"><span class="glyphicon glyphicon-star"></span> <span
                            class="badge">1</span></button>
                    <button class="btn btn-default" title="Comment"><span class="glyphicon glyphicon-list-alt"></span>
                        Comments
                        <span class="badge">0</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="collapse in col-xs-12 col-sm-4 collapsible-view" id="friends">
            <h2 class="bottom-border">Friends:</h2>
            <div class="well well-sm">
                <a class="btn btn-link friend-link"><img class="friend-picture" src="../../../../static/img/profile.png"
                                                         alt="Friend's Picture"> User Name</a>
            </div>
            <div class="well well-sm">
                <a class="btn btn-link friend-link"><img class="friend-picture" src="../../../../static/img/profile.png"
                                                         alt="Friend's Picture"> User Name</a>
            </div>
            <div class="well well-sm">
                <a class="btn btn-link friend-link"><img class="friend-picture" src="../../../../static/img/profile.png"
                                                         alt="Friend's Picture"> User Name</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
