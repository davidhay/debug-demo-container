#!/bin/bash

if [  $# -ne 1 ]; then
  echo "usage ./copyTxtFilesFromContainer.sh <directory-on-localhost>"
  exit 1
fi
export DIR=$1

if [ ! -d "$DIR" ]; then
    echo "$DIR is NOT a directory."
    exit 1
fi
if [ ! -w "$DIR" ]; then
    echo "$DIR is NOT writeable."
    exit 1
fi

CONTAINER_ID=$(docker container ls --all --quiet --filter "name=^the-demo-container$")
if [[ -z $CONTAINER_ID ]]; then
  echo "No docker container running with name='the-demo-container'"
else
  echo "copying files from container[/tmp/*.txt] to localhost[$DIR]"
  docker exec $CONTAINER_ID /bin/bash -c 'cd /tmp && tar cf - *.txt' | ( cd $DIR && tar xvf - )
fi

