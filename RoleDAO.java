import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDAO {
    public void insertRole(Role role) {
        String query = "INSERT INTO Role (roleId, roleName) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, role.getRoleId());
                stmt.setString(2, role.getRoleName());

                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted + " role(s) inserted.");

        } catch (SQLException e){
            e.printStackTrace();
        }

        
    }
}

