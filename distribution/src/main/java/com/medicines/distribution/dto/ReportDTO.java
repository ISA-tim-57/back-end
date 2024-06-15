package com.medicines.distribution.dto;

import com.medicines.distribution.model.*;

public class ReportDTO {

    private Integer id;
    private String description;
    private String response;
    private BasicUserDTO sender;
    private CompanyDTO reportedCompany;
    private CompanyAdminDTO reportedCompanyAdmin;
    private Report.StatusReport status;

    public ReportDTO() {
    }

    public ReportDTO(Integer id, String description, String response, BasicUserDTO sender, CompanyDTO reportedCompany, CompanyAdminDTO reportedCompanyAdmin, Report.StatusReport status) {
        this.id = id;
        this.description = description;
        this.response = response;
        this.sender = sender;
        this.reportedCompany = reportedCompany;
        this.reportedCompanyAdmin = reportedCompanyAdmin;
        this.status = status;
    }

    public ReportDTO(Report report) {
        this.id = report.getId();
        this.description = report.getDescription();
        this.response = report.getResponse();
        this.status = report.getStatus() != null ? Report.StatusReport.valueOf(report.getStatus().toString()) : null;
        this.sender = new BasicUserDTO(report.getSender());
        this.reportedCompany = report.getReportedCompany() != null ? new CompanyDTO(report.getReportedCompany()) : null;
        this.reportedCompanyAdmin = report.getReportedCompanyAdmin() != null ? new CompanyAdminDTO(report.getReportedCompanyAdmin()) : null;
    }

    // Getters and setters

    public BasicUserDTO getSender() {
        return sender;
    }

    public void setSender(BasicUserDTO sender) {
        this.sender = sender;
    }

    public CompanyDTO getReportedCompany() {
        return reportedCompany;
    }

    public void setReportedCompany(CompanyDTO reportedCompany) {
        this.reportedCompany = reportedCompany;
    }

    public CompanyAdminDTO getReportedCompanyAdmin() {
        return reportedCompanyAdmin;
    }

    public void setReportedCompanyAdmin(CompanyAdminDTO reportedCompanyAdmin) {
        this.reportedCompanyAdmin = reportedCompanyAdmin;
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

    public Report.StatusReport getStatus() {
        return status;
    }

    public void setStatus(Report.StatusReport status) {
        this.status = status;
    }
}
