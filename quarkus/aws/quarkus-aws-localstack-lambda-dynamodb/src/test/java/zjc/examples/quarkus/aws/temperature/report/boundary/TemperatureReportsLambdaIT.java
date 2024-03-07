package zjc.examples.quarkus.aws.temperature.report.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import zjc.examples.quarkus.aws.temperature.report.entity.OfficeReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

@QuarkusTest
class TemperatureReportsLambdaIT extends BaseDynamoDBTestSupport {

    @Inject
    ObjectMapper objectMapper;

    DynamoDbEnhancedClient enhancedClient;
    DynamoDbTable<OfficeReport> officeReportDDBTable;

    @BeforeAll
    void setUp() {
        enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
        officeReportDDBTable = enhancedClient.table(ddbTable, TableSchema.fromClass(OfficeReport.class));
    }

    @Test
    void testOfficeReportRetrieved() throws Exception {

        final OfficeReport officeReport = saveOfficeReportItem("officeItemMarketing.json");

        InputObject in = new InputObject();
        in.setOfficeId(officeReport.getId());
        final OutputObject output = given().contentType("application/json")
                                           .accept("application/json")
                                           .body(in)
                                           .when()
                                           .post()
                                           .then()
                                           .statusCode(200)
                                           .extract()
                                           .as(OutputObject.class);

        MatcherAssert.assertThat(output, notNullValue());
        MatcherAssert.assertThat(output.getRequestId(), notNullValue());
        MatcherAssert.assertThat(output.getOfficeReport(), equalToObject(officeReport));
    }

    private OfficeReport saveOfficeReportItem(final String fileName) throws IOException {
        final String jsonAsText = readFile(fileName);
        OfficeReport officeReport = objectMapper.readValue(jsonAsText, OfficeReport.class);

        officeReportDDBTable.putItem(PutItemEnhancedRequest.<OfficeReport>builder(officeReport.getClass())
                                                         .item(officeReport)
                                                         .build());
        return officeReport;
    }

    private String readFile(final String fileName) throws IOException {
        try (final InputStream fis = getClass().getResourceAsStream(fileName)) {
            assert fis != null;
            return IOUtils.toString(fis, StandardCharsets.UTF_8);
        }
    }
}
