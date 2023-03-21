package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.ConsultRepository;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.VetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService {
    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VetRepository vetRepository,
                              PetRepository petRepository,
                              ConsultRepository consultRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }

    @Override
    public void createConsult(long vetId, long petId, Date dateOfConsult, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (vetId <= 0) {
            throw new IllegalArgumentException("Invalid Vet Id, value must be greater than zero");
        }
        if (petId <= 0) {
            throw new IllegalArgumentException("Invalid Vet Id, value must be greater than zero");
        }
        if (dateOfConsult == null) {
            throw new IllegalArgumentException("Invalid date, null value found");
        }
        if(dateOfConsult.before(Date.from(Instant.now().minus(Duration.ofDays(1))))){
            throw new IllegalArgumentException("Invalid date, date from future found");
        }
        if(description == null || description.isBlank() || description.isEmpty()){
            throw new IllegalArgumentException("Invalid description, value must be not null or not blank");
        }
        Optional<Vet> optionalVet = vetRepository.findById(vetId);
        if (optionalVet.isEmpty()){
            throw new EntityNotFoundException("Vet id was not found " + vetId);
        }
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()){
            throw new EntityNotFoundException("Pet id was not found " + petId);
        }
        Consult consult = new Consult(dateOfConsult,description);
        consult.setVet(optionalVet.get());
        consult.setPet(optionalPet.get());
        consultRepository.save(consult);
    }

    @Override
    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }

    @Override
    public Optional<Consult> getConsultById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Consult Id, value must be greater than zero");
        }
        return consultRepository.findById(id);
    }

    @Override
    public void updateConsultById(long id, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Consult Id, value must be greater than zero");
        }
        if (description == null || description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description, value must be not null or not blank");
        }
        Optional<Consult> optionalConsult = consultRepository.findById(id);
        if (optionalConsult.isEmpty()) {
            throw new EntityNotFoundException("Consult id not found");
        }
        Consult consult = optionalConsult.get();
        consult.setDescription(description);
        consultRepository.update(consult);
    }
}
