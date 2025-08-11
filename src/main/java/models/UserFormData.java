package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import customAnnotations.ColumnMapping;

/**
 * Represents a user's form data mapped from Excel using @ColumnMapping annotations.
 */
public class UserFormData {

    // 🧍 Personal Info
    @ColumnMapping(name = "First Name")
    private String firstName;

    @ColumnMapping(name = "Last Name")
    private String lastName;

    @ColumnMapping(name = "Email")
    private String userMail;

    @ColumnMapping(name = "Gender")
    private String gender;

    @ColumnMapping(name = "Mobile")
    private String mobile;

    @ColumnMapping(name = "DOB")
    private String dob;

    // 📚 Academic & Interests
    @ColumnMapping(name = "Subject")
    private String subject;

    @ColumnMapping(name = "Hobbies")
    private String hobbies;

    // 📁 File & Location
    @ColumnMapping(name = "Picture")
    private String picturePath;

    @ColumnMapping(name = "Address")
    private String address;

    @ColumnMapping(name = "State")
    private String state;

    @ColumnMapping(name = "City")
    private String city;

    // ✅ Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUserMail() { return userMail; }
    public String getGender() { return gender; }
    public String getMobile() { return mobile; }
    public String getDob() { return dob; }
    public String getSubject() { return subject; }
    public String getHobbies() { return hobbies; }
    public String getPicturePath() { return picturePath; }
    public String getAddress() { return address; }
    public String getState() { return state; }
    public String getCity() { return city; }

    // ✏️ Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUserMail(String userMail) { this.userMail = userMail; }
    public void setGender(String gender) { this.gender = gender; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setDob(String dob) { this.dob = dob; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }
    public void setPicturePath(String picturePath) { this.picturePath = picturePath; }
    public void setAddress(String address) { this.address = address; }
    public void setState(String state) { this.state = state; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return new StringBuilder("UserFormData{")
            .append("firstName='").append(firstName).append('\'')
            .append(", lastName='").append(lastName).append('\'')
            .append(", userMail='").append(userMail).append('\'')
            .append(", gender='").append(gender).append('\'')
            .append(", mobile='").append(mobile).append('\'')
            .append(", dob='").append(dob != null ? dob : "null").append('\'')
            .append(", subject='").append(subject).append('\'')
            .append(", hobbies='").append(hobbies).append('\'')
            .append(", picturePath='").append(picturePath).append('\'')
            .append(", address='").append(address).append('\'')
            .append(", state='").append(state).append('\'')
            .append(", city='").append(city).append('\'')
            .append('}')
            .toString();
    }
    
    public static LocalDate parseDob(String dobString) {
        List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dobString, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new IllegalArgumentException("❌ Unrecognized DOB format: " + dobString);
    }
}