<%@ page import="database.ConnectionFactory" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%
    if (session.getAttribute("username") == null) {
        request.setAttribute("message",
                "<p style='color : orange;'> Login to continue!</p>"
        );
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into logs (username, page, time) values (?, ?, ?)");

            ps.setString(1, session.getAttribute("username").toString());
            ps.setString(2, request.getRequestURI());
            ps.setLong(3, (System.currentTimeMillis() - session.getLastAccessedTime())/1000);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>