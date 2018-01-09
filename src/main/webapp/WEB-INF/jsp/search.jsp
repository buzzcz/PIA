<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <title>Search Users</title>
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
            ${user.firstName} ${user.lastName}
        </a>
        <button class="btn btn-default" name="log-out" onclick="window.location.href='logout'" title="Log Out">Log
            Out
        </button>
    </div>
</nav>
<div class="container-fluid content full-height">
    <div class="row full-height">
        <div class="collapse in col-xs-12 col-sm-4 col-sm-offset-4 collapsible-view">
            <div>
                <h2 class="bottom-border">Search Users:</h2>
                <form:form class="form-inline" action="search" method="post"
                           modelAttribute="text">
                    <form:label class="sr-only" path="text">Search text</form:label>
                    <form:input class="form-control" path="text" placeholder="Type your search..."
                                value="${text.text}"/>
                    <button class="btn btn-default" type="submit" title="Post">Post</button>
                    <div>
                        <small><span class="glyphicon glyphicon-question-sign"></span> Hint: Empty search retrieves all
                            users.
                        </small>
                    </div>
                </form:form>
            </div>
            <div>
                <c:if test="${text.text != null}">
                    <h2 class="bottom-border">Results:</h2>
                    <c:choose>
                        <c:when test="${not empty result}">
                            <c:forEach items="${result}" var="r">
                                <div class="well well-sm">
                                    <a class="btn btn-link friend-link" href="profile?user=${r.username}">
                                        <c:choose>
                                            <c:when test="${r.picture == null || empty r.picture}">
                                                <img class="friend-picture"
                                                     src="${pageContext.request.contextPath}/img/profile.png"
                                                     alt="Friend's Picture">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="friend-picture" src="${r.picture}" alt="Friend's Picture">
                                            </c:otherwise>
                                        </c:choose>
                                            ${r.firstName} ${r.lastName}</a>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            No results found.
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
