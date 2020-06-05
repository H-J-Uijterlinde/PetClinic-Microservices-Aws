package com.semafoor.petclinic.visitsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomersServiceClient {

    private final RestTemplate restTemplate;
    private final String hostname = "//customers-service";

    public boolean validatePetId(UUID petId) {
        ResponseEntity<?> response = restTemplate.exchange(hostname + "/petclinic/cs/api/pets/" + petId, HttpMethod.HEAD, null, ResponseEntity.class);

        return response.getStatusCode() == HttpStatus.NO_CONTENT;
    }
}
