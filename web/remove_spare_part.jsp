<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file = "_session_check.jsp"%>

<html>
<head>
    <title>Remove Spare Part</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file = "_navbar.jsp"%>

<div class="container">

    <h1>Remove Spare Part</h1>

    <form action="/remove-spare-part" method="post" class="col-md-6 col-md-offset-3">

        <%@ include file = "_message.jsp"%>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="model">Model:</label>
            <input type="text" class="form-control" id="model" name="model">
        </div>
        <input type="submit" class="btn btn-danger" value="Remove Spare Part">
    </form>

</div>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
    $("#theme").change(function () {
        var theme = 'navbar-' + $( "#theme option:selected" ).text().toLowerCase();

        console.log(theme);
        $("nav").toggleClass("navbar-inverse navbar-default");

        $.post("/theme-ajax",
            {
                theme: theme
            },
            function(data, status, xhr){
                console.log(data);
            });
    }).change();
</script>

</body>
</html>