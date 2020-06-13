#!/bin/bash

cd ../microservices/card-service
mvn clean install -DskipTests -T 4
docker build -t card-service:v1 .

cd ../microservices/doing-service
mvn clean install -DskipTests -T 4 
docker build -t doing-service:v1 .

cd ../microservices/done-service
mvn clean install -DskipTests -T 4 
docker build -t done-service:v1 .

cd ../microservices/todo-service
mvn clean install -DskipTests -T 4
docker build -t todo-service:v1 .
