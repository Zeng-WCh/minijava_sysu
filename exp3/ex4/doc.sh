#!/bin/bash

mkdir -p doc
mkdir -p doc/exceptions

javadoc -classpath ./lib/flowchart.jar:./bin:./lib/jgraph.jar:./lib/callgraph.jar -private -author -version -d doc ./src/exceptions/*.java
javadoc -classpath ./lib/flowchart.jar:./bin:./lib/jgraph.jar:./lib/callgraph.jar -author -version -classpath bin -d doc ./src/*.java