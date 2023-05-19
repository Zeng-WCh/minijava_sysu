#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: ./ast.sh <expr>"
    exit 1
fi

java --class-path ./bin test $*