package com.devsu.devsu.application.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.devsu.application.commands.MovementCreationDTO;
import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.DailyLimitExceeded;
import com.devsu.devsu.core.exceptions.MovementBalanceNotAvailableException;
import com.devsu.devsu.core.model.Movement;
import com.devsu.devsu.core.services.MovementService;

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
    public ResponseEntity<List<Movement>> getReport(@RequestParam("date") String dateRange, @RequestParam("accountNumber") String accountNumber) throws AccountNotFoundException, ParseException {
        String[] dates = dateRange.split(",");
        Date dateA = new SimpleDateFormat("dd-MM-yyyy").parse(dates[0]);
        Date dateB = new SimpleDateFormat("dd-MM-yyyy").parse(dates[1]);

        return ResponseEntity.ok(movementService.queryMovementsBydateRange(dateA, dateB, accountNumber));
    }
}
