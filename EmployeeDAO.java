import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {
    public void insertEmployee(Employee emp) {
        String query = "INSERT INTO Employee (empId, name, age, gender, roleId, depId) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, emp.getEmpId());
            stmt.setString(2, emp.getName());
            stmt.setInt(3, emp.getAge());
            stmt.setString(4, emp.getGender());
            stmt.setInt(5, emp.getRole().getRoleId());
            stmt.setInt(6, emp.getDepartment().getDepId());

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " employee(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
