package com.semafoor.petclinic.customersservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Types")
@DynamoDBDocument
public class PetType {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String name;
}
