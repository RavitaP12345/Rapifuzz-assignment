package com.example.incident_management_system.models;

import jakarta.validation.constraints.*;

import java.util.List;

public class UserModel {
    private Long userId;
    @NotNull(message = "Name must not be null.")
    @NotBlank(message = "Name must not be blank.")
    @Size(min = 2, max = 50, message = "Name must be in 2 to 50 characters.")
    private String name;
    @NotBlank(message = "Phone number must not be blank.")
    @NotNull(message = "Phone number must not be null.")
    @Pattern(regexp="^[0-9]{10}$", message="Phone number must be 10 digits without any characters.")
    private String phoneNumber;
    @NotNull(message = "Address must not be null.")
    @NotBlank(message = "Address must not be blank.")
    @Size(min = 2, max = 300, message = "Address must be in 2 to 300 characters.")
    private String address;
    @NotBlank(message = "Pin code must not be blank.")
    @NotNull(message = "Pin code must not be null.")
    /*@Pattern(regexp="^[0-9]{6}$", message="Pin code must be 10 digits without any characters.")*/
    private String pinCode;
    private String city;
    private String country;
    private List<IncidentModel> incidentList;
    private String creationDate;
    @NotNull(message = "Email id must not be null.")
    @NotBlank(message = "Email id must not be blank.")
    @Email(message = "Email id is not correct format.")
    private String emailId;
    @NotNull(message = "Password must not be null.")
    @NotBlank(message = "Password must not be blank.")
    @Size(min = 4, max = 8, message = "Password must be in 4 to 8 characters.")
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<IncidentModel> getIncidentList() {
        return incidentList;
    }

    public void setIncidentList(List<IncidentModel> incidentList) {
        this.incidentList = incidentList;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
