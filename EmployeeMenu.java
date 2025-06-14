import java.util.Scanner;

public class EmployeeMenu {
    public static void handle(Scanner sc) {
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee by ID");
            System.out.println("3. Update Employee Name");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Emp ID: "); int empId = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
                    System.out.print("Gender: "); String gender = sc.nextLine();
                    System.out.print("Role ID: "); int roleId = sc.nextInt();
                    System.out.print("Dept ID: "); int depId = sc.nextInt();

                    Role role = new Role(roleId, "");
                    Department dept = new Department(depId, "");
                    Employee emp = new Employee(empId, name, age, gender, role, dept);
                    dao.insertEmployee(emp);
                    break;
                case 2:
                    System.out.print("Emp ID: ");
                    Employee e = dao.getEmployeeById(sc.nextInt());
                    if (e != null) {
                        System.out.println("ID: " + e.getEmpId() + ", Name: " + e.getName());
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 3:
                    System.out.print("Emp ID: "); int eid = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: "); String newName = sc.nextLine();
                    dao.updateEmployeeName(eid, newName);
                    break;
                case 4:
                    System.out.print("Emp ID to delete: ");
                    dao.deleteEmployee(sc.nextInt());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
