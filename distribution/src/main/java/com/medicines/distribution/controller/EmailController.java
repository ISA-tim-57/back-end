package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CompanyDTO;
import com.medicines.distribution.dto.EmailRequest;
import com.medicines.distribution.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/mailing")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailRequest> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendSimpleEmail(request.getTo(), request.getSubject(), request.getBody());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
