<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.SparePart" %>
<%@ page import="java.sql.SQLException" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file = "_session_check.jsp"%>

<html>
<head>
    <title>Profile</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
</head>
<body>

<%@ include file = "_navbar.jsp"%>

<div class="container">

    <h1>
        <% if (Integer.parseInt(session.getAttribute("privilege").toString()) >= 1) { %>
            Inventory
        <% } else { %>
            Buy Spare Sparts
        <% } %>
    </h1><br>
    <div class="btn-group btn-group-justified">
        <% if (Integer.parseInt(session.getAttribute("privilege").toString()) >= 1) { %>
            <a href="add_stock.jsp" class="btn btn-success">Add Stock</a>
            <a href="sell_stock.jsp" class="btn btn-success">Sell Stock</a>
        <% } %>
        <% if (Integer.parseInt(session.getAttribute("privilege").toString()) == 2) { %>
            <a href="add_spare_part.jsp" class="btn btn-success">Add Spare Part</a>
            <a href="remove_spare_part.jsp" class="btn btn-success">Remove Spare Part</a>
            <a href="terminate_operator.jsp" class="btn btn-danger">Terminate Operator</a>
        <% } %>
    </div>
    <hr>

    <% if (Integer.parseInt(session.getAttribute("privilege").toString()) == 2) { %>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">Spare Parts</a></li>
        <li><a data-toggle="tab" href="#menu1">Logs</a></li>
    </ul>

    <div class="tab-content container">
        <div id="home" class="tab-pane fade in active">
            <%@ include file = "_spare_parts_table.jsp" %>
        </div>
        <div id="menu1" class="tab-pane fade">
            <%@ include file = "_logs_table.jsp" %>
        </div>
    </div>
    <% } %>

    <% if (Integer.parseInt(session.getAttribute("privilege").toString()) == 1) { %>
        <%@ include file = "_spare_parts_table.jsp" %>
    <% } %>

    <% if (Integer.parseInt(session.getAttribute("privilege").toString()) == 0) { %>
        <%@ include file = "_customer_profile.jsp" %>
    <% } %>

</div>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $("#after-name").remove();

    $("#name").change(function () {
        $("#after-name").remove();
        var name = $("#name option:selected").text().toLowerCase();

        console.log("Selected name" + name);

        $.post("/buy-spare-part-ajax",
            {
                name: name
            },
            function(data, status, xhr) {
                $("#name").after(data);
            });
    }).change();

    $("#theme").change(function () {
        var theme = 'navbar-' + $("#theme option:selected").text().toLowerCase();

        console.log(theme);
        $("nav").toggleClass("navbar-inverse navbar-default");

        $.post("/theme-ajax",
            {
                theme: theme
            },
            function(data, status, xhr) {
                console.log(data);
            });
    }).change();
</script>

</body>
</html>