package menus;

import dao.EmployeeDAO;
import dao.LeaveRecordDAO;
import entity.Employee;
import entity.LeaveRecord;

import java.util.List;
import java.util.Scanner;

public class LeaveRecordMenu {

    private static final LeaveRecordDAO leaveDAO = new LeaveRecordDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void handle(Scanner sc) {
        while (true) {
            System.out.println("\n--- Leave Record Menu ---");
            System.out.println("1. Add Leave Record");
            System.out.println("2. View Leave Record by ID");
            System.out.println("3. Update Leave Record");
            System.out.println("4. Delete Leave Record");
            System.out.println("5. View All Leave Records");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addLeave(sc);
                case 2 -> viewById(sc);
                case 3 -> updateLeave(sc);
                case 4 -> deleteLeave(sc);
                case 5 -> viewAllLeaves();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addLeave(Scanner sc) {
        System.out.print("Enter Leave ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Total Leave Days: ");
        int total = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Reason: ");
        String reason = sc.nextLine();
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();
        Employee emp = employeeDAO.getById(empId);
        if (emp != null) {
            leaveDAO.insert(new LeaveRecord(id, total, reason, emp));
            System.out.println("Leave record added.");
        } else {
            System.out.println("Invalid Employee ID.");
        }
    }

    private static void viewById(Scanner sc) {
        System.out.print("Enter Leave ID: ");
        int id = sc.nextInt();
        LeaveRecord l = leaveDAO.getById(id);
        if (l != null) {
            System.out.println("Leave ID: " + l.getLeaveId());
            System.out.println("Total Days: " + l.getTotalDays());
            System.out.println("Reason: " + l.getReason());
            System.out.println("Employee ID: " + l.getEmployee().getEmpId());
        } else {
            System.out.println("Leave record not found.");
        }
    }

    private static void updateLeave(Scanner sc) {
        System.out.print("Enter Leave ID to update: ");
        int id = sc.nextInt();
        LeaveRecord l = leaveDAO.getById(id);
        if (l != null) {
            System.out.print("Enter new Total Days: ");
            int total = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new Reason: ");
            String reason = sc.nextLine();
            System.out.print("Enter new Employee ID: ");
            int empId = sc.nextInt();
            Employee emp = employeeDAO.getById(empId);
            if (emp != null) {
                l.setTotalDays(total);
                l.setReason(reason);
                l.setEmployee(emp);
                leaveDAO.update(l);
                System.out.println("Leave record updated.");
            } else {
                System.out.println("Invalid Employee ID.");
            }
        } else {
            System.out.println("Leave record not found.");
        }
    }

    private static void deleteLeave(Scanner sc) {
        System.out.print("Enter Leave ID to delete: ");
        int id = sc.nextInt();
        leaveDAO.delete(id);
        System.out.println("Leave record deleted.");
    }

    private static void viewAllLeaves() {
        List<LeaveRecord> list = leaveDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No leave records found.");
        } else {
            System.out.println("All Leave Records:");
            for (LeaveRecord l : list) {
                System.out.println(l.getLeaveId() + " | " + l.getTotalDays() + " days | Reason: " +
                        l.getReason() + " | EmpID: " + l.getEmployee().getEmpId());
            }
        }
    }
}
