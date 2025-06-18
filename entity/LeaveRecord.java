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

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}