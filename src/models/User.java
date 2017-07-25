package models;

import database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rawlooa on 7/17/2017.
 */
public class User {
    Connection conn = ConnectionFactory.getConnection();

    public ResultSet getOperators() {
        try {
            PreparedStatement ps = this.conn.prepareStatement("select username from users where privilege=1");
            ResultSet rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean removeUser(String username) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from users where username=?");

            ps.setString(1, username);

            int rows_affected = ps.executeUpdate();
            return (rows_affected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
