package com.semafoor.petclinic.vetsservice.web;

import com.google.common.collect.Lists;
import com.semafoor.petclinic.vetsservice.model.Specialty;
import com.semafoor.petclinic.vetsservice.model.Vet;
import com.semafoor.petclinic.vetsservice.repository.SpecialtyRepository;
import com.semafoor.petclinic.vetsservice.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vets")
public class VetController {

    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;

    @GetMapping
    public ResponseEntity<List<Vet>> getAllVets() {
        return ResponseEntity.ok(Lists.newArrayList(vetRepository.findAll()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vet> postVet(@RequestBody VetDto vetDto) {
        Vet vet = new Vet();
        vet.setFirstName(vetDto.getFirstName());
        vet.setLastName(vetDto.getLastName());

        specialtyRepository.findAllById(vetDto.getSpecialtyIds()).forEach(vet::addSpecialty);
        return ResponseEntity.ok(vetRepository.save(vet));
    }

    @PostMapping("/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Specialty> postSpecialty(@RequestBody Specialty specialty) {
        return ResponseEntity.ok(specialtyRepository.save(specialty));
    }
}
