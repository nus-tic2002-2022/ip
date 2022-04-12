# Developers Guide

## Setting Up
**Prerequisites**

* JDK 11
* IntelliJ IDE

### Importing the project into IntelliJ

1. Fork this repo, and clone the fork to your computer
2. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
3. Set up the correct JDK version
    - Click `Configure` > `Project Defaults` > `Project Structure`
    - If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
    - Click `OK`
4. IntelliJ IDEA has the Gradle plugin installed by default. If you have disabled it, go to File → Settings → Plugins to re-enable them.
5. Open the project into Intellij as follows:
    - Click `Open`
    - Locate the project directory and click `OK`
    - If there are any further prompts, accept the defaults.
6. Click OK to accept the default settings but do ensure that the selected version of Gradle JVM matches the JDK being used for the project.
7. Wait for the importing process to finish (could take a few minutes).

### Dependencies

Duke uses the Natty library for date processing  
Hence, the following libraries are required: 
```
natty-0.8.jar
ical4j-1.0.2.jar
antlr-runtime-3.2.jar
```
Create a new directory called `libs` and add them in.


### Gradle Support
* This project uses Gradle for build automation and dependency management.

### Testing

#### JUnit tests
* In IntelliJ, right-click on the test folder and choose Run 'All Tests'

### CheckStyles and Code Formatting
* CheckStyle rule configuration is provided at `config/checkstyle`