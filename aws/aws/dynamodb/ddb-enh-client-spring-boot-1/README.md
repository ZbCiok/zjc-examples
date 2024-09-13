# ddb-enh-client-spring-boot-1
## ___DynamoDB Enhanced Client Spring Boot___
<br />

### Create Table
```
awslocal dynamodb create-table \
    --table-name person \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
```