
import java.sql.*;

public class DepartmentDAO {
    public void insertDepartment(Department dept) {
        String query = "INSERT INTO Department (depId, depName) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, dept.getDepId());
            stmt.setString(2, dept.getDepName());

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " department(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(int depId) {
        String query = "SELECT * FROM Department WHERE depId = ?";
        Department dept = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, depId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dept = new Department(rs.getInt("depId"), rs.getString("depName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dept;
    }

    public void updateDepartmentName(int depId, String newName) {
        String query = "UPDATE Department SET depName = ? WHERE depId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, depId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int depId) {
        String query = "DELETE FROM Department WHERE depId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, depId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
