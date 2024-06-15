package com.medicines.distribution.service;


import com.medicines.distribution.dto.ReportDTO;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.model.Report;
import com.medicines.distribution.repository.BasicUserRepository;
import com.medicines.distribution.repository.CompanyAdminRepository;
import com.medicines.distribution.repository.CompanyRepository;
import com.medicines.distribution.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)

public class ReportService {

    @Autowired
    CompanyAdminRepository companyAdminRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    BasicUserRepository basicUserRepository;

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ReportRepository reportRepository;

    @Transactional(readOnly = false)
    public Report addReport(ReportDTO reportDTO) {

        Company company=new Company();
        CompanyAdmin companyAdmin=new CompanyAdmin();
        if(reportDTO.getReportedCompanyAdmin()!=null)
        {
            companyAdmin = companyAdminRepository.findAllById(reportDTO.getReportedCompanyAdmin().getUser().getId());

        }

        BasicUser customer = basicUserRepository.findByUserId(reportDTO.getSender().getUser().getId());

        if(!Objects.equals(reportDTO.getReportedCompany().getName(), ""))
        {
           company = companyRepository.findById(reportDTO.getReportedCompany().getId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
        }
        else
        {
            company=null;
        }

        Report report =new Report();
        report.setReportedCompany(company);
        report.setSender(customer);
        report.setReportedCompanyAdmin(companyAdmin);
        report.setStatus(Report.StatusReport.ON_HOLD);
        report.setDescription(reportDTO.getDescription());
        report.setResponse("");


        return reportRepository.save(report);
    }

    public Set<Report> getReportByUserId(Integer userId) {
        return reportRepository.findAllBySenderUserId(userId);

    }

    public List<Report> getReportForReview() {

        List<Report> reports=reportRepository.findAll();
        List<Report> forReviewReports=new ArrayList<>();
        for(Report r: reports)
        {
            if(Objects.equals(r.getResponse(), ""))
            {
                forReviewReports.add(r);
            }
        }

        return  forReviewReports;

    }

    @Transactional(readOnly = false)
    public ReportDTO addReview(Integer id, ReportDTO reportDTO) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        BasicUser basicUser=basicUserRepository.findByUserId(report.getSender().getUser().getId());
        report.setResponse(reportDTO.getResponse());
        report.setStatus(reportDTO.getStatus());

        emailService.sendSimpleEmail(basicUser.getUser().getEmail(),"Odgovor na zalbu",reportDTO.getResponse());

        Report updatedReport = reportRepository.save(report);
        return new ReportDTO(updatedReport);
    }




}
