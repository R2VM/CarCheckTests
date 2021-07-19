# CarCheckTests Automation Tests
This project contains automation tests for cartaxcheck.co.uk

### Tools Used
- Java
- Selenium
- Webdrivermanager
- opencsv
- Junit
- Maven

### How to run tests using maven
- mvn clean test (runs tests in dev environment)
- mvn clean test -Denvironment=qa (runs tests in qa environment)
- mvn clean test -Denvironment=prod (runs tests in production environment)

### How to run tests using IDE
Run com.car.CarCheckTest (runs tests in dev environment)
Run com.car.CarCheckTest with system property "-Denvironment=qa" (runs tests in qa environment)

### How to check test reports
- Run: mvn clean surefire-report:report
- Test reports can be found at: {ProjectHome}/target/site/surefire-report.html

### Improvements todo
- Enhance project to run tests on other browsers Ex: IE, Firefox, Android Mobile Web, iPhone mobile web
- Add browserstack or saucelab support
- Send notifications to Slack or Teams channel incase build fails
- Run tests against local selenium grid (Use docker)
