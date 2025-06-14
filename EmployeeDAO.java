import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Employee getEmployeeById(int empId) {
        String query = "SELECT * FROM Employee where empId = ?";
        Employee emp = null;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, empId);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    int id = rs.getInt("empId");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    int roleId = rs.getInt("roleId");
                    int depId = rs.getInt("depId");

                    Role role = new Role(roleId, "");
                    Department dept = new Department(depId, "");
                    emp = new Employee(id, name, age, gender, role, dept);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    public void updateEmployeeName(int empId, String newName) {
    String query = "UPDATE Employee SET name = ? WHERE empId = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, newName);
        stmt.setInt(2, empId);

        int rowsUpdated = stmt.executeUpdate();
        System.out.println(rowsUpdated + " employee(s) updated.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void deleteEmployee(int empId) {
    String query = "DELETE FROM Employee WHERE empId = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, empId);

        int rowsDeleted = stmt.executeUpdate();
        System.out.println(rowsDeleted + " employee(s) deleted.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
