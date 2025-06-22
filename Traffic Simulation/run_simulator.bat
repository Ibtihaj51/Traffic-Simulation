@echo off
set JAVA_FX_LIB="C:\javafx-sdk-24.0.1\lib"

echo Compiling...
javac --module-path %JAVA_FX_LIB% --add-modules javafx.controls -d out src\simulator\Main.java

echo Running...
java --module-path %JAVA_FX_LIB% --add-modules javafx.controls -cp out simulator.Main

pause
