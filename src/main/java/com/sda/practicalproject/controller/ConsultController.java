package com.sda.practicalproject.controller;

import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.ConsultService;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class ConsultController {
    private final ConsultService consultService;
    private final Scanner scanner;

    public ConsultController(ConsultService consultService, Scanner scanner) {
        this.consultService = consultService;
        this.scanner = scanner;
    }

    public void createConsult() {
        try {
            System.out.println("Please insert Vet's Id");
            long vetId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please insert Pet's Id");
            long petId = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please insert description");
            String description = scanner.nextLine().trim();
            System.out.println("Please insert date of consult: YYYY-MM-DD");
            Date dateOfConsult = Date.from(LocalDate.parse(scanner.nextLine(),
                    DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay().toInstant(ZoneOffset.UTC));
            consultService.createConsult(vetId, petId, dateOfConsult, description);
            System.out.println("Consult was created");
        } catch (NumberFormatException e) {
            System.err.println("Please insert a number");
        } catch (DateTimeParseException e) {
            System.err.println("Please insert a valid date");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error");
        }
    }
}
