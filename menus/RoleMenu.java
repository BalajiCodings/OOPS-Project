import java.util.*;
import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;s

public class RoleMenu {
    public static void handle(Scanner sc) {
        RoleDAO dao = new RoleDAO();

        while (true) {
            System.out.println("\n--- Role Menu ---");
            System.out.println("1. Add Role");
            System.out.println("2. View Role by ID");
            System.out.println("3. Update Role Name");
            System.out.println("4. Delete Role");
            System.out.println("5. Back");
            System.out.println("6. List All Roles"); 
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Role ID: ");
                    int roleId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Role Name: ");
                    String roleName = sc.nextLine();
                    dao.insert(new Role(roleId, roleName));
                    break;

                case 2:
                    System.out.print("Role ID: ");
                    Role r = dao.getById(sc.nextInt());
                    if (r != null)
                        System.out.println("ID: " + r.getRoleId() + ", Name: " + r.getRoleName());
                    else
                        System.out.println("Not found.");
                    break;

                case 3:
                    System.out.print("Role ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    Role updatedRole = new Role(id, newName);
                    dao.update(updatedRole);
                    break;

                case 4:
                    System.out.print("Role ID: ");
                    dao.delete(sc.nextInt());
                    break;

                case 5:
                    return;


                case 6:
                    List<Role> allRoles = dao.getAll();
                    for (Role role : allRoles) {
                        System.out.println("ID: " + role.getRoleId() + ", Name: " + role.getRoleName());
                    }
                    break;


                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
