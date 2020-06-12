#!/bin/bash

cd ../card-service
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
