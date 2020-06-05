package com.semafoor.petclinic.vetsservice.repository;

import com.semafoor.petclinic.vetsservice.model.Specialty;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@EnableScan
public interface SpecialtyRepository extends CrudRepository<Specialty, UUID> {
}
