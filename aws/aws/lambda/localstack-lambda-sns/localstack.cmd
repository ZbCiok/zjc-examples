Lambda:
-------
awslocal lambda create-function \
    --region us-east-1 \
    --function-name localstack-lambda-sns \
    --runtime java21 \
    --zip-file fileb://localstack-lambda-sns-0.0.1.jar \
    --handler com.example.SnsRequestHandler \
    --role arn:aws:iam::000000000000:role/example-lambda-noop-role \
    --timeout 120

awslocal lambda list-functions


Topic:
------
awslocal sns create-topic --name example-topic --region us-east-1
awslocal sns list-topics


Subscription:
------------
awslocal sns subscribe \
--region us-east-1 \
--protocol lambda \
--topic-arn arn:aws:sns:us-east-1:000000000000:example-topic \
--notification-endpoint arn:aws:lambda:us-east-1:000000000000:function:localstack-lambda-sns

awslocal sns list-subscriptions


Publish an event:
-----------------
awslocal sns publish \
--region us-east-1 \
--topic-arn arn:aws:sns:us-east-1:000000000000:example-topic \
--message "Hello Lambda..........!"



Verify:
-------
docker logs localstack-main







