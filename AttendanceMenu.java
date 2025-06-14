import java.sql.Date;
import java.util.Scanner;

public class AttendanceMenu {
    public static void handle(Scanner sc) {
        AttendanceDAO dao = new AttendanceDAO();
        while (true) {
            System.out.println("\n--- Attendance Menu ---");
            System.out.println("1. Add Attendance");
            System.out.println("2. View Attendance by ID");
            System.out.println("3. Delete Attendance");
            System.out.println("4. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Attendance ID: "); int attId = sc.nextInt();
                    System.out.print("Employee ID: "); int empId = sc.nextInt(); sc.nextLine();
                    System.out.print("Date (yyyy-mm-dd): "); Date date = Date.valueOf(sc.nextLine());
                    System.out.print("Status (Present/Absent): "); String status = sc.nextLine();
                    Employee emp = new EmployeeDAO().getEmployeeById(empId);
                    Attendance att = new Attendance(attId, date.toLocalDate(), status, emp);
                    dao.insertAttendance(att);
                    break;
                case 2:
                    System.out.print("Attendance ID: ");
                    Attendance a = dao.getAttendanceById(sc.nextInt());
                    if (a != null) System.out.println("ID: " + a.getAttId() + ", Date: " + a.getDate() + ", Status: " + a.getStatus());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Attendance ID: "); dao.deleteAttendance(sc.nextInt());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}