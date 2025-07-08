package entity;

public class LeaveRecord {
    private int leaveId;
    private int totalDays;
    private String reason;
    private Employee employee;

    public LeaveRecord(int leaveId, int totalDays, String reason, Employee employee) {
        this.leaveId = leaveId;
        this.totalDays = totalDays;
        this.reason = reason;
        this.employee = employee;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public String getReason() {
        return reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return leaveId + " - " + totalDays + " - " + reason + " - " + employee.getName();
    }
}
