#!/bin/bash

javadoc -private -author -version -d ./doc -classpath ./bin ./src/*/*.java
