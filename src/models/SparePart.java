package models;

import database.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rawlooa on 7/17/2017.
 */
public class SparePart {
    Connection conn = ConnectionFactory.getConnection();

    public ResultSet getAll() {
        try {
            PreparedStatement ps = this.conn.prepareStatement("select name, model, units, price from spareparts");
            ResultSet rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getModels(String name) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("select distinct model from spareparts where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getPrice(String name, String model) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("select price from spareparts where name=? and model=?");
            ps.setString(1, name);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getDouble("price");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean addStock(String name, String model, int quantity) {
        try {
            PreparedStatement ps = conn.prepareStatement("select units from spareparts where name=? and model = ?");
            ps.setString(1, name);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            int t = 0;
            if (rs.next()) {
                t = rs.getInt(1) + quantity;
            }
            String sql = "update spareparts set units=? where name=? and model = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, t);
            ps.setString(2, name);
            ps.setString(3, model);
            int rows_affected = ps.executeUpdate();
            return (rows_affected > 0);
        } catch (SQLException|NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sellStock(String name, String model, int quantity) {
        try {
            PreparedStatement ps = conn.prepareStatement("select units from spareparts where name=? and model = ?");
            ps.setString(1, name);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            int t = 0;
            if (rs.next()) {
                t = rs.getInt(1) - quantity;
            }
            String sql = "update spareparts set units=? where name=? and model = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, t);
            ps.setString(2, name);
            ps.setString(3, model);

            int rows_affected = ps.executeUpdate();
            return (rows_affected > 0);
        } catch (SQLException|NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeSparePart(String name, String model) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from spareparts where name=? and model=?");

            ps.setString(1, name);
            ps.setString(2, model);

            int rows_affected = ps.executeUpdate();
            return (rows_affected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addSparePart(String name, String model, int units, double price) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into spareparts(name, model, units, price) values (?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, model);
            ps.setInt(3, units);
            ps.setDouble(4, price);

            int rows_affected = ps.executeUpdate();
            return (rows_affected > 0);
        } catch (SQLException|NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
