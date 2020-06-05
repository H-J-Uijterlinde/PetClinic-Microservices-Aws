package com.semafoor.petclinic.vetsservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Specialties")
@DynamoDBDocument
public class Specialty {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String name;
}
