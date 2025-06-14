import java.util.Scanner;

public class DepartmentMenu {
    public static void handle(Scanner sc) {
        DepartmentDAO dao = new DepartmentDAO();
        while (true) {
            System.out.println("\n--- Department Menu ---");
            System.out.println("1. Add Department");
            System.out.println("2. View Department by ID");
            System.out.println("3. Update Department Name");
            System.out.println("4. Delete Department");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Dept ID: "); int depId = sc.nextInt(); sc.nextLine();
                    System.out.print("Dept Name: "); String depName = sc.nextLine();
                    dao.insertDepartment(new Department(depId, depName));
                    break;
                case 2:
                    System.out.print("Dept ID: ");
                    Department d = dao.getDepartmentById(sc.nextInt());
                    if (d != null) System.out.println("ID: " + d.getDepId() + ", Name: " + d.getDepName());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Dept ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: "); String newName = sc.nextLine();
                    dao.updateDepartmentName(id, newName);
                    break;
                case 4:
                    System.out.print("Dept ID: "); dao.deleteDepartment(sc.nextInt());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}