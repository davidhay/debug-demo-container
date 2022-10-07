#!/bin/bash

CONTAINER_ID=$(docker container ls --all --quiet --filter "name=^the-demo-container$")
if [[ -z $CONTAINER_ID ]]; then
  docker run -d --rm \
  -p 8282:8080  \
  -p 6666:5005 \
  -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" \
  --name the-demo-container \
  demo-container:latest
  echo "now attach debugger to port 6666 :-)"
else
  echo "Container with name='the-demo-container' already running!"
fi
