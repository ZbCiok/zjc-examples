
## ddb-delete-table-example-01
### ___with Java AWS SDK v2___

## Description

This example's purpose is to support the LocalStack on using the platform with Java applications.

## Prerequisites

- [Maven 3.8.5+](https://maven.apache.org/install.html) & [Java 17](https://www.java.com/en/download/help/download_options.html)
- [LocalStack](https://localstack.cloud/)
- [Docker](https://docs.docker.com/get-docker/) - for running LocalStack
- [AWS CLI](https://aws.amazon.com/cli/) and [awslocal](https://docs.localstack.cloud/user-guide/integrations/aws-cli/#localstack-aws-cli-awslocal)

## Running and testing the example

```
mvn clean package
```

```
mvn exec:java -Dexec.mainClass="v2.dynamodb.DynamoDBService"
```


