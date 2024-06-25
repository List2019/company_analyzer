# exercise_106

## Getting started

### Build project

In order to build project, navigate to the root project directory and run
> mvn clean package

This will create JAR artefact in target directory, which could be executed with the following command
> java -jar exercise-106-1.0.jar employees.csv


## Assumptions

This application makes the following assumptions:

1. The CSV file is already located in the resource directory, and the file name will be passed as a parameter during JAR execution.
2. The column order in the CSV file will be as follows: [Id, firstName, lastName, salary, managerId].
3. The 'Id' field is unique.
4. The 'managerId' field can be null, but only in the case of the CEO. There can be only one CEO in the dataset.
