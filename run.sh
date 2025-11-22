#!/bin/bash

echo "========================================"
echo "  Aquarium Multithreading Simulation"
echo "========================================"
echo ""
echo "Building project..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo ""
echo "Running application..."
mvn exec:java -Dexec.mainClass="com.aquarium.AquariumApp"

