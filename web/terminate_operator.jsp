<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="models.User" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Terminate Operator</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file = "_navbar.jsp"%>

<div class="container">

    <h1>Register</h1>


    <form method="post" action="/remove-user" class="col-md-6 col-md-offset-3" id="register-form">

        <%@ include file = "_message.jsp"%>

        <div class="form-group">
            <label for="username">Select Operator:</label>
            <select class="form-control" id="username" name="username">
                <%
                    try {
                        ResultSet rs = new User().getOperators();
                        while (rs.next()) {
                %>
                            <option><%= rs.getString("username") %></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </select>
        </div>

        <input type="submit" id="submitButton" class="btn btn-danger" value="Terminate Operator">
    </form>

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