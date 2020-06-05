package com.semafoor.petclinic.customersservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Owner")
public class Owner {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    private String address;

    @DynamoDBAttribute
    private String city;

    @DynamoDBAttribute
    private String telephone;

    @DynamoDBAttribute
    private Set<Pet> pets;

    public void addPet(Pet pet) {

        if (pets == null) {
            pets = new HashSet<>();
        }

        this.pets.add(pet);
        pet.setOwnerId(this.id);
    }
}
