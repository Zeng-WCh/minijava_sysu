#!/bin/bash

mkdir -p doc
mkdir -p doc/exceptions

javadoc -private -author -version -d doc ./src/exceptions/*.java
javadoc -author -version -classpath bin -d doc ./src/*.java