#!/bin/bash

echo "Compiling..."
javac -d ./bin -classpath ./bin ./src/*/*.java
echo "Success"