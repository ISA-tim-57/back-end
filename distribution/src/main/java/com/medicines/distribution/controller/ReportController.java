package com.medicines.distribution.controller;

import com.medicines.distribution.dto.EquipmentDTO;
import com.medicines.distribution.dto.PurchaseOrderDTO;
import com.medicines.distribution.dto.ReportDTO;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.model.Report;
import com.medicines.distribution.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/report")
public class ReportController {

    @Autowired
    ReportService reportService;


    @PostMapping("/add")
    public ResponseEntity<ReportDTO> addReport(@RequestBody ReportDTO reportDTO) {
        Report report = reportService.addReport(reportDTO);
        ReportDTO responseDTO = new ReportDTO(report);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<ReportDTO>> getReportByUserId(@PathVariable Integer userId) {
        Set<Report> reports = reportService.getReportByUserId(userId);
        if (reports != null && !reports.isEmpty()) {
            List<ReportDTO> reportDTOs = new ArrayList<>();
            for (Report r : reports) {
                reportDTOs.add(new ReportDTO(r));
            }
            return new ResponseEntity<>(reportDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReportDTO>> getReportForReview() {
        List<Report> reports = reportService.getReportForReview();
        if (reports != null && !reports.isEmpty()) {
            List<ReportDTO> reportDTOs = new ArrayList<>();
            for (Report r : reports) {
                reportDTOs.add(new ReportDTO(r));
            }
            return new ResponseEntity<>(reportDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> addReview(@PathVariable Integer id, @RequestBody ReportDTO reportDTO){
       ReportDTO report = reportService.addReview(id,reportDTO);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }








}
