import java.sql.*;

public class PayrollDAO {
    public void insertPayroll(Payroll payroll) {
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
            e.printStackTrace();
        }
    }

    public Payroll getPayrollById(int payrollId) {
        String query = "SELECT * FROM Payroll WHERE payrollId = ?";
        Payroll payroll = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, payrollId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee emp = new EmployeeDAO().getEmployeeById(rs.getInt("empId"));
                payroll = new Payroll(rs.getInt("payrollId"), rs.getInt("month"), rs.getInt("year"), rs.getDouble("netSalary"), emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    public void updateNetSalary(int payrollId, double newSalary) {
        String query = "UPDATE Payroll SET netSalary = ? WHERE payrollId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, payrollId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayroll(int payrollId) {
        String query = "DELETE FROM Payroll WHERE payrollId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, payrollId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
