package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConsultService {
    void createConsult(long vetId, long petId, Date dateOfConsult, String description)
            throws EntityNotFoundException, EntityUpdateFailedException;

    List<Consult> getAllConsults();

    Optional<Consult> getConsultById(long id);

    void updateConsultById(long id, String description) throws EntityNotFoundException, EntityUpdateFailedException;
}
