package models;

import java.util.Map;

public class WebTableUser {
	private String firstName;
    private String lastName;
    private String email;
    private String salary;
    private String age;
    private String department;

    // ✅ No-arg constructor
    public WebTableUser() {}

    // ✅ Optional all-args constructor
    public WebTableUser(String firstName, String lastName, String email, String salary, String age, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.age = age;
        this.department = department;
    }

    // ✅ Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public static WebTableUser fromExcelRow(Map<String, String> rowData) {
        return new WebTableUser(
            rowData.getOrDefault("First Name", ""),
            rowData.getOrDefault("Last Name", ""),
            rowData.getOrDefault("Email", ""),
            rowData.getOrDefault("Salary", ""),
            rowData.getOrDefault("Age", ""),
            rowData.getOrDefault("Department", "")
        );
    }


}
