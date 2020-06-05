package com.semafoor.petclinic.authservice;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface ClientRepository extends CrudRepository<Client, UUID> {

    Optional<Client> findByClientId(String clientId);
}
