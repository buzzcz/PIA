<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/css/common.css">
    <link type="text/css" rel="stylesheet" href="/css/feed.css">
    <script type="application/javascript" src="/js/jquery.min.js"></script>
    <script type="application/javascript" src="/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="/js/common.js"></script>
    <script type="application/javascript" src="/js/feed.js"></script>
    <title>Feed of Posts</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top container-fluid row">
    <div class="col-xs-9 col-sm-4">
        <a class="btn btn-link" href="/feed" id="logo">Kiv<span class="logo-border">Book</span></a>
    </div>
    <div class="visible-xs col-xs-3 pull-right top-padding">
        <button class="btn btn-default glyphicon glyphicon-menu-hamburger" data-toggle="collapse"
                data-target=".collapsible-menu" aria-expanded="true" aria-controls="menu log-out-form"
                title="Menu"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 top-padding collapsible-menu" id="menu">
        <button class="btn btn-default glyphicon glyphicon-th-list" onclick="window.location.href='/feed'"
                title="Posts"></button>
        <button class="btn btn-default glyphicon glyphicon-comment" onclick="window.location.href='/messages'"
                title="Messages"></button>
        <button class="btn btn-default glyphicon glyphicon-search" onclick="window.location.href='/search'"
                title="Search"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 collapsible-menu" id="log-out-form">
        <a class="btn btn-link" id="logged-user" href="/profile">
            <c:choose>
                <c:when test="${user.picture == null || empty user.picture}">
                    <img id="user-picture" src="/img/profile.png" alt="Profile Picture">
                </c:when>
                <c:otherwise>
                    <img id="user-picture" src="${user.picture}" alt="Profile Picture">
                </c:otherwise>
            </c:choose>
            ${user.firstName} ${user.lastName}
        </a>
        <button class="btn btn-default" name="log-out" onclick="window.location.href='/logout'" title="Log Out">Log
            Out
        </button>
    </div>
</nav>
<div class="container-fluid content full-height">
    <div class="row full-height">
        <div class="visible-xs col-xs-12 bottom-padding" id="buttons">
            <button class="btn btn-default glyphicon glyphicon-edit" title="New Post" id="new-post-toggle"
                    onclick="showView('new-post')"></button>
            <button class="btn btn-default glyphicon glyphicon-th-list" id="posts-toggle"
                    onclick="showView('posts')" title="Posts"></button>
            <button class="btn btn-default glyphicon glyphicon-user" id="friends-toggle"
                    onclick="showView('friends')" title="Friends"></button>
        </div>
        <div class="collapse in col-xs-12 col-sm-3 col-sm-offset-1 collapsible-view" id="new-post">
            <div class="top-padding">
                <h2 class="bottom-border">New Post:</h2>
                <form:form class="form-horizontal" id="postForm" action="/new-post" method="post"
                           modelAttribute="post" enctype="multipart/form-data">
                    <form:label class="sr-only" path="text">Post text</form:label>
                    <form:textarea class="form-control bottom-padding" path="text"
                                   placeholder="Type your post..."/>
                    <form:label path="privacy">Private: </form:label>
                    <form:checkbox id="privacy" path="privacy"/><br>
                    <form:label path="file">Profile Picture:</form:label>
                    <form:input class="form-control bottom-margin" type="file" id="picture" path="file"
                                accept="image/*"/>
                    <button class="btn btn-default" type="submit" title="Post">Post</button>
                </form:form>
            </div>
        </div>
        <div class="collapse in col-xs-12 col-sm-4 collapsible-view" id="posts">
            <div>
                <h2 class="bottom-border">Posts:</h2>
                <c:forEach items="${posts}" var="p">
                    <div class="well well-sm">
                        <div>
                            <a class="btn btn-link friend-link post-owner" href="/profile?user=${p.owner.username}">
                                    ${p.owner.firstName} ${p.owner.lastName}
                            </a><br>
                                ${formatter.format(p.created)}
                            <c:if test="${p.text != null}">
                                <h4>${p.text}</h4>
                            </c:if>
                            <c:if test="${p.picture != null}">
                                <img class="post-picture bottom-padding" src="${p.picture}" alt="Post Picture">
                            </c:if>
                        </div>
                        <div>
                            <button class="btn btn-default" title="Like"
                                    onclick="window.location.href='/${p.liked ? 'un' : ''}like?postId=${p.id}'"><span
                                    class="glyphicon glyphicon-star${p.liked ? '' : '-empty'}"></span><span
                                    class="badge">${p.likes.size()}</span>
                            </button>
                            <button class="btn btn-default" title="Comment" data-toggle="modal"
                                    data-target="#post-comments${p.id}"><span
                                    class="glyphicon glyphicon-list-alt"></span> Comments <span
                                    class="badge">${p.comments.size()}</span>
                            </button>
                            <div id="post-comments${p.id}" class="modal fade" role="dialog">
                                <div class="modal-dialog modal-content">
                                    <div class="modal-header">
                                        <button class="close" title="Close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <c:forEach items="${p.comments}" var="c">
                                            <div class="well well-sm">
                                                <a class="btn btn-link friend-link post-owner"
                                                   href="/profile?user=${c.owner.username}">
                                                        ${c.owner.firstName} ${c.owner.lastName}
                                                </a><br>
                                                    ${formatter.format(c.created)}
                                                <h4>${c.text}</h4>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="modal-footer">
                                        <form:form class="form-horizontal" action="/new-comment" method="post"
                                                   modelAttribute="comment">
                                            <form:input cssClass="hidden" path="postId" value="${p.id}"/>
                                            <form:label class="sr-only" path="text">Comment text</form:label>
                                            <form:textarea class="form-control bottom-margin" path="text"
                                                           placeholder="Type your comment..."/>
                                            <button class="btn btn-default" type="submit" title="Post">Post</button>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
                <%-- TODO: Pagination --%>
            </div>
        </div>
        <div class="collapse in col-xs-12 col-sm-3 collapsible-view" id="friends">
            <h2 class="bottom-border">Friends:</h2>
            <c:forEach items="${friends}" var="f">
                <div class="well well-sm">
                    <a class="btn btn-link friend-link" href="/profile?user=${f.username}">
                        <c:choose>
                            <c:when test="${f.picture == null || empty f.picture}">
                                <img class="friend-picture" src="/img/profile.png" alt="Friend's Picture">
                            </c:when>
                            <c:otherwise>
                                <img class="friend-picture" src="${f.picture}" alt="Friend's Picture">
                            </c:otherwise>
                        </c:choose>
                            ${f.firstName} ${f.lastName}</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
