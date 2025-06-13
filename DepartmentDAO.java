import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
