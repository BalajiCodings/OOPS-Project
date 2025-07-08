package dao;

import entity.Department;
import java.sql.*;
import java.util.*;

public class DepartmentDAO implements DAO<Department> {
    public void insert(Department d) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO department(depName) VALUES (?)")) {
            ps.setString(1, d.getDepName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }

    public Department getById(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM department WHERE depId = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new Department(rs.getInt("depId"), rs.getString("depName"));
        } catch (SQLException e) {
            System.out.println("Get Error: " + e.getMessage());
        }
        return null;
    }

    public void update(Department d) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE department SET depName=? WHERE depId=?")) {
            ps.setString(1, d.getDepName());
            ps.setInt(2, d.getDepId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM department WHERE depId=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM department")) {
            while (rs.next())
                list.add(new Department(rs.getInt("depId"), rs.getString("depName")));
        } catch (SQLException e) {
            System.out.println("Fetch All Error: " + e.getMessage());
        }
        return list;
    }
}