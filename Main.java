import java.util.Scanner;
import menus.AttendanceMenu;
import menus.AuthMenu;
import menus.DepartmentMenu;
import menus.EmployeeMenu;
import menus.LeaveRecordMenu;
import menus.PayrollMenu;
import menus.RoleMenu;

public class Main {
    public static void main(String[] args) {
        AuthMenu authMenu = new AuthMenu();
        if (!authMenu.login()) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        EmployeeMenu empMenu = new EmployeeMenu();

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Employee Management");
            System.out.println("2. Department Management");
            System.out.println("3. Role Management");
            System.out.println("4. Payroll Management");
            System.out.println("5. Leave Record Management");
            System.out.println("6. Attendance Management");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (ch) {
                case 1 -> empMenu.show();
                case 2 -> DepartmentMenu.handle(sc);
                case 3 -> RoleMenu.handle(sc);
                case 4 -> PayrollMenu.handle(sc);
                case 5 -> LeaveRecordMenu.handle(sc);
                case 6 -> AttendanceMenu.handle(sc);
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
