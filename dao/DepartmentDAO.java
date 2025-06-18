package dao;

import entity.Department;
import java.sql.*;
import java.util.*;

public class DepartmentDAO implements DAO<Department> {
    @Override
    public void insert(Department d) {
        String sql = "INSERT INTO department(depName) VALUES (?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getDepName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting department: " + e.getMessage());
        }
    }

    @Override
    public Department getById(int id) {
        String sql = "SELECT * FROM department WHERE depId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt("depId"), rs.getString("depName"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching department: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Department d) {
        String sql = "UPDATE department SET depName = ? WHERE depId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getDepName());
            ps.setInt(2, d.getDepId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating department: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM department WHERE depId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting department: " + e.getMessage());
        }
    }

    @Override
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(rs.getInt("depId"), rs.getString("depName")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching departments: " + e.getMessage());
        }
        return list;
    }
}
