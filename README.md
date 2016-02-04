# Java RPN Calculator

This is an implementation for the RPN Calculator problem.
It is written in Java programming language version 1.8.x, it has  message internationalization, unit tests, code coverage and packaging support.

## Dependencies

The code relies on only one compilation dependency: Apache Commons Lang, it is used only to perform a clean check whether a string is numeric or not.

## Building the Project

mvn package

## Testing

mvn test

## Running the RPN Calculator

java -jar target/rpncalculator-1.0-SNAPSHOT-jar-with-dependencies.jar

## Code Coverage Reports

open target/site/jacoco/index.html
