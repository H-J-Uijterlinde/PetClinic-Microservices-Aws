package com.semafoor.petclinic.customersservice.repository;

import com.semafoor.petclinic.customersservice.model.PetType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@EnableScan
public interface PetTypeRepository extends CrudRepository<PetType, UUID> {
}
