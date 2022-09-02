 # API TESTING FRAMEWORK

## About:
This project contains a framework for API Testing and test suite for pet store APIs.
`https://petstore3.swagger.io/`

## Prerequisites:
1. Java 11
2. Maven 3.10.1

## Tools and Libraries used
1. Rest Assured
2. Testng
3. Allure reports
4. SonarLint

## Setup

Clone the repository to your workspace

### To Run test suit:
1. Open editor and run testNg.xml OR
2. Run the following Maven command from command prompt

   `mvn clean test -DsuiteXmlFile=testNg.xml`

### To view reports
Run the following allure command in command prompt

`cd path to repo`

`mvn allure:serve -DresultsDirectory={Path to the repo}\petStore\src\test\Reports\allure-results`


### Framework Structure Details

- petStore\src\main\java\com\constants : This folder contains all the frequently used values stored for easy reuse and refactoring.
- petStore\src\main\java\com\listeners : This folder contains listeners that will listen to the test execution, rerun if a test case fails to check if it is flaky.
- petStore\src\main\java\com\pojo : This folder contains the pojo class that was used for creating a builder pattern. This comes handy when user wants to pass data throgh chained method call to an api call.
we need to pass only those parameters that has value. Optional parameters can be skipped.
- petStore\src\main\java\com\utils\BusinessFlows.java: This class contains the business logic and validation for the API calls.
- petStore\src\main\java\com\utils\CommonUtil.java : This class contains utility methods used.
- petStore\src\main\java\com\utils\ConfigPropertyReader.java : This class defines the method used for read config.property file.
- petStore\src\main\java\com\utils\RequestManager.java : This class contains the rest assured implementation of the CRUD operations
- petStore\src\main\resources : This folder contains settings for allure report, log4j and config.properties file
- petStore\src\test\java\com\pet\Pets.java: Contains the test cases for pet and purchase scenarios.One assertion has been intentionally failed to demo the failure in the test report.
The test cases have been configured to run based on the testNg priority. For the sake of demo, One test case employs conventional JSONObject generation of the request body while the second test case depends on builder pattern to generate the request body. Feature and step annotation have been added as part of allure report. 
- petStore\src\test\logs : This folder contains the test log.
- petStore\pom.xml: This file contains all the maven dependencies and plugins
- petStore\testNg.xml: This file contains the details of the test suit to be run,listener added etc.
- SonarLint plugin was used to fix the code quality issues.

### Advantages of the selected tech stack
- Rest Assured is very flexible in terms of customization. It comes with pre defined functions to deal with parameterization, content types, even reading from files for request body.
It also supports encoderconfig which has the capability to encode the body of a request based on requirements.Also, the given when then language makes the code more readable.
- TestNg framework is also highly configurable starting from which test case to run,to setting priorities for the run, parameterizing them or even reading from external files. The listeners helps us track the execution and also supports its own test report
- Allure report has a very good user interface and has various sections for displaying the status of a test run. This can be separated based on epics, features etc. The tests can be marked as flaky, categories can be added for defects. 