package menus;

import dao.RoleDAO;
import entity.Role;

import java.util.List;
import java.util.Scanner;

public class RoleMenu {

    private static final RoleDAO roleDAO = new RoleDAO();

    public static void handle(Scanner sc) {
        while (true) {
            System.out.println("\n--- Role Menu ---");
            System.out.println("1. Add Role");
            System.out.println("2. View Role by ID");
            System.out.println("3. Update Role");
            System.out.println("4. Delete Role");
            System.out.println("5. View All Roles");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addRole(sc);
                case 2 -> viewById(sc);
                case 3 -> updateRole(sc);
                case 4 -> deleteRole(sc);
                case 5 -> viewAllRoles();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addRole(Scanner sc) {
        System.out.print("Enter role name: ");
        String name = sc.nextLine();
        roleDAO.insert(new Role(0, name));
        System.out.println("Role added successfully.");
    }

    private static void viewById(Scanner sc) {
        System.out.print("Enter role ID: ");
        int id = sc.nextInt();
        Role r = roleDAO.getById(id);
        if (r != null) {
            System.out.println("Role ID: " + r.getRoleId());
            System.out.println("Role Name: " + r.getRoleName());
        } else {
            System.out.println("Role not found.");
        }
    }

    private static void updateRole(Scanner sc) {
        System.out.print("Enter role ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Role existing = roleDAO.getById(id);
        if (existing != null) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            existing.setRoleName(name);
            roleDAO.update(existing);
            System.out.println("Role updated successfully.");
        } else {
            System.out.println("Role not found.");
        }
    }

    private static void deleteRole(Scanner sc) {
        System.out.print("Enter role ID to delete: ");
        int id = sc.nextInt();
        roleDAO.delete(id);
        System.out.println("Role deleted successfully.");
    }

    private static void viewAllRoles() {
        List<Role> list = roleDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No roles found.");
        } else {
            System.out.println("All Roles:");
            for (Role r : list) {
                System.out.println(r.getRoleId() + " - " + r.getRoleName());
            }
        }
    }
}
