#!/bin/bash

echo "checking test-container http port is running at http://localhost:8282"
curl -s localhost:8282/actuator/health | jq

echo "checking test-container debug port is running at http://localhost:6666"
nc -zv localhost 6666
