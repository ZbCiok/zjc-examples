package com.jreact.dynamodb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.text.ParseException;

@SpringBootApplication
@RequiredArgsConstructor
public class DynamoDBService  implements ApplicationRunner {

  @Autowired
  private DynamoDbEnhancedClient enhancedClient;

  public static void main(String[] args) throws ParseException {
    SpringApplication.run(DynamoDBService.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
      String personId = "1400012356";
      String TABLE_NAME = "person";
      DynamoDbTable<Person> table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Person.class));

      putItem(table, personId);
      getItem(table, personId);
  }

  private static void getItem(DynamoDbTable<Person> table, String personId) {
    Person person = table.getItem(Key.builder().partitionValue(personId).build());
    if (person != null) {
      System.out.println("Retrieved Person: " + person);
    } else {
      System.out.println("Person with ID " + personId + " not found.");
    }
  }

  private static void putItem(DynamoDbTable<Person> table, String personId) {
    try {
        Person person = new Person();
        person.setId(personId);
        person.setName("John Doe");
        person.setBirthdateFromString("1979-01-01");

        table.putItem(person);

    } catch (DynamoDbException | ParseException exception) {
        System.out.println("An error occurred: " + exception.getMessage());
    }
  }
}
