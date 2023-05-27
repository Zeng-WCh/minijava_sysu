#!/bin/bash

javac -d bin ./src/exceptions/*.java
javac -d bin --source-path ./src ./src/OberonScanner.java