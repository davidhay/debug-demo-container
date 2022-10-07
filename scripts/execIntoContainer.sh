#!/bin/bash

CONTAINER_ID=$(docker container ls --all --quiet --filter "name=^the-demo-container$")
if [[ -z $CONTAINER_ID ]]; then
  echo "No docker container running with name='the-demo-container'"
else
  docker exec -it $CONTAINER_ID /bin/bash
fi