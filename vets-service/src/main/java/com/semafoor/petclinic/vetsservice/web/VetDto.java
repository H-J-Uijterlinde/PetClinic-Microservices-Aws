package com.semafoor.petclinic.vetsservice.web;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class VetDto {

    private String firstName;
    private String lastName;
    private Set<UUID> specialtyIds;
}
