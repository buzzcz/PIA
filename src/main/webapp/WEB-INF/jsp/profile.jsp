<%@ page import="cz.zcu.kiv.pia.kivbook.dto.UserDto" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/css/common.css">
    <link type="text/css" rel="stylesheet" href="/css/profile.css">
    <script type="application/javascript" src="/js/jquery.min.js"></script>
    <script type="application/javascript" src="/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="/js/common.js"></script>
    <script type="application/javascript" src="/js/profile.js"></script>
    <title>User's Profile</title>
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
            ${user.firstName} ${user.lastName}</a>
        <button class="btn btn-default" name="log-out" onclick="window.location.href='/logout'" title="Log Out">Log
            Out
        </button>
    </div>
</nav>
<div class="container-fluid content full-height">
    <div class="row full-height">
        <div class="col-xs-12 col-sm-6 col-sm-offset-4" id="profile-info">
            <!-- TODO: Make the line not to break under the picture. -->
            <h1>
                <c:choose>
                    <c:when test="${profile.picture == null || empty profile.picture}">
                        <img class="profile-picture" id="profile-picture" src="/img/profile.png"
                             alt="User's Profile Picture">
                    </c:when>
                    <c:otherwise>
                        <img class="profile-picture" id="profile-picture" src="${profile.picture}"
                             alt="User's Profile Picture">
                    </c:otherwise>
                </c:choose>
                ${profile.firstName} ${profile.lastName}
            </h1>
        </div>
        <div class="visible-xs col-xs-12 bottom-padding" id="buttons">
            <button class="btn btn-default glyphicon glyphicon-briefcase" id="personal-info-toggle"
                    onclick="showView('personal-info')" title="Personal Info"></button>
            <button class="btn btn-default glyphicon glyphicon-th-list" id="posts-toggle"
                    onclick="showView('posts')" title="Posts"></button>
            <button class="btn btn-default glyphicon glyphicon-user" id="friends-toggle"
                    onclick="showView('friends')" title="Friends"></button>
        </div>
        <div class="collapse in col-xs-12 col-sm-3 col-sm-offset-1 collapsible-view" id="personal-info">
            <h2 class="bottom-border">Personal info:</h2>
            <table>
                <tr>
                    <td><h4>First Name:</h4></td>
                    <td class="info-row"><h4>${profile.firstName}</h4></td>
                </tr>
                <tr>
                    <td><h4>Last Name:</h4></td>
                    <td class="info-row"><h4>${profile.lastName}</h4></td>
                </tr>
                <tr>
                    <td><h4>Birthday:</h4></td>
                    <c:if test="${profile.birthday != null}">
                        <td class="info-row"><h4>
                            <%
                                out.print(((UserDto) request.getAttribute("profile")).getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                            %>
                        </h4></td>
                    </c:if>
                </tr>
                <tr>
                    <td><h4>Age:</h4></td>
                    <c:if test="${profile.birthday != null}">
                        <td class="info-row"><h4>
                            <%
                                out.print(ChronoUnit.YEARS.between(((UserDto) request.getAttribute("profile")).getBirthday(), LocalDate.now()));
                            %>
                        </h4>
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td><h4>Email:</h4></td>
                    <td class="info-row"><h4>${profile.email}</h4></td>
                </tr>
            </table>
            <c:choose>
                <c:when test="${friendship != null}">
                    <div class="top-padding">
                        <c:choose>
                            <c:when test="${friendship.ack}">
                                <button class="btn btn-default" name="add-friend" disabled>Already Friends <span
                                        class="glyphicon glyphicon-ok" title="Friendship Status"></span></button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-default" name="add-friend" disabled>Request Pending <span
                                        class="glyphicon glyphicon-time" title="Friendship Status"></span></button>
                                <c:if test="${profile.username != user.username && friendship.userId1 != user.id}">
                                    <button class="btn btn-default" name="ack-friend"
                                            onclick="window.location.href='/ack-friendship?user=${profile.username}'">
                                        Accept Friendship <span class="glyphicon glyphicon-ok"
                                                                title="Accept Friendship"></span></button>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="top-padding">
                        <button class="btn btn-default" name="new-message"
                                onclick="window.location.href='/messages?user=${profile.username}'">New Message
                            <span class="glyphicon glyphicon-send" title="New Message"></span></button>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${profile.username != user.username}">
                        <div class="top-padding">
                            <button class="btn btn-default" name="add-friend"
                                    onclick="window.location.href='/new-friendship?user=${profile.username}'">Send
                                Friendship Request <span class="glyphicon glyphicon-ok"
                                                         title="Friendship Status"></span></button>

                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
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
                                    class="glyphicon glyphicon-star${p.liked ? '' : '-empty'}"></span> <span
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
                                            <form:input class="hidden" path="postId" value="${p.id}"/>
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
