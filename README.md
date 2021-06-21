# QuizSpringProject

This is a Spring Boot Website, API and MySQL database that allows a user to create, store and use different sets of flashcards.

## Getting Started



## Testing
When using Maven/SonarQube to build, all tests should run automatically however, to run tests manually, run the specific test.java in src/test/java or the entire project with junit. This will find and then run all tests.

1. Run all tests: "mvn clean package" OR Eclipse -> QuizSpringProject -> Run As Junit Test
2. Ran Specific Tests: Eclipse -> Specific Test.java -> Run as Junit Test

The tests use Junit 5 (Jupiter), Mockito & Selenium
*All tests use h2 memory database and test on the data.sql data*

## Sonarqube
Sonarqube was used to help detect bugs and application security vulnerabilities. Sonarqube found 0 bugs, 4 vulnerabilities and 35 different code smells which can be fixed and refactored. I fixed the vulnerabilities and 29 code smells. A code smell and vulnerability sonarqube found is shown below.

final int prime = 31;
Declare this local variable with "var" instead.
public VehicleDTO addVehicle(@RequestBody Vehicle vehicle) {
Replace this persistent entity with a simple POJO or DTO object.
Deployment
The following steps create a jar file and run the application in the git bash window.

Open git bash in the application root folder

Type mvn clean package

Using git bash navigate to the target folder

Type java -jar HobbyProject-0.0.1-SNAPSHOT.jar


## All Technologies

- Version Control System: Git
- Source Code Management: GitHub
- Kanban Board: Jira
- Database Management System: MySQL (GCP)
- Back-End Programming Language: Java
- API Development Platform: Spring
- Front-End Web Technologies: HTML, CSS, JavaScript
- Build Tool: Maven
- Static Analysis: SonarQube
- Unit Testing: Junit, Mockito
- User-Acceptance Testing: Selenium

## License
This project is licensed under the MIT license - see the LICENSE.md file for details
