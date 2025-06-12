import java.time.LocalDate;

public class Attendance {
    private int attId;
    private LocalDate date;
    private String status;
    private Employee employee;

    public Attendance(int attId, LocalDate date, String status, Employee employee) {
        this.attId = attId;
        this.date = date;
        this.status = status;
        this.employee = employee;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}