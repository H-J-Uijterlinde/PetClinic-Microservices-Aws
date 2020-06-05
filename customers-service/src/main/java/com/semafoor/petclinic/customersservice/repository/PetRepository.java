package com.semafoor.petclinic.customersservice.repository;

import com.semafoor.petclinic.customersservice.model.Pet;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@EnableScan
public interface PetRepository extends CrudRepository<Pet, UUID> {
}
