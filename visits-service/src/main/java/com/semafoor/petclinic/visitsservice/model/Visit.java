package com.semafoor.petclinic.visitsservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "Visits")
public class Visit {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private UUID petId;
}
