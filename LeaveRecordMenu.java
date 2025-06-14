
import java.util.Scanner;

public class LeaveRecordMenu {
    public static void handle(Scanner sc) {
        LeaveRecordDAO dao = new LeaveRecordDAO();
        while (true) {
            System.out.println("\n--- Leave Record Menu ---");
            System.out.println("1. Add Leave Record");
            System.out.println("2. View Leave Record by ID");
            System.out.println("3. Delete Leave Record");
            System.out.println("4. Back");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Leave ID: "); int lid = sc.nextInt();
                    System.out.print("Employee ID: "); int empId = sc.nextInt(); sc.nextLine();
                    System.out.print("Total Days: "); int days = sc.nextInt(); sc.nextLine();
                    System.out.print("Reason: "); String reason = sc.nextLine();
                    Employee emp = new EmployeeDAO().getEmployeeById(empId);
                    dao.insertLeaveRecord(new LeaveRecord(lid, days, reason, emp));
                    break;
                case 2:
                    System.out.print("Leave ID: ");
                    LeaveRecord l = dao.getLeaveRecordById(sc.nextInt());
                    if (l != null) System.out.println("ID: " + l.getLeaveId() + ", Days: " + l.getTotalDays() + ", Reason: " + l.getReason());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Leave ID: "); dao.deleteLeaveRecord(sc.nextInt());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}