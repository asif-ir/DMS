<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file = "_navbar.jsp"%>

<div class="container">

    <h1>Register</h1>


    <form method="post" action="/sign-up" class="col-md-6 col-md-offset-3" id="register-form">

        <%@ include file = "_message.jsp"%>

        <div class="form-group">
            <label for="userName">Name:</label>
            <input type="text" class="form-control" id="userName" name="userName" required>
            <strong><span style="color:red;" id="nameError"></span></strong>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" minlength="4">
        </div>

        <% if (Integer.parseInt(session.getAttribute("privilege").toString()) == 2) { %>
        <label>Privilege Level: </label>
        <div class="radio">
            <label><input type="radio" name="privilege" value="0"> Customer</label>
        </div>
        <div class="radio">
            <label><input type="radio" name="privilege" value="1" checked> Operator</label>
        </div>
        <% } %>

        <input type="submit" id="submitButton" class="btn btn-success" value="Register">
    </form>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script>
        $("#userName").blur(function(event) {
            $.post("/sign-up-ajax",
                {
                    userName: $("#userName").val()
                },
                function(data, status, xhr){
                    $("#nameError").text("");
                    $("#submitButton").prop("disabled", false);

                    if (data == "exists") {
                        nameError = "User already exists";
                        $("#nameError").text(nameError);
                        $("#submitButton").prop("disabled", "disabled");
                    }
                });
        });

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
</div>

</body>
</html>