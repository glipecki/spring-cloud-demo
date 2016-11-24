#!/usr/bin/env bash

trap ctrl_c INT

set -e

SERVICE_PIDS=""
DISCOVERY_PORT=9201
GATEWAY_PORT=9100
GATEWAY_STATIC_PORT=9199
ZIPKIN_PORT=9202
TURBINE_PORT=9203
HYSTRIX_PORT=9204

function start {
 echo "Staring $1 (port=$2, instanceId=$3)..."
 nohup java -jar $1/target/$1-*-SNAPSHOT.jar \
 	--server.port=$2 \
 	--spring.application.instance_id=$3 \
 	--discovery.server=http://localhost:$DISCOVERY_PORT/eureka \
 	--spring.sleuth.sampler.percentage=1 \
 	--turbine.stream.port=$TURBINE_PORT \
 	&> logs/$1.$3.log&
 PID=`ps aux | grep $1 | grep $2 | head -n 1 | awk '{print $2}'`
 echo "...started with logs in logs/$1.log and PID: $PID"
 SERVICE_PIDS="$SERVICE_PIDS $PID"
 echo
}

function ctrl_c {
 echo "Killing services ($SERVICE_PIDS)"
 kill -9 $SERVICE_PIDS
 rm -rf logs/
}

echo "Building..."
bash mvnw clean package -DskipTests > /dev/null && echo "...ok" || ( echo "...fail, run: bash mvnw clean package"; exit 1 )
echo

echo "Cleaning logs..."
if [ -d logs ]; then
  rm -rf logs/
fi
mkdir logs
echo

echo "Starting apps..."
echo
start srv-01 9101 1
start srv-02 9102 1
start srv-03 9103 1
start srv-gateway-static $GATEWAY_STATIC_PORT 1
start srv-discovery $DISCOVERY_PORT 1
start srv-gateway $GATEWAY_PORT 1
start srv-zipkin $ZIPKIN_PORT 1
start srv-hystrix $HYSTRIX_PORT 1


echo
echo "Running services:"
echo "- service discovery dashboard: http://localhost:$DISCOVERY_PORT/"
echo "- service static gateway: http://localhost:$GATEWAY_STATIC_PORT/"
echo "- service gateway: http://localhost:$GATEWAY_PORT/"
echo "- zipkin dashboard: http://localhost:$ZIPKIN_PORT/"
echo "- hystrix dashboard: http://localhost:$HYSTRIX_PORT/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A$TURBINE_PORT%2F&title=Cloud%20demo"
echo "  - turbine stream: http://localhost:$TURBINE_PORT/"
echo

echo
echo "Waiting for ctrl+c"
echo
cat