#!/usr/bin/env bash

set -e

PORT=9090
SERVICE=basic-srv-a

echo "Building..."
bash mvnw clean package -DskipTests -pl basic-srv-b -am > /dev/null && echo "...ok" || ( echo "...fail, run: bash mvnw clean package"; exit 1 )

echo "Starting on port $PORT..."
java -jar $SERVICE/target/$SERVICE-*-SNAPSHOT.jar --server.port=$PORT
