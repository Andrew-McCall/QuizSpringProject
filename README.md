# QuizSpringProject

This is a Spring Boot Website, API and MySQL database that allows a user to create, store and use different sets of flashcards.

## Getting Started

For each, This project used the most recent release as of (15/06/21).
Other Versions should work but have not been tested.

 - Maven 3.8.1 - [Download](https://mirrors.ukfast.co.uk/sites/ftp.apache.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip)
 - Eclipse - [Download](https://www.eclipse.org/downloads/)
 - MySQL-Workbench - [Download](https://www.mysql.com/products/workbench/)
 - SonarQube - [Download](https://www.sonarqube.org/?gads_campaign=Europe-4-DSA-SonarQube&gads_ad_group=DSA&gads_keyword=&gclid=CjwKCAjw8cCGBhB6EiwAgORey3WqaDt66CWdu4s3VNqBTLe4S-wH6IJXg5HBJY2ApQBE6IrnV22QJhoCP78QAvD_BwE])

If you wish to use another database then the default GCP or testing memory based h2, 
Change Application-production.properties (for production properties)

```
spring.datasource.url=jdbc:mysql: New Url
spring.datasource.username= New Username
spring.datasource.password= New Password
```
Change Application-testing.properties (for testing properties)
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.path=/h2-console
spring.datasource.username= New Username
spring.datasource.password= New Password
```
*The testing-data.sql & testing Schema-sql can also be updated if testing requires new data (the .sql must be in h2 sql)*


## Testing
When using Maven/SonarQube to build, all tests should run automatically however, to run tests manually, run the specific test.java in src/test/java or the entire project with junit. This will find and then run all tests.

1. Run all tests: "mvn clean package" OR Eclipse -> QuizSpringProject -> Run As Junit Test
2. Ran Specific Tests: Eclipse -> Specific Test.java -> Run as Junit Test

The tests use Junit 5 (Jupiter), Mockito & Selenium
*All tests use h2 memory database and test on the data.sql data*

## Sonarqube
Sonarqube is a tool used to perform automatic reviews/analysis of code to detect bugs, code smells, and security vulnerabilities.

The maven pom is setup to send all builds to the local sonarqube server (localhost:9000).
Use "mvn clean package" to build, test and sonarqube the project.

*Sonarqube test coverage has been enabled so coverage is shown on the report*


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
