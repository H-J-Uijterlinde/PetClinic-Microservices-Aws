package com.semafoor.petclinic.visitsservice.repositories;

import com.semafoor.petclinic.visitsservice.model.Visit;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@EnableScan
public interface VisitsRepository extends CrudRepository<Visit, UUID> {

    List<Visit> findByPetId(UUID petId);

    List<Visit> findByPetIdIn(Collection<UUID> petIds);
}
