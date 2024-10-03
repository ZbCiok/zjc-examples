package v2.dynamodb;

import java.text.ParseException;
import java.net.URI;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.extern.java.Log;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Log
public class SavePersonHandler  implements RequestHandler<PersonRequest, PersonResponse> {

  private static final String ACCESS_KEY = "test";
  private static final String SECRET_KEY = "test";
  private static String TABLE_NAME = "person";
  private static AwsCredentialsProvider credentials = StaticCredentialsProvider.create(
      AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY));

  private static Region region = Region.US_EAST_1;

  private static DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
      .region(region)
      .credentialsProvider(credentials)
      .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
      .build();

// -----

  public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
    log.info("Start handleRequest >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    String personId1 = "300012356";
      try {
          addEntryToDynamoDB(personId1);
      } catch (Exception e) {
          log.info("Exception handleRequest >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + e.getMessage());
      }

    PersonResponse personResponse = new PersonResponse();
    personResponse.setMessage("Success >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add Entry Successfully!!!");
    return personResponse;
  }

  private static void addEntryToDynamoDB(String personID) throws ParseException {
    try {
      DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
              .dynamoDbClient(dynamoDbClient)
              .build();

      // create the person object
      PersonRequest person = new PersonRequest();
      person.setId(Integer.parseInt(personID));
      person.setFirstName("Doe");
      person.setLastName("John");
      person.setAge(Integer.parseInt("19790101"));
      person.setAddress("Address 01");

      // use the enhanced client to interact with the table
      DynamoDbTable<PersonRequest> table = enhancedClient.table(TABLE_NAME,
              TableSchema.fromBean(PersonRequest.class));
      table.putItem(person);

      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add Entry successfully");
    } catch (DynamoDbException exception) {
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add Entry an error occurred: " + exception.getMessage());
    }
  }

}
