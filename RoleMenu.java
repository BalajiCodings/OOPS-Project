import java.util.Scanner;

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
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Role ID: "); int roleId = sc.nextInt(); sc.nextLine();
                    System.out.print("Role Name: "); String roleName = sc.nextLine();
                    dao.insertRole(new Role(roleId, roleName));
                    break;
                case 2:
                    System.out.print("Role ID: ");
                    Role r = dao.getRoleById(sc.nextInt());
                    if (r != null) System.out.println("ID: " + r.getRoleId() + ", Name: " + r.getRoleName());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Role ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: "); String newName = sc.nextLine();
                    dao.updateRoleName(id, newName);
                    break;
                case 4:
                    System.out.print("Role ID: "); dao.deleteRole(sc.nextInt());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
