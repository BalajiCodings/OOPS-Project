public class Payroll {
    private int payrollId;
    private int month;
    private int year;
    private double netSalary;
    private Employee employee;

    public Payroll(int payrollId, int month, int year, double netSalary, Employee employee) {
        this.payrollId = payrollId;
        this.month = month;
        this.year = year;
        this.netSalary = netSalary;
        this.employee = employee;
    }

    // Getters and Setters
    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}