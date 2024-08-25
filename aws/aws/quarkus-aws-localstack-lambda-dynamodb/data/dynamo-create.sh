# "Creating table"
awslocal dynamodb create-table --cli-input-json file://create-dynamo-table-temperature.json --region us-east-1

# "Put Item"
awslocal dynamodb put-item --table-name TemperatureReport --item '{"id":{"N":"1"},"name":{"S":"Marketing"},"country":{"S":"Argentina"},"weeklyReports":{"L":[{"M":{"temperature":{"N":"11.5"},"description":{"S":"Sunny Day"},"time":{"S":"2024-01-03T10:15:30.00Z"}}}]}}' --region us-east-1


