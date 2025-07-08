package menus;

import dao.UserDAO;
import java.util.Scanner;

public class AuthMenu {

    private final UserDAO userDAO = new UserDAO();
    private final Scanner scanner = new Scanner(System.in);

    public boolean login() {
        System.out.println("=== Employee Management System ===");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = userDAO.authenticate(username, password);

        if (isAuthenticated) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }
}
