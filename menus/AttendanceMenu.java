package menus;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import entity.Attendance;
import entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AttendanceMenu {

    private static final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void handle(Scanner sc) {
        while (true) {
            System.out.println("\n--- Attendance Menu ---");
            System.out.println("1. Add Attendance");
            System.out.println("2. View Attendance by ID");
            System.out.println("3. Update Attendance");
            System.out.println("4. Delete Attendance");
            System.out.println("5. View All Attendance Records");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addAttendance(sc);
                case 2 -> viewById(sc);
                case 3 -> updateAttendance(sc);
                case 4 -> deleteAttendance(sc);
                case 5 -> viewAllAttendance();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addAttendance(Scanner sc) {
        System.out.print("Enter Attendance ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String dateStr = sc.nextLine();
        System.out.print("Enter Status (Present/Absent): ");
        String status = sc.nextLine();
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        Employee emp = employeeDAO.getById(empId);
        if (emp != null) {
            LocalDate date = LocalDate.parse(dateStr);
            attendanceDAO.insert(new Attendance(id, date, status, emp));
            System.out.println("Attendance added.");
        } else {
            System.out.println("Invalid Employee ID.");
        }
    }

    private static void viewById(Scanner sc) {
        System.out.print("Enter Attendance ID: ");
        int id = sc.nextInt();
        Attendance a = attendanceDAO.getById(id);
        if (a != null) {
            System.out.println("Attendance ID: " + a.getAttId());
            System.out.println("Date: " + a.getDate());
            System.out.println("Status: " + a.getStatus());
            System.out.println("Employee ID: " + a.getEmployee().getEmpId());
        } else {
            System.out.println("Attendance not found.");
        }
    }

    private static void updateAttendance(Scanner sc) {
        System.out.print("Enter Attendance ID to update: ");
        int id = sc.nextInt();
        Attendance a = attendanceDAO.getById(id);
        if (a != null) {
            sc.nextLine();
            System.out.print("Enter new Date (yyyy-mm-dd): ");
            String dateStr = sc.nextLine();
            System.out.print("Enter new Status: ");
            String status = sc.nextLine();
            System.out.print("Enter new Employee ID: ");
            int empId = sc.nextInt();

            Employee emp = employeeDAO.getById(empId);
            if (emp != null) {
                a.setDate(LocalDate.parse(dateStr));
                a.setStatus(status);
                a.setEmployee(emp);
                attendanceDAO.update(a);
                System.out.println("Attendance updated.");
            } else {
                System.out.println("Invalid Employee ID.");
            }
        } else {
            System.out.println("Attendance not found.");
        }
    }

    private static void deleteAttendance(Scanner sc) {
        System.out.print("Enter Attendance ID to delete: ");
        int id = sc.nextInt();
        attendanceDAO.delete(id);
        System.out.println("Attendance deleted.");
    }

    private static void viewAllAttendance() {
        List<Attendance> list = attendanceDAO.getAll();
        if (list.isEmpty()) {
            System.out.println("No attendance records found.");
        } else {
            System.out.println("All Attendance Records:");
            for (Attendance a : list) {
                System.out.println(a.getAttId() + " | " + a.getDate() + " | " + a.getStatus() + " | EmpID: " + a.getEmployee().getEmpId());
            }
        }
    }
}
