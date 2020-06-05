package com.semafoor.petclinic.vetsservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Vets")
public class Vet {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    private Set<Specialty> specialties;

    public void addSpecialty(Specialty specialty) {

        if (specialties == null) {
            specialties = new HashSet<>();
        }

        specialties.add(specialty);
    }
}
