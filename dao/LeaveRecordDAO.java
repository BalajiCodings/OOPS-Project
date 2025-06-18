package dao;

import java.sql.*;
import java.util.*;
import entity.LeaveRecord;
import entity.Employee;

public class LeaveRecordDAO implements DAO<LeaveRecord> {

    @Override
    public void insert(LeaveRecord leave) {
        String query = "INSERT INTO LeaveRecord (leaveId, totalDays, reason, empId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leave.getLeaveId());
            stmt.setInt(2, leave.getTotalDays());
            stmt.setString(3, leave.getReason());
            stmt.setInt(4, leave.getEmployee().getEmpId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting leave record: " + e.getMessage());
        }
    }

    @Override
    public LeaveRecord getById(int leaveId) {
        String query = "SELECT * FROM LeaveRecord WHERE leaveId = ?";
        LeaveRecord leave = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leaveId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));
                leave = new LeaveRecord(rs.getInt("leaveId"), rs.getInt("totalDays"), rs.getString("reason"), emp);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching leave record: " + e.getMessage());
        }
        return leave;
    }

    @Override
    public void update(LeaveRecord leave) {
        String query = "UPDATE LeaveRecord SET totalDays = ?, reason = ?, empId = ? WHERE leaveId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leave.getTotalDays());
            stmt.setString(2, leave.getReason());
            stmt.setInt(3, leave.getEmployee().getEmpId());
            stmt.setInt(4, leave.getLeaveId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating leave record: " + e.getMessage());
        }
    }

    @Override
    public void delete(int leaveId) {
        String query = "DELETE FROM LeaveRecord WHERE leaveId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, leaveId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting leave record: " + e.getMessage());
        }
    }

    @Override
    public List<LeaveRecord> getAll() {
        List<LeaveRecord> list = new ArrayList<>();
        String query = "SELECT * FROM LeaveRecord";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int leaveId = rs.getInt("leaveId");
                int totalDays = rs.getInt("totalDays");
                String reason = rs.getString("reason");
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));
                list.add(new LeaveRecord(leaveId, totalDays, reason, emp));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all leave records: " + e.getMessage());
        }
        return list;
    }
}