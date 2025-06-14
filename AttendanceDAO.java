import java.sql.*;
import java.time.LocalDate;

public class AttendanceDAO {
    public void insertAttendance(Attendance attendance) {
        String query = "INSERT INTO Attendance (attId, date, status, empId) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, attendance.getAttId());
                stmt.setDate(2, Date.valueOf(attendance.getDate()));
                stmt.setString(3, attendance.getStatus());
                stmt.setInt(4, attendance.getEmployee().getEmpId());

                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted + " attendance record(s) inserted.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Attendance getAttendanceById(int attId) {
        String query = "SELECT * FROM Attendance WHERE attId = ?";
        Attendance att = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int empId = rs.getInt("empId");
                Employee emp = new EmployeeDAO().getEmployeeById(empId);
                LocalDate date = rs.getDate("date").toLocalDate();

                att = new Attendance(rs.getInt("attId"), date, rs.getString("status"), emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return att;
    }

    public void updateStatus(int attId, String newStatus) {
        String query = "UPDATE Attendance SET status = ? WHERE attId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, attId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAttendance(int attId) {
        String query = "DELETE FROM Attendance WHERE attId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}