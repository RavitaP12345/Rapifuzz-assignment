package com.example.incident_management_system.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IncidentModel {
    private Long incidentId;
    private String customUniqueIncidentId;
    @NotNull(message = "Incident type must not be null.")
    @NotBlank(message = "Incident type must not be blank.")
    private String enterpriseOrGovernment;
    private String reporterName;
    @NotNull(message = "Incident details must not be null.")
    @NotBlank(message = "Incident details must not be blank.")
    private String incidentDetails;
    private String reportedDateTime;
    @NotNull(message = "Priority must not be null.")
    @NotBlank(message = "Priority must not be blank.")
    private String priority;
    @NotNull(message = "Status must not be null.")
    @NotBlank(message = "Status must not be blank.")
    private String status;
    private UserModel user;
    @NotNull(message = "User Id must not be null.")
    @NotBlank(message = "User Id must not be blank.")
    private String userId;

    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public String getCustomUniqueIncidentId() {
        return customUniqueIncidentId;
    }

    public void setCustomUniqueIncidentId(String customUniqueIncidentId) {
        this.customUniqueIncidentId = customUniqueIncidentId;
    }

    public String getEnterpriseOrGovernment() {
        return enterpriseOrGovernment;
    }

    public void setEnterpriseOrGovernment(String enterpriseOrGovernment) {
        this.enterpriseOrGovernment = enterpriseOrGovernment;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getIncidentDetails() {
        return incidentDetails;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public String getReportedDateTime() {
        return reportedDateTime;
    }

    public void setReportedDateTime(String reportedDateTime) {
        this.reportedDateTime = reportedDateTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
