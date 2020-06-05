package com.semafoor.petclinic.customersservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Pets")
@DynamoDBDocument
public class Pet {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private Date birthDate;

    @DynamoDBAttribute
    private PetType type;

    @DynamoDBAttribute
    private UUID ownerId;
}
