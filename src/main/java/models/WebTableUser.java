package models;

import java.lang.reflect.Field;
import java.util.Map;

import customAnnotations.ColumnMapping;

public class WebTableUser {

    @ColumnMapping(name = "First Name")
    private String firstName;

    @ColumnMapping(name = "Last Name")
    private String lastName;

    @ColumnMapping(name = "Email")
    private String email;

    @ColumnMapping(name = "Salary")
    private int salary;

    @ColumnMapping(name = "Age")
    private int age;

    @ColumnMapping(name = "Department")
    private String department;

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "WebTableUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }

    public static WebTableUser fromExcelRow(Map<String, String> rowData) {
        WebTableUser user = new WebTableUser();
        Class<?> clazz = WebTableUser.class;

        for (Field field : clazz.getDeclaredFields()) {
            ColumnMapping mapping = field.getAnnotation(ColumnMapping.class);
            if (mapping != null) {
                String columnName = mapping.name();
                String cellValue = rowData.get(columnName);
                if (cellValue != null) {
                    field.setAccessible(true);
                    try {
                        if (field.getType() == int.class) {
                            field.set(user, Integer.parseInt(cellValue));
                        } else if (field.getType() == double.class) {
                            field.set(user, Double.parseDouble(cellValue));
                        } else {
                            field.set(user, cellValue);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to map field: " + field.getName(), e);
                    }
                }
            }
        }

        return user;
    }
}