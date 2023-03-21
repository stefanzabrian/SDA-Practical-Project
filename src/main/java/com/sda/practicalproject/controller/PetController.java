package com.sda.practicalproject.controller;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.PetService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class PetController {
    private final Scanner scanner;
    private final PetService petService;

    public PetController(PetService petService, Scanner scanner) {
        this.scanner = scanner;
        this.petService = petService;
    }

    public void createPet(){
        try {
            System.out.println("Please insert Pet's race");
            String race = scanner.nextLine().trim();
            System.out.println("Is this pet Vaccinated? (true/false)");
            boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine().trim());
            System.out.println("Please insert Pet's Owner name");
            String ownerName = scanner.nextLine();
            System.out.println("Please insert Date of Birth : YYYY-MM-DD");
            Date dateOfBirth = Date.from(LocalDate.parse(scanner.nextLine(),
                    DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay().toInstant(ZoneOffset.UTC));
            petService.addPet(race, dateOfBirth, isVaccinated, ownerName);
            System.out.println("Pet was created");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry!");
        }catch (DateTimeParseException e){
            System.out.println("Please insert date format YYYY-MM-DD");
        } catch (Exception e) {
            System.err.println("Internal server error");
            e.printStackTrace();
        }
    }
}
