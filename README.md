# exercise_106

## Getting started

To run this app you need to provide csv file name

> java -jar exercise-106-1.0.jar employees.csv


## Assumptions

Let's presume that the CSV file is already located in the resource directory, and all we need to do is pass the file name. 

Code in this app relies on next statements:
- file name will we passed as parameter during JAR execution
- column order in csv will be next [Id,firstName,lastName,salary,managerId]
- id in unique
- managerId can be null only in the case of the CEO, and there can be only one CEO.
