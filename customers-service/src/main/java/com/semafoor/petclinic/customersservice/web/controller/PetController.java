package com.semafoor.petclinic.customersservice.web.controller;

import com.google.common.collect.Lists;
import com.semafoor.petclinic.customersservice.model.Pet;
import com.semafoor.petclinic.customersservice.model.PetType;
import com.semafoor.petclinic.customersservice.repository.OwnerRepository;
import com.semafoor.petclinic.customersservice.repository.PetRepository;
import com.semafoor.petclinic.customersservice.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PetController {

    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @GetMapping("/petTypes")
    public List<PetType> getPetTypes() {
        return Lists.newArrayList(petTypeRepository.findAll());
    }

    @PostMapping("/petTypes")
    @ResponseStatus(HttpStatus.CREATED)
    public PetType postPetType(@RequestBody PetType petType) {
        return petTypeRepository.save(petType);
    }

    @PostMapping("/owners/{ownerId}/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public Pet processPetCreation(@RequestBody PetDto petDto, @PathVariable UUID ownerId) {

        Pet pet = petTypeRepository.findById(petDto.getTypeId()).map(type -> {
            Pet newPet = new Pet();
            newPet.setType(type);
            return newPet;
        }).orElseThrow(() -> new ResourceNotFoundException("Pet type with id " + petDto.getTypeId()
                + " could not be found"));

        return ownerRepository.findById(ownerId).map(owner -> {

            pet.setBirthDate(petDto.getBirthDate());
            pet.setName(petDto.getName());
            Pet savedPet = petRepository.save(pet);
            owner.addPet(savedPet);
            ownerRepository.save(owner);
            return savedPet;
        }).orElseThrow(() -> new ResourceNotFoundException("Owner with id " + ownerId + " not found"));

    }

    @GetMapping("/pets")
    public List<Pet> findAll() {
        return Lists.newArrayList(petRepository.findAll());
    }

    @GetMapping("/owners/*/pets/{petId}")
    public ResponseEntity<Pet> findPet(@PathVariable UUID petId) {
        return petRepository.findById(petId).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Pet with id " + petId + " could not be found"));
    }

    @RequestMapping(value = "/pets/{id}", method = RequestMethod.HEAD)
    ResponseEntity<?> head(@PathVariable UUID id) {
        return this.petRepository.findById(id)
                .map(pet -> ResponseEntity.noContent().build())
                .orElseThrow(() -> new ResourceNotFoundException("Pet with id " + id + " could not be found"));
    }
}
