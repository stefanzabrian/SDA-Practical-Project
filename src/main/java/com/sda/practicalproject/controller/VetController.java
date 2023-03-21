package com.sda.practicalproject.controller;

import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.VetService;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.util.Optional;
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

    public void displayAllVets() {
        vetService.getAllVets().stream()
                .forEach(vet -> System.out.println(vet.getId() + " " +
                        vet.getFirstName() + " " +
                        vet.getLastName()));
    }

    public void updateVet() {
        try {
            System.out.println("Please insert Vet's Id");
            long id = Long.parseLong(scanner.nextLine());
            System.out.println("Please insert Vet's Last Name");
            String lastName = scanner.nextLine();
            System.out.println("Please insert Vet's Address");
            String address = scanner.nextLine();
            System.out.println("Please insert Vet's Speciality");
            String speciality = scanner.nextLine();

            vetService.updateVet(id, lastName, address, speciality);
            System.out.println("Vet has been updated");

        } catch (NumberFormatException e) {
            System.err.println("Please insert valid numeric id");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry!");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal Server Error");
        }
    }

    public void findVetById() {
        try {
            System.out.println("Please insert Vet's Id");
            long id = Long.parseLong(scanner.nextLine());
            Optional<Vet> optionalVet = vetService.findVetById(id);
            if (optionalVet.isPresent()) {
                System.out.println(optionalVet.get());
            } else {
                System.out.println("Vet was not found by id: " + id);
            }
        } catch (NumberFormatException e) {
            System.err.println("Please insert valid numeric id");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal Server Error");
        }
    }

    public void deleteVetById() {
        try {
            System.out.println("Please insert Vet's Id");
            long id = Long.parseLong(scanner.nextLine());
            vetService.deleteVetById(id);
            System.out.println("Vet was successfully deleted");
        } catch (NumberFormatException e) {
            System.err.println("Please insert valid numeric id");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch(Exception e){
            System.err.println("Internal Server Error");
        }
    }
}
