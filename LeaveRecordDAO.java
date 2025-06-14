import java.sql.*;

public class LeaveRecordDAO {
    public void insertLeaveRecord(LeaveRecord leave) {
        String query = "INSERT INTO LeaveRecord (leaveId, totalDays, reason, empId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leave.getLeaveId());
            stmt.setInt(2, leave.getTotalDays());
            stmt.setString(3, leave.getReason());
            stmt.setInt(4, leave.getEmployee().getEmpId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LeaveRecord getLeaveRecordById(int leaveId) {
        String query = "SELECT * FROM LeaveRecord WHERE leaveId = ?";
        LeaveRecord leave = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leaveId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee emp = new EmployeeDAO().getEmployeeById(rs.getInt("empId"));
                leave = new LeaveRecord(rs.getInt("leaveId"), rs.getInt("totalDays"), rs.getString("reason"), emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leave;
    }

    public void updateLeaveReason(int leaveId, String newReason) {
        String query = "UPDATE LeaveRecord SET reason = ? WHERE leaveId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newReason);
            stmt.setInt(2, leaveId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLeaveRecord(int leaveId) {
        String query = "DELETE FROM LeaveRecord WHERE leaveId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leaveId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
