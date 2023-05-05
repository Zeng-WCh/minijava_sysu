@echo off
cd src
javac -d ..\bin -classpath ..\bin token\*.java
javac -d ..\bin -classpath ..\bin scanner\*.java
javac -d ..\bin -classpath ..\bin ast\*.java
javac -d ..\bin -classpath ..\bin parser\*.java
cd ..
pause
@echo on
