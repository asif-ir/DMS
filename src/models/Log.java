package models;

import database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rawlooa on 7/20/2017.
 */
public class Log {
    Connection conn = ConnectionFactory.getConnection();

    public ResultSet getAll() {
        try {
            PreparedStatement ps = this.conn.prepareStatement("select username, page, time, done_at from logs order by done_at desc limit 200");
            ResultSet rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
