package zjc.examples.quarkus.aws.temperature.report.control;

import zjc.examples.quarkus.aws.temperature.report.boundary.InputObject;
import zjc.examples.quarkus.aws.temperature.report.boundary.OutputObject;
import zjc.examples.quarkus.aws.temperature.report.entity.OfficeReport;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class TemperatureReportStore {

    public static final String OFFICE_MUST_BE_CHOOSEN = "Office Id must be given";

    @ConfigProperty(name = "DDB_TABLE")
    String DDB_TABLE;

    @Inject
    DynamoDbClient dynamoDbClient;

    DynamoDbEnhancedClient enhancedClient;

    DynamoDbTable<OfficeReport> officeReportTable;

    @PostConstruct
    void init() {
        enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
        officeReportTable = enhancedClient.table(DDB_TABLE, TableSchema.fromClass(OfficeReport.class));
    }

    public OutputObject getOfficeTemperatureReport(final InputObject input) {

        if (input.getOfficeId() <= 0) {
            throw new IllegalArgumentException(OFFICE_MUST_BE_CHOOSEN);
        }

        final OfficeReport officeReport = officeReportTable.getItem(Key.builder().partitionValue(input.getOfficeId()).build());
        OutputObject out = new OutputObject();
        out.setOfficeReport(officeReport);
        return out;
    }
}
