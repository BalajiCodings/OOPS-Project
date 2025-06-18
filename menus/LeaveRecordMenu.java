import java.util.*;
import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;

public class LeaveRecordMenu {
    public static void handle(Scanner sc) {
        LeaveRecordDAO dao = new LeaveRecordDAO();

        while (true) {
            System.out.println("\n--- Leave Record Menu ---");
            System.out.println("1. Add Leave Record");
            System.out.println("2. View Leave Record by ID");
            System.out.println("3. Delete Leave Record");
            System.out.println("4. Back");
            System.out.println("5. List All Leave Records");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Leave ID: ");
                    int lid = sc.nextInt();
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();
                    System.out.print("Total Days: ");
                    int days = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Reason: ");
                    String reason = sc.nextLine();

                    Employee emp = new EmployeeDAO().getById(empId);
                    if (emp == null) {
                        System.out.println("Invalid Employee ID.");
                        break;
                    }

                    LeaveRecord leave = new LeaveRecord(lid, days, reason, emp);
                    dao.insert(leave);
                    break;

                case 2:
                    System.out.print("Leave ID: ");
                    LeaveRecord l = dao.getById(sc.nextInt());
                    if (l != null) {
                        System.out.println("ID: " + l.getLeaveId()
                            + ", Days: " + l.getTotalDays()
                            + ", Reason: " + l.getReason()
                            + ", Employee: " + l.getEmployee().getName());
                    } else {
                        System.out.println("Not found.");
                    }
                    break;

                case 3:
                    System.out.print("Leave ID: ");
                    dao.delete(sc.nextInt());
                    break;

                case 4:
                    return;

                case 5:
                    List<LeaveRecord> allLeaves = dao.getAll();
                    for (LeaveRecord lr : allLeaves) {
                        System.out.println("ID: " + lr.getLeaveId() + ", Days: " + lr.getTotalDays() +
                        ", Reason: " + lr.getReason() +
                        ", Employee: " + lr.getEmployee().getName());
                    }
                    break;


                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
