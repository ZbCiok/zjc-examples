# Amazon AWS SDK S3 Example 

Spring Boot project that interacts with Amazon S3 buckets over the official Java Amazon AWS SDK.
(Optional is possible to interact with a local AWS setup using LocalStack (pay attention to the used Docker image!).

It's using following stuff:
- Java, version 17
- Maven
- Spring Boot, version 3
- Amazon SDK for Java, version 2

Before you run the project, please consider changing the properties in `resources/application.properties`.

This command could be helpful to run the application:
```
./mvnw spring-boot:run
```

If you want to make use of LocalStack just run this command:
```
docker compose -f docker-compose.yml up
```
(Please check out the default environment variables.)

After you can interact with the application using Swagger UI http://localhost:8080/swagger-ui/index.html. A basic overview of all buckets can be viewed with help of http://localhost:8080/buckets.

This project is based on the Medium article https://mmarcosab.medium.com/how-about-s3-bucket-and-localstack-b0816bab452a and relating GitHub project https://github.com/mmarcosab/s3-example.

## S3 bucket
Create S3 bucket:
```
awslocal s3api create-bucket --bucket bucket-example
```

List buckets:
```
awslocal s3api list-buckets
```

Delete bucket:
```
awslocal s3api delete-bucket --endpoint-url http://localhost:4566 --region eu-central-1 --bucket bucket-example
```

List the bucket content:
```
awslocal --endpoint-url=http://localhost:4566 s3 ls s3://bucket-example/
```

Upload object:
```
awslocal --endpoint-url=http://localhost:4566 s3 cp ./tmp-data/example-1.png s3://bucket-example
awslocal --endpoint-url=http://localhost:4566 s3 cp ./tmp-data/example-2.png s3://bucket-example
```
http://localhost:8080/buckets
http://localhost:8080/buckets/bucket-example/objects