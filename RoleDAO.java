import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Role getRoleById(int roleId) {
        String query = "SELECT * FROM Role WHERE roleId = ?";
        Role role = null;
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, roleId);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    role = new Role(rs.getInt("roleId"), rs.getString("roleName"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public void updateRoleName(int roleId, String newName) {
        String query = "UPDATE Role SET roleName = ? WHERE roleId = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newName);
                stmt.setInt(2, roleId);
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRole(int roleId) {
        String query = "DELETE FROM Role WHERE roleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

