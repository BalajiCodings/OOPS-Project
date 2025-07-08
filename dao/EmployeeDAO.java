package dao;

import entity.Department;
import entity.Employee;
import entity.Role;
import java.sql.*;
import java.util.*;

public class EmployeeDAO implements DAO<Employee> {

    @Override
    public void insert(Employee e) {
        String sql = "INSERT INTO employee(name, age, gender, roleId, depId) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getGender());
            ps.setInt(4, e.getRole().getRoleId());
            ps.setInt(5, e.getDepartment().getDepId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error inserting employee: " + ex.getMessage());
        }
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employee WHERE empId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = new RoleDAO().getById(rs.getInt("roleId"));
                Department dep = new DepartmentDAO().getById(rs.getInt("depId"));
                return new Employee(
                    rs.getInt("empId"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    role,
                    dep
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employee: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Employee e) {
        String sql = "UPDATE employee SET name = ?, age = ?, gender = ?, roleId = ?, depId = ? WHERE empId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getGender());
            ps.setInt(4, e.getRole().getRoleId());
            ps.setInt(5, e.getDepartment().getDepId());
            ps.setInt(6, e.getEmpId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE empId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Role role = new RoleDAO().getById(rs.getInt("roleId"));
                Department dep = new DepartmentDAO().getById(rs.getInt("depId"));
                list.add(new Employee(
                    rs.getInt("empId"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    role,
                    dep
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
        return list;
    }

    public List<Employee> search(String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = """
            SELECT * FROM employee e
            JOIN department d ON e.depId = d.depId
            WHERE e.name LIKE ? OR d.depName LIKE ?
        """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new RoleDAO().getById(rs.getInt("roleId"));
                Department dep = new DepartmentDAO().getById(rs.getInt("depId"));
                list.add(new Employee(
                    rs.getInt("empId"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    role,
                    dep
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employees: " + e.getMessage());
        }
        return list;
    }
}
