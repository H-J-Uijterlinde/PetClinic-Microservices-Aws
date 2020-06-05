package com.semafoor.petclinic.customersservice.web.controller;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PetDto {

    private String name;
    private Date birthDate;
    private UUID typeId;
}
