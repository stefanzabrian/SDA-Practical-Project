package com.sda.practicalproject.controller;

import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.VetService;

import java.util.Scanner;

public class VetController {
    private final VetService vetService;
    private final Scanner scanner;

    public VetController(VetService vetService, Scanner scanner) {
        this.vetService = vetService;
        this.scanner = scanner;
    }

    public void createVet() {
        try {
            System.out.println("Please insert Vet's First Name");
            String firstName = scanner.nextLine();
            System.out.println("Please insert Vet's Last Name");
            String lastName = scanner.nextLine();
            System.out.println("Please insert Vet's Address");
            String address = scanner.nextLine();
            System.out.println("Please insert Vet's Speciality");
            String speciality = scanner.nextLine();
            vetService.addVet(firstName, lastName, address, speciality);
            System.out.println("Vet successfully saved");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
