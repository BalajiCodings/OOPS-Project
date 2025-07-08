package menus;

import dao.DepartmentDAO;
import entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentMenu {

    private static final DepartmentDAO departmentDAO = new DepartmentDAO();

    public static void handle(Scanner sc) {
        while (true) {
            System.out.println("\n--- Department Menu ---");
            System.out.println("1. Add Department");
            System.out.println("2. View Department by ID");
            System.out.println("3. Update Department");
            System.out.println("4. Delete Department");
            System.out.println("5. View All Departments");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addDepartment(sc);
                case 2 -> viewById(sc);
                case 3 -> updateDepartment(sc);
                case 4 -> deleteDepartment(sc);
                case 5 -> viewAllDepartments();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addDepartment(Scanner sc) {
        System.out.print("Enter department name: ");
        String name = sc.nextLine();
        departmentDAO.insert(new Department(0, name));
        System.out.println("Department added successfully.");
    }

    private static void viewById(Scanner sc) {
        System.out.print("Enter department ID: ");
        int id = sc.nextInt();
        Department d = departmentDAO.getById(id);
        if (d != null) {
            System.out.println("Department ID: " + d.getDepId());
            System.out.println("Department Name: " + d.getDepName());
        } else {
            System.out.println("Department not found.");
        }
    }

    private static void updateDepartment(Scanner sc) {
        System.out.print("Enter department ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Department existing = departmentDAO.getById(id);
        if (existing != null) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            existing.setDepName(name);
            departmentDAO.update(existing);
            System.out.println("Department updated successfully.");
        } else {
            System.out.println("Department not found.");
        }
    }

    private static void deleteDepartment(Scanner sc) {
        System.out.print("Enter department ID to delete: ");
        int id = sc.nextInt();
        departmentDAO.delete(id);
        System.out.println("Department deleted successfully.");
    }

    private static void viewAllDepartments() {
        List<Department> list = departmentDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            System.out.println("All Departments:");
            for (Department d : list) {
                System.out.println(d.getDepId() + " - " + d.getDepName());
            }
        }
    }
}
