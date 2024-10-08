package com.devsu.devsu.application.controllers;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.devsu.application.commands.MovementCreationDTO;
import com.devsu.devsu.application.query.MovementViewQuery;
import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.DailyLimitExceeded;
import com.devsu.devsu.core.exceptions.MovementBalanceNotAvailableException;
import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.core.services.MovementService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/movement")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping()
    public ResponseEntity<Object> createMovement(@Valid @RequestBody MovementCreationDTO movementCreationDTO)
            throws AccountNotFoundException, DailyLimitExceeded, MovementBalanceNotAvailableException {

        Movement movement = new Movement();
        movement.setDate(new Date());
        movement.setMovementType(movementCreationDTO.getMovementType());
        movement.setQuantity(movementCreationDTO.getQuantity());

        return ResponseEntity.created(null)
                .body(movementService.createMovement(movement, movementCreationDTO.getAccountNumber()));
    }

    // ?date=01-01-2021,01-01-2022&accountNumber=123456
    @GetMapping("/report")
    public ResponseEntity<List<Movement>> getReport(@RequestParam("date") String dateRange,
            @RequestParam("accountNumber") String accountNumber) throws AccountNotFoundException, ParseException {
        String[] dates = dateRange.split(",");
        Date dateA = new SimpleDateFormat("dd-MM-yyyy").parse(dates[0]);
        Date dateB = new SimpleDateFormat("dd-MM-yyyy").parse(dates[1]);

        return ResponseEntity.ok(movementService.queryMovementsBydateRange(dateA, dateB, accountNumber));
    }

    @GetMapping()
    public ResponseEntity<List<MovementViewQuery>> getAllMovements() throws AccountNotFoundException {
        List<MovementViewQuery> movements = movementService.getAllMovements().stream()
                .map(m -> new MovementViewQuery(m.getMovementId(), m.getDate(), m.getQuantity(), m.getMovementType(),
                        m.getAccount().getAccountNumber()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(movements);
    }

    @PostMapping(value = "/reportPDF")
    public ResponseEntity<byte[]> generatePdf(@RequestParam("date") String dateRange,
            @RequestParam("accountNumber") String accountNumber) throws AccountNotFoundException, ParseException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String[] dates = dateRange.split(",");
            Date dateA = new SimpleDateFormat("dd-MM-yyyy").parse(dates[0]);
            Date dateB = new SimpleDateFormat("dd-MM-yyyy").parse(dates[1]);

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out));
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Movements - accountNumber: " + accountNumber));
            document.add(new Paragraph("From: " + sdf.format(dateA) + " To: " + sdf.format(dateB)));

            Table table = new Table(4);
            table.addCell("Account Number");
            table.addCell("Quantity");
            table.addCell("Movement Type");
            table.addCell("Date");

            List<Movement> movements = movementService.queryMovementsBydateRange(dateA, dateB, accountNumber);

            for (Movement movement : movements) {
                table.addCell(movement.getAccount().getAccountNumber());
                table.addCell(movement.getQuantity().toString());
                table.addCell(movement.getMovementType().name());
                table.addCell(sdf.format(movement.getDate()));
            }

            document.add(table);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "reporte.pdf");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
