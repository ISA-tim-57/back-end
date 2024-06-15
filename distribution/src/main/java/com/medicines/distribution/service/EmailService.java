package com.medicines.distribution.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.model.OrderEquipment;
import com.medicines.distribution.model.PurchaseOrder;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    private String emailUsername;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;


    @Async
    public void sendReservationConfirmationEmail(PurchaseOrder order) throws Exception {
        System.out.println("Async metoda se izvršava u drugom Threadu u odnosu na prihvaćeni zahtev. Thread id: " + Thread.currentThread().getId());
        // Simulacija duže aktivnosti da bi se uočila razlika
        Thread.sleep(10000);
        System.out.println("Slanje reservation emaila...");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // Set email details
        helper.setTo(order.getCustomer().getUser().getEmail());
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setSubject("Reservation Confirmation");
        String emailContent = "Hi, " + order.getCustomer().getName()
                + "\n\nThank you for your reservation. To access reservation details, please scan the QR code below";

        // Save the QR code image on disk
        File qrCodeFile = saveQR(order);

        // Attach the QR code image to the email
        helper.addAttachment("qrcode.png", qrCodeFile);

        // Set the email content
        helper.setText(emailContent, true);

        // Send the email
        javaMailSender.send(mimeMessage);

        System.out.println("Reservation email sent!");
    }




    private String generateEquipmentDetails(List<OrderEquipment> orderEquipments) {
        StringBuilder equipmentDetails = new StringBuilder();

        for (OrderEquipment orderEquipment : orderEquipments) {
            Equipment equipment = orderEquipment.getEquipment();
            int quantity = orderEquipment.getQuantity();
            equipmentDetails.append(equipment.getName())
                    .append(" (Quantity: ")
                    .append(quantity)
                    .append(")\n");
        }

        return equipmentDetails.toString();
    }


    public File saveQR(PurchaseOrder order) throws Exception {
        int width = 300;
        int height = 300;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String equipmentDetails = generateEquipmentDetails(order.getOrderEquipments());
        String reservationDetails = "Reservation id: " + order.getId() +
                "\nStatus: " + order.getStatus() +
                "\nEquipment: " + equipmentDetails +
                "\nAppointment Date: " + order.getAppointment().getDateAndTime() +
                "\nAppointment Duration: " + order.getAppointment().getDuration() +
                "\nUser NAME: " + order.getCustomer().getName() +
                "\nCompany name: " + order.getAppointment().getCompany().getName() +
                "\nCompany Administrator: " + order.getCompanyAdmin().getName();

        // Generate the QR code bit matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(reservationDetails, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        // Define the directory path
        String directoryPath = "./qrcodes/";

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String qrCodeImageName = order.getCustomer().getUser().getId() + "_" + order.getId() + "_" + timestamp + ".png";

        // Create the full path to the QR code image
        File qrCodeFile = new File(directoryPath + qrCodeImageName);
        ImageIO.write(qrImage, "png", qrCodeFile);

        // Assuming you have a method to set the QR code path in your reservation object
        // reservation.setQrCode("qrcodes/" + qrCodeImageName);
        // Assuming you have a service to update the reservation with the QR code
        // reservationService.updateQRCode(reservation);

        return qrCodeFile;
    }



    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailUsername);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println(emailUsername);
        javaMailSender.send(message);
    }

    @Async
    public void sendNotificaitionAsync(BasicUser basicUser) throws MailException, InterruptedException {

        Thread.sleep(10000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(basicUser.getUser().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Account activation");
        mail.setText("HI," + basicUser.getName() + ",To activate your account, please click the following link:\n\nhttp://localhost:4200/verify/" + basicUser.getUser().getId() + "\n\nGoodbye!");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }










}
