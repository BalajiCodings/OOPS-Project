import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department dept = new Department(1, "Engineering");
        Role role = new Role(101, "Software Developer");
        Employee emp1 = new Employee(1001, "Balaji", 20, "Male", role, dept);

        Attendance att = new Attendance(1, LocalDate.now(), "Present", emp1);
        LeaveRecord leave = new LeaveRecord(10, 2, "Vacation", emp1);
        Payroll payroll = new Payroll(1001, 01, 2025, 1000.25, emp1);
        //printDetails(emp1);

        System.out.println(att.getEmployee().getEmpId());
    }


    /*
    public static void printDetails(Employee emp) {
        
    }*/
}