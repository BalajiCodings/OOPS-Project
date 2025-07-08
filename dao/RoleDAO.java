package dao;

import entity.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements DAO<Role> {

    @Override
    public void insert(Role role) {
        String sql = "INSERT INTO role(roleName) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, role.getRoleName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting role: " + e.getMessage());
        }
    }

    @Override
    public Role getById(int roleId) {
        String sql = "SELECT * FROM role WHERE roleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Role(roleId, rs.getString("roleName"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching role: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Role role) {
        String sql = "UPDATE role SET roleName = ? WHERE roleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating role: " + e.getMessage());
        }
    }

    @Override
    public void delete(int roleId) {
        String sql = "DELETE FROM role WHERE roleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting role: " + e.getMessage());
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                roles.add(new Role(rs.getInt("roleId"), rs.getString("roleName")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching roles: " + e.getMessage());
        }
        return roles;
    }
}
