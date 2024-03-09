
# aws-sdk-java-v2-dynamodb-s3-1


| Environment      | [LocalStack](https://localstack.cloud/)   |
|------------------|-------------------------------------------|
| __Services__     | Amazon S3, DynamoDB                       |
| __Categories__   | JAVA AWS SDK                              |


## Description

This example is using the Java AWS SDK v2 ([AWS guide](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)). It describes an example use of S3 and DynamoDB.

## Prerequisites

- Maven 3.9 & Java 17
- [LocalStack](https://localstack.cloud/)
- Docker
- AWS CLI and awslocal

## Running the example

- Start LocalStack: _localstack start_.
- Execute: _create-dynamodb-table.sh_ and _create-s3-bucket.sh_.
- Execute: _mvn clean package_
- Run: _mvn exec:java -Dexec.mainClass="v2.dynamodb.DynamoDBService"_ - _(creates record in DynamoDB)_.
- Run: _mvn exec:java -Dexec.mainClass="v2.s3.S3Service"_ - _(creates S3 object)_.



