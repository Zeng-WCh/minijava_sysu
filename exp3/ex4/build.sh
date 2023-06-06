#!/bin/bash 

rm src/OberonScanner.java > /dev/null 2>&1
java -jar ./jflex/jflex-1.9.1.jar -d src src/oberon.flex

mkdir -p bin

javac ./src/exceptions/*.java -d ./bin
javac -classpath ./lib/flowchart.jar:./bin:./lib/jgraph.jar:./lib/callgraph.jar ./src/ast/*.java -d ./bin
javac -classpath ./lib/flowchart.jar:./bin:./lib/jgraph.jar:./lib/callgraph.jar ./src/*.java -d ./bin
