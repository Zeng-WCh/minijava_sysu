#!/bin/bash

rm src/OberonScanner.java

java -jar ./jflex/jflex-1.9.1.jar -d src src/oberon.flex $*