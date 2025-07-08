package entity;

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

    public int getPayrollId() {
        return payrollId;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return payrollId + " - " + month + "/" + year + " - ₹" + netSalary + " - " + employee.getName();
    }
}
