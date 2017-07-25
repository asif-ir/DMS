<form action="/buy-spare-part" method="post" class="col-md-6 col-md-offset-3">

    <%@ include file = "_message.jsp"%>

    <%
        ResultSet rs = new SparePart().getAll();
    %>

    <div class="form-group">
        <label for="name">Select Name:</label>
        <select class="form-control" name="name" id="name" >
            <%
                try {
                    while (rs.next()) {
            %>
            <option><%= rs.getString("name") %></option>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </select>
    </div>

    <div class="form-group">
        <label for="units">Units:</label>
        <input type="number" class="form-control" id="units" name="units" required>
    </div>
    <input type="submit" class="btn btn-success" value="Buy">
    <%
        String bill_button = (String) request.getAttribute("bill_button");
        if (bill_button != null) {
            out.print(bill_button);
        }
    %>
</form>