package com.semafoor.petclinic.visitsservice.web;

import com.semafoor.petclinic.visitsservice.model.Visit;
import com.semafoor.petclinic.visitsservice.repositories.VisitsRepository;
import com.semafoor.petclinic.visitsservice.service.CustomersServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class VisitsController {

    private final VisitsRepository visitsRepository;
    private final CustomersServiceClient customersServiceClient;

    @PostMapping("/owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Visit> postVisit(@Valid @RequestBody Visit visit,
                                    @PathVariable UUID petId) {

        if (customersServiceClient.validatePetId(petId)) {
            visit.setPetId(petId);
        } else {
            throw new ResourceNotFoundException("Pet with id " + petId + " could not be found");
        }


        log.info("Saving visit {}", visit);
        return ResponseEntity.ok(visitsRepository.save(visit));
    }

    @GetMapping("/owners/*/pets/{petId}/visits")
    ResponseEntity<List<Visit>> visitsByPetId(@PathVariable UUID petId) {
        return ResponseEntity.ok(visitsRepository.findByPetId(petId));
    }

    @GetMapping("/pets/visits")
    ResponseEntity<List<Visit>> visitsMultiGet(@RequestParam("petId") List<UUID> petIds) {
        return ResponseEntity.ok(visitsRepository.findByPetIdIn(petIds));
    }
}
