<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/messages.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/messages.js"></script>
    <title>Messages</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top container-fluid row">
    <div class="col-xs-9 col-sm-4">
        <a class="btn btn-link" href="feed" id="logo">Kiv<span class="logo-border">Book</span></a>
    </div>
    <div class="visible-xs col-xs-3 pull-right top-padding">
        <button class="btn btn-default glyphicon glyphicon-menu-hamburger" data-toggle="collapse"
                data-target=".collapsible-menu" aria-expanded="true" aria-controls="menu log-out-form"
                title="Menu"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 top-padding collapsible-menu" id="menu">
        <button class="btn btn-default glyphicon glyphicon-th-list" onclick="window.location.href='feed'"
                title="Posts"></button>
        <button class="btn btn-default glyphicon glyphicon-comment" onclick="window.location.href='messages'"
                title="Messages"></button>
        <button class="btn btn-default glyphicon glyphicon-search" onclick="window.location.href='search'"
                title="Search"></button>
    </div>
    <div class="collapse in col-xs-12 col-sm-4 collapsible-menu" id="log-out-form">
        <a class="btn btn-link" id="logged-user" href="profile">
            <c:choose>
                <c:when test="${user.picture == null || empty user.picture}">
                    <img id="user-picture" src="${pageContext.request.contextPath}/img/profile.png"
                         alt="Profile Picture">
                </c:when>
                <c:otherwise>
                    <img id="user-picture" src="${user.picture}" alt="Profile Picture">
                </c:otherwise>
            </c:choose>
            ${user.firstName} ${user.lastName}</a>
        <button class="btn btn-default" name="log-out" onclick="window.location.href='logout'" title="Log Out">Log
            Out
        </button>
    </div>
</nav>
<div class="container-fluid full-height">
    <div class="row content full-height">
        <div class="visible-xs col-xs-12" id="toggle-buttons">
            <button class="btn btn-default glyphicon glyphicon-user" id="conversations-toggle"
                    onclick="showView('conversations')" title="Last Conversations"></button>
            <button class="btn btn-default glyphicon glyphicon-comment" id="messages-toggle"
                    onclick="showView('messages')" title="Messages"></button>
        </div>
        <div class="collapse in col-xs-12 col-sm-3 col-sm-offset-1 collapsible-view" id="conversations">
            <h2 class="bottom-border">Last Conversations:</h2>
            <c:forEach items="${conversations}" var="c">
                <div class="well well-sm ${c.selected ? 'selected' : ''}">
                    <a class="btn btn-link friend-link" href="messages?user=${c.username}">
                        <c:choose>
                            <c:when test="${c.picture == null || empty c.picture}">
                                <img class="friend-picture" src="${pageContext.request.contextPath}/img/profile.png"
                                     alt="Friend's Picture">
                            </c:when>
                            <c:otherwise>
                                <img class="friend-picture" src="${c.picture}" alt="Friend's Picture">
                            </c:otherwise>
                        </c:choose>
                            ${c.firstName} ${c.lastName}</a>
                </div>
            </c:forEach>
        </div>
        <div class="collapse in col-xs-12 col-sm-7 collapsible-view" id="messages">
		<h2 class="bottom-border">${c.firstName} ${c.lastName}:</h2>
            <div class="well well-sm">
                <c:forEach items="${messages}" var="m">
                    <div class="well well-sm message-${m.owner.username == user.username ? 'to' : 'from'}">
                        <small>${formatter.format(m.created)}</small>
                        <br>
                            ${m.text}
                    </div>
                </c:forEach>
            </div>
            <div>
                <form:form class="form-horizontal" action="new-message" method="post" modelAttribute="message">
                    <form:input class="hidden" path="conversationId" value="${conversationId}"/>
                    <form:label class="sr-only" path="text">Message</form:label>
                    <form:textarea class="form-control bottom-margin" id="message" path="text"
                                   placeholder="Type your message..."/>
                    <button class="btn btn-default" type="submit" title="Send">Send</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
