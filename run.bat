@echo off
cls
echo Compiling Java files...

javac -cp ".;library\mysql-connector-j-9.3.0.jar" -d *.java

if errorlevel 1 (
    echo Compilation failed.
    pause
    exit /b
)

echo Running the application...
java -cp ".;library\mysql-connector-j-9.3.0.jar" Main
pause
