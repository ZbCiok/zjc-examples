package v2.dynamodb;

import java.net.URI;
import java.text.ParseException;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;


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
    deleteTable(dynamoDbClient, "person");
  }

  public static void deleteTable(DynamoDbClient ddb, String tableName) {
    DeleteTableRequest request = DeleteTableRequest.builder()
            .tableName(tableName)
            .build();

    try {
      ddb.deleteTable(request);

    } catch (DynamoDbException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    System.out.println(tableName + " was successfully deleted!");
  }
}
