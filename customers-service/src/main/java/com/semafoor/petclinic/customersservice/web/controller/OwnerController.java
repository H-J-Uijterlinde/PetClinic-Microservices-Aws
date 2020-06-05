package com.semafoor.petclinic.customersservice.web.controller;

import com.google.common.collect.Lists;
import com.semafoor.petclinic.customersservice.model.Owner;
import com.semafoor.petclinic.customersservice.repository.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@Valid @RequestBody Owner owner) {

        Owner savedOwner = ownerRepository.save(owner);

        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}").buildAndExpand(owner.getId()).toUri();

        return ResponseEntity.created(uri).body(savedOwner);
    }

    @GetMapping
    public ResponseEntity<Collection<Owner>> findAll() {
        return ResponseEntity.ok(Lists.newArrayList(ownerRepository.findAll()));
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getById(@PathVariable UUID ownerId) {

        return ownerRepository.findById(ownerId).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Owner with id " + ownerId + " could not be found"));
    }
}
