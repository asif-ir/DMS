<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Log" %>
<table class="table table-striped">
    <legend>Recent Logs</legend>
    <thead>
    <tr>
        <th>User</th>
        <th>Page Accessed</th>
        <th>Seconds Spent</th>
        <th>Done At</th>
    </tr>
    </thead>
    <tbody>
    <%
        try {
            ResultSet rs = new Log().getAll();
            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("username")%></td>
        <td><%= rs.getString("page")%></td>
        <td><%= rs.getInt("time")%></td>
        <td><%= rs.getString("done_at")%></td>
    </tr>
    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
    </tbody>
</table>