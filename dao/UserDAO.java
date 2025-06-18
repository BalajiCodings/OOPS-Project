package dao;

import java.sql.*;

public class UserDAO {
    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return false;
        }
    }
}