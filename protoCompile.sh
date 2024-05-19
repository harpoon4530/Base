#!/bin/bash

export SRC_DIR=./src/main/proto
export DST_DIR=./src/main/java

rm -rf src/main/java/org/base/protos/*.java
mkdir -p $DST_DIR

export PROTOC='protoc '

echo COMPILING THE PROTOCOL BUFFERS....!!!

ls -1 $SRC_DIR | xargs -I FILENAME $PROTOC -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/FILENAME

