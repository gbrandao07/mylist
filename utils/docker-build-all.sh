#!/bin/bash

cd ../microservices/card-service
mvn clean install -DskipTests -T 4
docker build -t card-service:v1 .

cd ../doing-service
mvn clean install -DskipTests -T 4 
docker build -t doing-service:v1 .

cd ../done-service
mvn clean install -DskipTests -T 4 
docker build -t done-service:v1 .

cd ../todo-service
mvn clean install -DskipTests -T 4
docker build -t todo-service:v1 .

cd ../webapp-mylist
ng build --prod
docker build -t webapp-mylist:v1 .

##################

docker tag card-service:v1 gbrandao/card-service:v1
docker push gbrandao/card-service:v1

docker tag doing-service:v1 gbrandao/doing-service:v1
docker push gbrandao/doing-service:v1

docker tag done-service:v1 gbrandao/done-service:v1
docker push gbrandao/done-service:v1

docker tag todo-service:v1 gbrandao/todo-service:v1
docker push gbrandao/todo-service:v1

docker tag webapp-mylist:v1 gbrandao/webapp-mylist:v1
docker push gbrandao/webapp-mylist:v1
