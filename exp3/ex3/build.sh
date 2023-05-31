#!/bin/bash 

mkdir -p bin

javac ./src/exceptions/*.java -d ./bin
javac ./src/ast/*.java -d ./bin
javac -classpath ./javacup/java-cup-11b-runtime.jar:./bin:./lib/jgraph.jar:./lib/callgraph.jar ./src/*.java -d ./bin
