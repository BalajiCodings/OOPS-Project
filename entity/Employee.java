package entity;

public class Employee {
    private int empId;
    private String name;
    private int age;
    private String gender;
    private Role role;
    private Department department;

    public Employee(int empId, String name, int age, String gender, Role role, Department department) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.department = department;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return empId + " - " + name + " - " + age + " - " + gender + " - " + role.getRoleName() + " - " + department.getDepName();
    }
}
