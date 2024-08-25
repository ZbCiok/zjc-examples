# spring-boot-with-aws-s3-1


| Environment      | [LocalStack](https://localstack.cloud/) |
|------------------|-----------------------------------------|
| __Services__     | LocalStack, Amazon S3                   |
| __Categories__   | Spring Boot, JAVA AWS SDK               |


## Description
This example is using the Spring Boot and Java AWS SDK v2 ([AWS guide](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html)). It describes an example use of S3.

## Prerequisites

- Maven 3.9+ & Java 17+
- Spring Boot
- [LocalStack](https://localstack.cloud/)
- Docker
- AWS CLI and awslocal

## Running the example
Create S3 bucket in LocalStack
```shell
Run: Docker Desktop
Run: localstack start
Run localstack-script/script.sh:

awslocal s3api create-bucket \
--bucket mybucket
```

Copy file to s3 bucket on localstack
```shell
Go to localstack-script and do:

awslocal s3 cp \
samplefile.txt \
s3://mybucket
```

S3 list
```shell
awslocal s3 ls s3://mybucket
```

---

#### Description: ___https://jreact.com/index.php/2024/03/13/spring-boot-with-aws-s3-1/___