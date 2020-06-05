package com.semafoor.petclinic.authservice;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Clients")
public class Client {

    @DynamoDBHashKey
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private UUID id;

    @DynamoDBAttribute
    private String clientId;

    @DynamoDBAttribute
    private String secret;

    private String scopes = StringUtils.arrayToCommaDelimitedString(new String[]{"openid"});

    private String authorizedGrantTypes = StringUtils.arrayToCommaDelimitedString(new String[]{
            "authorization_code", "refresh_token", "password"
    });

    private String authorities = StringUtils.arrayToCommaDelimitedString(new String[]{
            "ROLE_USER", "ROLE_ADMIN"
    });

    private String autoApproveScopes = StringUtils.arrayToCommaDelimitedString(new String[]{".*"});

    public Client(UUID id, String secret) {
        this.id = id;
        this.secret = secret;
    }
}
