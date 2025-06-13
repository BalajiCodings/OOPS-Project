@echo off
javac -cp ".;library\mysql-connector-j-9.3.0.jar" *.java
java -cp ".;library\mysql-connector-j-9.3.0.jar" Main
pause
