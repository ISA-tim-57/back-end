package com.medicines.distribution.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {

    public enum StatusReport {
        ON_HOLD, ACCEPTED, REJECTED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "response", nullable = true)
    private String response;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private BasicUser sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id",nullable = true)
    private Company reportedCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_admin_id",nullable = true)
    private CompanyAdmin reportedCompanyAdmin;


    @Enumerated(EnumType.STRING)
    private Report.StatusReport status = Report.StatusReport.ON_HOLD;

    public Report() {
    }

    public Report(Integer id, String description, String response, BasicUser customer, Company company, CompanyAdmin companyAdmin, StatusReport status) {
        this.id = id;
        this.description = description;
        this.response = response;
        this.sender = customer;
        this.reportedCompany = company;
        this.reportedCompanyAdmin = companyAdmin;
        this.status = status;
    }

    public StatusReport getStatus() {
        return status;
    }

    public void setStatus(StatusReport status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public BasicUser getSender() {
        return sender;
    }

    public void setSender(BasicUser sender) {
        this.sender = sender;
    }

    public Company getReportedCompany() {
        return reportedCompany;
    }

    public void setReportedCompany(Company reportedCompany) {
        this.reportedCompany = reportedCompany;
    }

    public CompanyAdmin getReportedCompanyAdmin() {
        return reportedCompanyAdmin;
    }

    public void setReportedCompanyAdmin(CompanyAdmin reportedCompanyAdmin) {
        this.reportedCompanyAdmin = reportedCompanyAdmin;
    }
}