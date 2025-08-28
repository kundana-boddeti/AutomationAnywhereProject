macos 10.14
selenium version 4.20.0
chrome version 116
RestAssured
POM framework

properties:
baseURL
user
password
browser
headless
added to config.properties

commands to run the tests
mvn clean test

Scenario1: message box ui automation
completed end to end flow

Scenario2: form upload ui automation
Following issues observed
1)Upload button not enabled in the form to test out the upload functionality.
2)Multiple widgets could not be put in the form through automation
used the following options:
1)actions drapanddrop-> not working
2)actions clickandhold -> not working
3)js script ->not working 

only one widget could be added.

Rest end point:
was able to get endpoint only for login from the network tab


git clone https://github.com/kundana-boddeti/AutomationAnywhereProject.git
cd <source directory of project>
Replace the user and password in config.properties
mvn clean test





