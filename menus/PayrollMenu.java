import java.util.*;
import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;

public class PayrollMenu {
    public static void handle(Scanner sc) {
        PayrollDAO dao = new PayrollDAO();

        while (true) {
            System.out.println("\n--- Payroll Menu ---");
            System.out.println("1. Add Payroll");
            System.out.println("2. View Payroll by ID");
            System.out.println("3. Delete Payroll");
            System.out.println("4. Back");
            System.out.println("5. List All Payrolls");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Payroll ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();
                    System.out.print("Month (1-12): ");
                    int month = sc.nextInt();
                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    System.out.print("Net Salary: ");
                    double salary = sc.nextDouble();

                    Employee emp = new EmployeeDAO().getById(empId);
                    if (emp == null) {
                        System.out.println("Invalid Employee ID.");
                        break;
                    }

                    Payroll payroll = new Payroll(pid, month, year, salary, emp);
                    dao.insert(payroll);
                    break;

                case 2:
                    System.out.print("Payroll ID: ");
                    Payroll p = dao.getById(sc.nextInt());
                    if (p != null) {
                        System.out.println("ID: " + p.getPayrollId() +
                                           ", Employee: " + p.getEmployee().getName() +
                                           ", Month: " + p.getMonth() +
                                           ", Year: " + p.getYear() +
                                           ", Salary: " + p.getNetSalary());
                    } else {
                        System.out.println("Not found.");
                    }
                    break;

                case 3:
                    System.out.print("Payroll ID: ");
                    dao.delete(sc.nextInt());
                    break;

                case 4:
                    return;

                case 5:
                    List<Payroll> allPayrolls = dao.getAll();
                    for (Payroll p1 : allPayrolls) {
                        System.out.println("ID: " + p1.getPayrollId() +
                        ", Month/Year: " + p1.getMonth() + "/" + p1.getYear() +
                        ", Net Salary: " + p1.getNetSalary() +
                        ", Employee: " + p1.getEmployee().getName());
                    }
                    break;


                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
