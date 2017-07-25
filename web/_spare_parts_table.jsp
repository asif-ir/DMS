<%@ page import="java.sql.SQLException" %>
<%@ page import="models.SparePart" %>
<%@ page import="java.sql.ResultSet" %>
<table class="table table-striped">
    <legend>Spare Parts Inventory</legend>
    <thead>
    <tr>
        <th>Part Name</th>
        <th>Model</th>
        <th>Units Available</th>
        <th>Price Per Unit</th>
    </tr>
    </thead>
    <tbody>
    <%
        try {
            ResultSet rs = new SparePart().getAll();
            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("name")%></td>
        <td><%= rs.getString("model")%></td>
        <td><%= rs.getInt("units")%></td>
        <td><%= rs.getDouble("Price")%></td>
    </tr>
    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
    </tbody>
</table>