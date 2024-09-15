package com.jreact.dynamodb;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
public class MusicEntity {
    public static final String TABLE_NAME = "Music";

    private String musicCode;
    private String musicDisplayName;

    @DynamoDbPartitionKey
    public String getMusicCode() {
        return musicCode;
    }
}
