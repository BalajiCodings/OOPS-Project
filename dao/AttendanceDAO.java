package dao;

import entity.Attendance;
import entity.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO implements DAO<Attendance> {

    @Override
    public void insert(Attendance attendance) {
        String query = "INSERT INTO Attendance (attId, date, status, empId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, attendance.getAttId());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setString(3, attendance.getStatus());
            stmt.setInt(4, attendance.getEmployee().getEmpId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting attendance: " + e.getMessage());
        }
    }

    @Override
    public Attendance getById(int attId) {
        String query = "SELECT * FROM Attendance WHERE attId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, attId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String status = rs.getString("status");
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));

                return new Attendance(attId, date, status, emp);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching attendance: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Attendance attendance) {
        String query = "UPDATE Attendance SET date = ?, status = ?, empId = ? WHERE attId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(attendance.getDate()));
            stmt.setString(2, attendance.getStatus());
            stmt.setInt(3, attendance.getEmployee().getEmpId());
            stmt.setInt(4, attendance.getAttId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating attendance: " + e.getMessage());
        }
    }

    @Override
    public void delete(int attId) {
        String query = "DELETE FROM Attendance WHERE attId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, attId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting attendance: " + e.getMessage());
        }
    }

    @Override
    public List<Attendance> getAll() {
        List<Attendance> list = new ArrayList<>();
        String query = "SELECT * FROM Attendance";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int attId = rs.getInt("attId");
                LocalDate date = rs.getDate("date").toLocalDate();
                String status = rs.getString("status");
                Employee emp = new EmployeeDAO().getById(rs.getInt("empId"));

                list.add(new Attendance(attId, date, status, emp));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all attendance records: " + e.getMessage());
        }

        return list;
    }
}
