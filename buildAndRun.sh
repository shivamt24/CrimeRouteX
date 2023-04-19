#!/bin/bash

# Create distributable package
mvn clean package
# Run JAR file
java -cp target/info6205-travelling-salesman-1.0-SNAPSHOT.jar edu.neu.info6205.algorithms.Christofides.Driver;