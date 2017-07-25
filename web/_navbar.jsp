<%@ page import="listeners.SessionCounterListener" %>

<%
    Cookie[] cookies = request.getCookies();
    String theme = "navbar-default";

    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("theme")) {
            theme = cookie.getValue();
            break;
        }
    }
%>

<nav class="navbar <%= theme %>">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">CDK Spare Parts</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="profile.jsp">Inventory</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a>
                    <em>
                        Last page access time:  <%= (System.currentTimeMillis() - session.getLastAccessedTime())/1000 %> seconds
                    </em>
                </a>
            </li>
            <% if (session.getAttribute("username") == null) { %>
                <li><a href="index.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <% } else { %>
                <li><a href="sign_up_ajax.jsp">Register</a></li>
                <li>
                    <a href="/logout">
                        <span class="glyphicon glyphicon-user"></span> Logout, <%= session.getAttribute("username").toString().toUpperCase()%>
                    </a>
                </li>
            <% } %>
        </ul>
    </div>
</nav>

<div class="col-md-1 col-md-offset-11">
    <label for="theme">Theme</label>
    <select class="form-control" id="theme" name="theme">
        <option <% if (theme.equals("navbar-default")) { %> selected <% }%>>Default</option>
        <option <% if (theme.equals("navbar-inverse")) { %> selected <% }%>>Inverse</option>
    </select>
</div>
