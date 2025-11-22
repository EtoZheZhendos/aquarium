@echo off
echo ========================================
echo   Aquarium Multithreading Simulation
echo ========================================
echo.
echo Building project...
call mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b %ERRORLEVEL%
)
echo.
echo Running application...
call mvn exec:java -Dexec.mainClass="com.aquarium.AquariumApp"
pause

