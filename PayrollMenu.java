import java.util.Scanner;

public class PayrollMenu {
    public static void handle(Scanner sc) {
        PayrollDAO dao = new PayrollDAO();
        while (true) {
            System.out.println("\n--- Payroll Menu ---");
            System.out.println("1. Add Payroll");
            System.out.println("2. View Payroll by ID");
            System.out.println("3. Delete Payroll");
            System.out.println("4. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Payroll ID: "); int pid = sc.nextInt();
                    System.out.print("Employee ID: "); int empId = sc.nextInt();
                    System.out.print("Month: "); int month = sc.nextInt();
                    System.out.print("Year: "); int year = sc.nextInt();
                    System.out.print("Net Salary: "); double salary = sc.nextDouble();
                    Employee emp = new EmployeeDAO().getEmployeeById(empId);
                    dao.insertPayroll(new Payroll(pid, month, year, salary, emp));
                    break;
                case 2:
                    System.out.print("Payroll ID: ");
                    Payroll p = dao.getPayrollById(sc.nextInt());
                    if (p != null) System.out.println("ID: " + p.getPayrollId() + ", Month: " + p.getMonth() + ", Salary: " + p.getNetSalary());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Payroll ID: "); dao.deletePayroll(sc.nextInt());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
