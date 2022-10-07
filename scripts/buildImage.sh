#!/bin/bash

SCRIPT_DIR=`dirname $0`
ROOT_DIR=$(cd $SCRIPT_DIR/../;pwd)
cd $ROOT_DIR
./mvnw spring-boot:build-image
