package com.jreact.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;


@Component
@RequiredArgsConstructor
public class MusicRepository {
    private final DynamoDbTable<MusicEntity> table;

    public List<MusicEntity> getAll() {
        return table.scan().items().stream().toList();
    }
}
