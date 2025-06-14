import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Departments");
            System.out.println("3. Manage Roles");
            System.out.println("4. Manage Attendance");
            System.out.println("5. Manage Leave Records");
            System.out.println("6. Manage Payroll");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: EmployeeMenu.handle(sc); break;
                case 2: DepartmentMenu.handle(sc); break;
                case 3: RoleMenu.handle(sc); break;
                case 4: AttendanceMenu.handle(sc); break;
                case 5: LeaveRecordMenu.handle(sc); break;
                case 6: PayrollMenu.handle(sc); break;
                case 7: 
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
