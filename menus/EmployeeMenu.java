package menus;

import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;
import java.util.*;

public class EmployeeMenu {
    private Scanner sc = new Scanner(System.in);
    private EmployeeDAO empDao = new EmployeeDAO();
    private RoleDAO roleDao = new RoleDAO();
    private DepartmentDAO depDao = new DepartmentDAO();

    public void show() {
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1: add(); break;
                case 2: viewAll(); break;
                case 3: update(); break;
                case 4: delete(); break;
                case 5: search(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void add() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.println("Available Roles:");
        for (Role r : roleDao.getAll()) System.out.println(r);
        System.out.print("Role ID: ");
        int roleId = sc.nextInt();
        sc.nextLine();
        System.out.println("Available Departments:");
        for (Department d : depDao.getAll()) System.out.println(d);
        System.out.print("Department ID: ");
        int depId = sc.nextInt();
        sc.nextLine();
        Role role = roleDao.getById(roleId);
        Department dep = depDao.getById(depId);
        if (role == null || dep == null) {
            System.out.println("Invalid role or department.");
            return;
        }
        empDao.insert(new Employee(0, name, age, gender, role, dep));
        System.out.println("Employee added.");
    }

    private void viewAll() {
        List<Employee> list = empDao.getAll();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("ID | Name | Age | Gender | Role | Department");
        for (Employee e : list) System.out.println(e);
    }

    private void update() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee e = empDao.getById(id);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.print("New Name (" + e.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) e.setName(name);
        System.out.print("New Age (" + e.getAge() + "): ");
        String ageStr = sc.nextLine();
        if (!ageStr.isEmpty()) e.setAge(Integer.parseInt(ageStr));
        System.out.print("New Gender (" + e.getGender() + "): ");
        String gender = sc.nextLine();
        if (!gender.isEmpty()) e.setGender(gender);
        System.out.println("Available Roles:");
        for (Role r : roleDao.getAll()) System.out.println(r);
        System.out.print("New Role ID (" + e.getRole().getRoleId() + "): ");
        String roleIdStr = sc.nextLine();
        if (!roleIdStr.isEmpty()) {
            Role role = roleDao.getById(Integer.parseInt(roleIdStr));
            if (role != null) e.setRole(role);
        }
        System.out.println("Available Departments:");
        for (Department d : depDao.getAll()) System.out.println(d);
        System.out.print("New Department ID (" + e.getDepartment().getDepId() + "): ");
        String depIdStr = sc.nextLine();
        if (!depIdStr.isEmpty()) {
            Department dep = depDao.getById(Integer.parseInt(depIdStr));
            if (dep != null) e.setDepartment(dep);
        }
        empDao.update(e);
        System.out.println("Employee updated.");
    }

    private void delete() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee e = empDao.getById(id);
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.print("Are you sure you want to delete " + e.getName() + "? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            empDao.delete(id);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private void search() {
        System.out.print("Enter name or department to search: ");
        String keyword = sc.nextLine();
        List<Employee> list = empDao.search(keyword);
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("ID | Name | Age | Gender | Role | Department");
        for (Employee e : list) System.out.println(e);
    }
}
