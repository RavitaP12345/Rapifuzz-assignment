package com.example.incident_management_system.entities;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "incident_details")
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incident_seq")
    @SequenceGenerator(name = "incident_seq", sequenceName = "incident_sequence", initialValue = 10000, allocationSize = 1)
    private Long incidentId;
    private String customUniqueIncidentId;
    private String enterpriseOrGovernment;
    private String reporterName;
    @Column(columnDefinition = "LONGTEXT")
    private String incidentDetails;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDateTime;
    private String priority;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

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

    public Date getReportedDateTime() {
        return reportedDateTime;
    }

    public void setReportedDateTime(Date reportedDateTime) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
