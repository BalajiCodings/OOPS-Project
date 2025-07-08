package dao;

import entity.Employee;
import entity.Payroll;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO implements DAO<Payroll> {

    @Override
    public void insert(Payroll payroll) {
        String query = "INSERT INTO Payroll (payrollId, month, year, netSalary, empId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payroll.getPayrollId());
            stmt.setInt(2, payroll.getMonth());
            stmt.setInt(3, payroll.getYear());
            stmt.setDouble(4, payroll.getNetSalary());
            stmt.setInt(5, payroll.getEmployee().getEmpId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting payroll: " + e.getMessage());
        }
    }

    @Override
    public Payroll getById(int payrollId) {
        String query = "SELECT * FROM Payroll WHERE payrollId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payrollId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double netSalary = rs.getDouble("netSalary");
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));

                return new Payroll(payrollId, month, year, netSalary, emp);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching payroll: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Payroll payroll) {
        String query = "UPDATE Payroll SET month = ?, year = ?, netSalary = ?, empId = ? WHERE payrollId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payroll.getMonth());
            stmt.setInt(2, payroll.getYear());
            stmt.setDouble(3, payroll.getNetSalary());
            stmt.setInt(4, payroll.getEmployee().getEmpId());
            stmt.setInt(5, payroll.getPayrollId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating payroll: " + e.getMessage());
        }
    }

    @Override
    public void delete(int payrollId) {
        String query = "DELETE FROM Payroll WHERE payrollId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payrollId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting payroll: " + e.getMessage());
        }
    }

    @Override
    public List<Payroll> getAll() {
        List<Payroll> list = new ArrayList<>();
        String query = "SELECT * FROM Payroll";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int payrollId = rs.getInt("payrollId");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double netSalary = rs.getDouble("netSalary");
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));

                list.add(new Payroll(payrollId, month, year, netSalary, emp));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching payroll records: " + e.getMessage());
        }

        return list;
    }
}
