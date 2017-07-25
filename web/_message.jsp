<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.print("<strong>"+ message +"</strong>");
    }
%>