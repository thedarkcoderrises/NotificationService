# NotificationService

Tech stack :
- spring-boot
- kafka
- AWS SNS
- Docker

Functionality : This service consumes events published by Ecart(CQRS) service on kafka topic and 
then further pushes notification events SMS and EMAIL to AWS arn 
