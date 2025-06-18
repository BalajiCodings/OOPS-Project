package dao;

import entity.Role;
import java.sql.*;
import java.util.*;

public class RoleDAO implements DAO<Role> {
    @Override
    public void insert(Role r) {
        String sql = "INSERT INTO role(roleName) VALUES (?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getRoleName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting role: " + e.getMessage());
        }
    }

    @Override
    public Role getById(int id) {
        String sql = "SELECT * FROM role WHERE roleId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Role(rs.getInt("roleId"), rs.getString("roleName"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching role: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Role r) {
        String sql = "UPDATE role SET roleName = ? WHERE roleId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getRoleName());
            ps.setInt(2, r.getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating role: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM role WHERE roleId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting role: " + e.getMessage());
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM role";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Role(rs.getInt("roleId"), rs.getString("roleName")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching roles: " + e.getMessage());
        }
        return list;
    }
}