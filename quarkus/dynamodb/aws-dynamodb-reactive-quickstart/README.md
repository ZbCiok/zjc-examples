# aws-dynamodb-reactive-quickstart

This example showcases how to use the AWS DynamoDB client with Quarkus. As a prerequisite install [AWS Command line interface](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html).

### DynamoDB local instance

Run:
```
docker run -d -p 8000:8000 --name my-dynamodb amazon/dynamodb-local
```

DynamoDB listens on `localhost:8000` for REST endpoints.

Create an AWS profile for your local instance using AWS CLI:

```
$ aws configure --profile localstack
AWS Access Key ID [None]: your test-key
AWS Secret Access Key [None]: your test-secret
Default region name [None]: [e.g. us-east-1]
Default output format [None]: json
```

### Create table

Create a DynamoDB table using AWS CLI and the localstack profile.
```
aws dynamodb create-table --table-name QuarkusFruits \
                          --attribute-definitions AttributeName=fruitName,AttributeType=S \
                          --key-schema AttributeName=fruitName,KeyType=HASH \
                          --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
                          --profile localstack --endpoint-url=http://localhost:8000
```

### Run example:

- `./mvnw clean package -DskipTests`
- `java -jar ./target/quarkus-app/quarkus-run.jar`


Go to [`http://localhost:8080/reactive-fruits.html`](http://localhost:8080/fruits.html), it should show a simple App to manage a list of Fruits. 
You can add fruits to the list via the form.

