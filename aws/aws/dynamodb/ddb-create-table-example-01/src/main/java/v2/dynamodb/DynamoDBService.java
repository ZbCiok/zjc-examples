package v2.dynamodb;

import java.net.URI;
import java.text.ParseException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;


public class DynamoDBService {

  // credentials that can be replaced with real AWS values
  private static final String ACCESS_KEY = "test";
  private static final String SECRET_KEY = "test";
  private static String TABLE_NAME = "person";
  private static AwsCredentialsProvider credentials = StaticCredentialsProvider.create(
      AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY));

  // create the dynamoDB client using the credentials and specific region
  private static Region region = Region.US_EAST_1;

  // create a dynamoDB client
  private static DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
      .region(region)
      .credentialsProvider(
          credentials)
      .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
      .build();

  public static void main(String[] args) throws ParseException {
    createTable(dynamoDbClient, "person", "id");
  }

  public static String createTable(DynamoDbClient ddb, String tableName, String key) {
    DynamoDbWaiter dbWaiter = ddb.waiter();
    CreateTableRequest request = CreateTableRequest.builder()
            .attributeDefinitions(AttributeDefinition.builder()
                    .attributeName(key)
                    .attributeType(ScalarAttributeType.S)
                    .build())
            .keySchema(KeySchemaElement.builder()
                    .attributeName(key)
                    .keyType(KeyType.HASH)
                    .build())
            .provisionedThroughput(ProvisionedThroughput.builder()
                    .readCapacityUnits(10L)
                    .writeCapacityUnits(10L)
                    .build())
            .tableName(tableName)
            .build();

    String newTable;
    try {
      CreateTableResponse response = ddb.createTable(request);
      DescribeTableRequest tableRequest = DescribeTableRequest.builder()
              .tableName(tableName)
              .build();

      // Wait until the Amazon DynamoDB table is created.
      WaiterResponse<DescribeTableResponse> waiterResponse = dbWaiter.waitUntilTableExists(tableRequest);
      waiterResponse.matched().response().ifPresent(System.out::println);
      newTable = response.tableDescription().tableName();
      return newTable;

    } catch (DynamoDbException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    return "";
  }
}
