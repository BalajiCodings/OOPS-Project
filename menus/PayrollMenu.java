package menus;

import dao.EmployeeDAO;
import dao.PayrollDAO;
import entity.Employee;
import entity.Payroll;

import java.util.List;
import java.util.Scanner;

public class PayrollMenu {

    private static final PayrollDAO payrollDAO = new PayrollDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void handle(Scanner sc) {
        while (true) {
            System.out.println("\n--- Payroll Menu ---");
            System.out.println("1. Add Payroll");
            System.out.println("2. View Payroll by ID");
            System.out.println("3. Update Payroll");
            System.out.println("4. Delete Payroll");
            System.out.println("5. View All Payrolls");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addPayroll(sc);
                case 2 -> viewById(sc);
                case 3 -> updatePayroll(sc);
                case 4 -> deletePayroll(sc);
                case 5 -> viewAllPayrolls();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addPayroll(Scanner sc) {
        System.out.print("Enter Payroll ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Month (1-12): ");
        int month = sc.nextInt();
        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        System.out.print("Enter Net Salary: ");
        double salary = sc.nextDouble();
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();
        Employee emp = employeeDAO.getById(empId);
        if (emp != null) {
            payrollDAO.insert(new Payroll(id, month, year, salary, emp));
            System.out.println("Payroll record added.");
        } else {
            System.out.println("Invalid Employee ID.");
        }
    }

    private static void viewById(Scanner sc) {
        System.out.print("Enter Payroll ID: ");
        int id = sc.nextInt();
        Payroll p = payrollDAO.getById(id);
        if (p != null) {
            System.out.println("Payroll ID: " + p.getPayrollId());
            System.out.println("Month: " + p.getMonth());
            System.out.println("Year: " + p.getYear());
            System.out.println("Net Salary: " + p.getNetSalary());
            System.out.println("Employee ID: " + p.getEmployee().getEmpId());
        } else {
            System.out.println("Payroll not found.");
        }
    }

    private static void updatePayroll(Scanner sc) {
        System.out.print("Enter Payroll ID to update: ");
        int id = sc.nextInt();
        Payroll existing = payrollDAO.getById(id);
        if (existing != null) {
            System.out.print("Enter new Month: ");
            int month = sc.nextInt();
            System.out.print("Enter new Year: ");
            int year = sc.nextInt();
            System.out.print("Enter new Net Salary: ");
            double salary = sc.nextDouble();
            System.out.print("Enter new Employee ID: ");
            int empId = sc.nextInt();
            Employee emp = employeeDAO.getById(empId);
            if (emp != null) {
                existing.setMonth(month);
                existing.setYear(year);
                existing.setNetSalary(salary);
                existing.setEmployee(emp);
                payrollDAO.update(existing);
                System.out.println("Payroll updated.");
            } else {
                System.out.println("Invalid Employee ID.");
            }
        } else {
            System.out.println("Payroll not found.");
        }
    }

    private static void deletePayroll(Scanner sc) {
        System.out.print("Enter Payroll ID to delete: ");
        int id = sc.nextInt();
        payrollDAO.delete(id);
        System.out.println("Payroll deleted.");
    }

    private static void viewAllPayrolls() {
        List<Payroll> list = payrollDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No payroll records found.");
        } else {
            System.out.println("All Payroll Records:");
            for (Payroll p : list) {
                System.out.println(p.getPayrollId() + " | " + p.getMonth() + "/" + p.getYear() + " | ₹" +
                        p.getNetSalary() + " | EmpID: " + p.getEmployee().getEmpId());
            }
        }
    }
}
