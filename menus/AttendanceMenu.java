import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import entity.Employee;
import entity.Role;
import entity.Department;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.DepartmentDAO;

public class AttendanceMenu {
    public static void handle(Scanner sc) {
        AttendanceDAO dao = new AttendanceDAO();
        while (true) {
            System.out.println("\n--- Attendance Menu ---");
            System.out.println("1. Add Attendance");
            System.out.println("2. View Attendance by ID");
            System.out.println("3. Delete Attendance");
            System.out.println("4. Update Attendance");
            System.out.println("5. List All Attendance Records"); 
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
 
            switch (ch) {
                case 1:
                    System.out.print("Attendance ID: "); int attId = sc.nextInt();
                    System.out.print("Employee ID: "); int empId = sc.nextInt(); sc.nextLine();
                    System.out.print("Date (yyyy-mm-dd): "); Date date = Date.valueOf(sc.nextLine());
                    System.out.print("Status (Present/Absent): "); String status = sc.nextLine();
                    Employee emp = new EmployeeDAO().getById(empId);
                    if (emp == null) {
                        System.out.println("Employee not found. Try again.");
                        break;
                    }
                    Attendance att = new Attendance(attId, date.toLocalDate(), status, emp);
                    dao.insert(att);
                    break;
                case 2:
                    System.out.print("Attendance ID: ");
                    Attendance a = dao.getById(sc.nextInt());
                    if (a != null) System.out.println("ID: " + a.getAttId() + ", Date: " + a.getDate() + ", Status: " + a.getStatus());
                    else System.out.println("Not found.");
                    break;
                case 3:
                    System.out.print("Attendance ID: "); dao.delete(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Attendance ID to update: ");
                    int updateId = sc.nextInt(); sc.nextLine();
                    System.out.print("New Date (yyyy-mm-dd): "); Date newDate = Date.valueOf(sc.nextLine());
                    System.out.print("New Status: "); String newStatus = sc.nextLine();
                    System.out.print("New Employee ID: "); int newEmpId = sc.nextInt(); sc.nextLine();
                    Employee updatedEmp = new EmployeeDAO().getById(newEmpId);
                    Attendance updated = new Attendance(updateId, newDate.toLocalDate(), newStatus, updatedEmp);
                    dao.update(updated);
                    break;
                

                case 5:
                    List<Attendance> allAtt = dao.getAll();
                    for (Attendance a1 : allAtt) {
                        System.out.println("ID: " + a1.getAttId() + ", Date: " + a1.getDate() +
                        ", Status: " + a1.getStatus() +
                        ", Employee: " + a1.getEmployee().getName());
                    }
                    break;


                default:
                    System.out.println("Invalid.");
            }
        }
    }
}