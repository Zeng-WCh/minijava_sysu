#!/bin/bash

rm src/OberonScanner.java
java -jar ./jflex/jflex-1.9.1.jar -d src src/oberon.flex
java -jar javacup/java-cup-11b.jar -interface -destdir src -parser Parser -symbols Symbol src/oberon.cup