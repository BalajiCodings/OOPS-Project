# 🧑‍💼 Employee Management System – Java + MySQL (JDBC)

A **modular console-based application** built in **Java** using **JDBC** to manage employees, departments, roles, attendance, payroll, and leave records in an organization. The project follows **OOP principles**, **DAO design pattern**, and simulates an HR system with real-world CRUD operations, relationships, and menu-driven user interaction.

---

## 📌 Features

✅ **Authentication**  
- Simple username/password login system  
- Restricts access to authorized users only  

✅ **Employee Module**  
- Add, update, delete, list employees  
- Each employee is associated with a department and role  
- Search by name or department  

✅ **Department Module**  
- Manage departments (CRUD)

✅ **Role Module**  
- Manage job roles (CRUD)

✅ **Attendance Module**  
- Mark and track employee attendance  
- Supports date and status (present/absent)

✅ **Leave Record Module**  
- Apply for leaves with reason and duration  
- Linked to employee

✅ **Payroll Module**  
- Monthly payroll with net salary  
- Linked to employee with year/month

✅ **Modular Menus**  
- Each module has its own class and menu  
- Easily extendable and reusable

---

## 🛠️ Tech Stack

| Layer              | Technology                      |
|--------------------|----------------------------------|
| Language           | Java (OOP Principles)            |
| Database           | MySQL                            |
| Connectivity       | JDBC (MySQL Connector)           |
| Build/Run          | `.bat` file with classpath setup |
| Design Pattern     | DAO (Data Access Object)         |
| DB Libraries       | `mysql-connector-j-9.3.0.jar`    |

---

## 📁 Project Structure

```
├── entity/
│   ├── Employee.java
│   ├── Department.java
│   ├── Role.java
│   ├── Attendance.java
│   ├── Payroll.java
│   └── LeaveRecord.java
│
├── dao/
│   ├── DAO<T>.java
│   ├── DBConnection.java
│   ├── EmployeeDAO.java
│   ├── DepartmentDAO.java
│   ├── RoleDAO.java
│   ├── AttendanceDAO.java
│   ├── PayrollDAO.java
│   ├── LeaveRecordDAO.java
│   └── UserDAO.java
│
├── menus/
│   ├── EmployeeMenu.java
│   ├── DepartmentMenu.java
│   ├── RoleMenu.java
│   ├── AttendanceMenu.java
│   ├── LeaveRecordMenu.java
│   ├── PayrollMenu.java
│   └── AuthMenu.java
│
├── Main.java
├── run.bat
└── library/
    └── mysql-connector-j-9.3.0.jar
```

---

## 🧠 Design Decisions

- Used a **generic DAO interface** for CRUD operations for code consistency
- Each module has its own menu and DAO class for **separation of concerns**
- JDBC is used directly instead of frameworks to get **hands-on SQL query execution**
- Employee entity has **foreign key relationships** with Role and Department
- Payroll, Leave, and Attendance entities are linked with Employee for real-world simulation

---

## ⚙️ How to Run

### Prerequisites:
- JDK 17+ installed
- MySQL installed and running
- Schema created (`ems`) with all necessary tables

### Setup Instructions:
1. Clone this repo or download files.
2. Place `mysql-connector-j-9.3.0.jar` inside the `library/` folder.
3. Update your database password in `DBConnection.java`.
4. Run `run.bat` or use this in terminal:
   ```bash
   javac -cp ".;library/mysql-connector-j-9.3.0.jar" *.java
   java -cp ".;library/mysql-connector-j-9.3.0.jar" Main
   ```

---

## 🧪 Sample Tables Schema

```sql
CREATE TABLE department (
  depId INT AUTO_INCREMENT PRIMARY KEY,
  depName VARCHAR(100) NOT NULL
);

CREATE TABLE role (
  roleId INT AUTO_INCREMENT PRIMARY KEY,
  roleName VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
  empId INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  gender VARCHAR(10),
  roleId INT,
  depId INT,
  FOREIGN KEY (roleId) REFERENCES role(roleId),
  FOREIGN KEY (depId) REFERENCES department(depId)
);

CREATE TABLE attendance (
  attId INT PRIMARY KEY,
  date DATE,
  status VARCHAR(10),
  empId INT,
  FOREIGN KEY (empId) REFERENCES employee(empId)
);

CREATE TABLE leaveRecord (
  leaveId INT PRIMARY KEY,
  totalDays INT,
  reason VARCHAR(255),
  empId INT,
  FOREIGN KEY (empId) REFERENCES employee(empId)
);

CREATE TABLE payroll (
  payrollId INT PRIMARY KEY,
  month INT,
  year INT,
  netSalary DOUBLE,
  empId INT,
  FOREIGN KEY (empId) REFERENCES employee(empId)
);

CREATE TABLE users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50)
);
```

---

## 💡 Challenges Faced

- Handling foreign key relations and cascading fetch using DAOs
- Avoiding redundancy in similar CRUD operations across modules
- Ensuring correct JDBC exception handling and SQL execution
- Designing modular yet simple structure to allow easy menu navigation

---

## ✅ Results & Learnings

- Understood full-cycle backend app structure with DAO and JDBC
- Gained hands-on experience with Java OOP, SQL joins, and relationships
- Developed reusable, maintainable, and testable codebase
- Practiced modular thinking, separation of concerns, and single responsibility

---

## 📌 Future Improvements

- Replace console UI with a JavaFX or web-based frontend
- Add user roles and access control
- Include unit testing (e.g., with JUnit)
- Use Maven/Gradle for dependency management
- Replace JDBC with Hibernate for ORM support

---

## 🤝 Credits

Made by **Balaji S**  
Feel free to fork and contribute.