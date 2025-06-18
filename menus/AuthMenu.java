package menus;

import dao.UserDAO;
import java.util.Scanner;

public class AuthMenu {
    private Scanner sc = new Scanner(System.in);
    private UserDAO userDao = new UserDAO();

    public boolean login() {
        System.out.println("--- Login ---");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (userDao.authenticate(username, password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }
}