import java.util.Scanner;
import menus.AuthMenu;
import menus.EmployeeMenu;

public class Main {
    public static void main(String[] args) {
        AuthMenu authMenu = new AuthMenu();
        if (!authMenu.login()) return;

        Scanner sc = new Scanner(System.in);
        EmployeeMenu empMenu = new EmployeeMenu();
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Employee Management");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1: empMenu.show(); break;
                case 0: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
