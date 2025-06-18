import java.util.*;
import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;

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
            System.out.println("6. List All Departments"); 
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Dept ID: ");
                    int depId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Dept Name: ");
                    String depName = sc.nextLine();
                    dao.insert(new Department(depId, depName));
                    break;

                case 2:
                    System.out.print("Dept ID: ");
                    int viewId = sc.nextInt();
                    Department d = dao.getById(viewId);
                    if (d != null)
                        System.out.println("ID: " + d.getDepId() + ", Name: " + d.getDepName());
                    else
                        System.out.println("Not found.");
                    break;

                case 3:
                    System.out.print("Dept ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); 
                    Department oldDept = dao.getById(updateId);
                    if (oldDept == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    Department updatedDept = new Department(updateId, newName);
                    dao.update(updatedDept);
                    break;

                case 4:
                    System.out.print("Dept ID: ");
                    int deleteId = sc.nextInt();
                    dao.delete(deleteId);
                    break;

                case 5:
                    return;

                case 6:
                    List<Department> allDeps = dao.getAll();
                    for (Department dep : allDeps) {
                        System.out.println("ID: " + dep.getDepId() + ", Name: " + dep.getDepName());
                    }
                    break;


                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
