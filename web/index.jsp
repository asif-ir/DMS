<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  if (session.getAttribute("username") != null) {
    request.getRequestDispatcher("profile.jsp").forward(request, response);
  }
%>

<html>
<head>
  <title>Welcome</title>

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file = "_navbar.jsp"%>

<div class="container">

  <h1>Login</h1>

  <form action="/login" method="post" class="col-md-6 col-md-offset-3">

    <%@ include file = "_message.jsp"%>

    <div class="form-group">
      <label for="userName">Name:</label>
      <input type="text" class="form-control" id="userName" name="userName">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" name="password">
    </div>
    <input type="submit" class="btn btn-info" value="Login">
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