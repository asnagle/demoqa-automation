package models;

import customAnnotations.ColumnMapping;

public class TextBoxUser {

    @ColumnMapping(name = "First Name")
    private String firstName;

    @ColumnMapping(name = "Last Name")
    private String lastName;

    @ColumnMapping(name = "Email")
    private String email;

    @ColumnMapping(name = "Current Address") // Maps to "Current Address" column
    private String currentAddress;

    @ColumnMapping(name = "Address") // Maps to "Address" column as Permanent Address
    private String permanentAddress;

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }
    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
        return "TextBoxUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                '}';
    }
}