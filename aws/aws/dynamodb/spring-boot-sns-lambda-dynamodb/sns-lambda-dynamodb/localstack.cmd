Lambda:
-------
awslocal lambda create-function \
    --region us-east-1 \
    --function-name localstack-lambda-sns \
    --runtime java21 \
    --zip-file fileb://sns-trigger-lambda-0.0.1.jar \
    --handler com.example.SnsRequestHandler \
    --role arn:aws:iam::000000000000:role/example-lambda-noop-role \
    --timeout 120

awslocal lambda list-functions
awslocal lambda delete-function \
    --function-name localstack-lambda-sns


Topic:
------
awslocal sns create-topic --name localstack-topic --region us-east-1
awslocal sns list-topics


Subscription:
------------
awslocal sns subscribe \
--region us-east-1 \
--protocol lambda \
--topic-arn arn:aws:sns:us-east-1:000000000000:localstack-topic \
--notification-endpoint arn:aws:lambda:us-east-1:000000000000:function:localstack-lambda-sns

awslocal sns list-subscriptions


Publish an event:
-----------------
awslocal sns publish \
--region us-east-1 \
--topic-arn arn:aws:sns:us-east-1:000000000000:example-topic \
--message "Hello Lambda..........!"


dynamodb:
---------
awslocal dynamodb create-table \
    --table-name person \
    --key-schema AttributeName=id,KeyType=HASH \
    --attribute-definitions AttributeName=id,AttributeType=N \
    --billing-mode PAY_PER_REQUEST

awslocal dynamodb list-tables \
    --region eu-central-1

awslocal dynamodb delete-table \
    --table-name person

 awslocal dynamodb query \
     --table-name person \
     --key-condition-expression "id = :id" \
     --expression-attribute-values  '{":id":{"N":"000000001"}}'



Verify:
-------
docker logs localstack-main







