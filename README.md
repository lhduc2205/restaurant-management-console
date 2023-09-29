# Restaurant Management Console App

Restaurant Management Console App is a text-based application designed to manage restaurant menus and orders.

## Features
- CRUD Operations for Menus: Create, Read, Update, and Delete restaurant menus.
- CRUD Operations for Orders: Create, Read, Update, and Delete restaurant orders.
- Data Persistence: Read and store data to CSV and JSON files.


## Getting started

To compile and run this application, you can use Maven, a popular build and dependency management tool.
Follow these step:

1. **Clone the Repository**: Start by cloning this repository to your local machine.
```shell
git clone https://gitlab.tma.com.vn/dc4-resource-java-upskill/lhduc/restaurant-management.git
```
2. **Navigate to the Project Directory**: Move into the project's root directory
```shell
cd restaurant-management-console
```
3. **Compile and Run**: Use the following Maven command to clean, compile, and execute the application.
```shell
mvn clean compile exec:java
```
This command will compile the source code and run the application, launching the Restaurant Management Console App.


## Build JAR file

To build the JAR file of the restaurant management console application, you need to use Maven. 
Make sure you have Maven installed on your system. 
If not, you can download and install it from https://maven.apache.org/.

1. Open your terminal or command prompt.
2. Navigate to the root directory of the project where the pom.xml file is located.
3. Run the following Maven command to clean and package the project:

```shell
mvn clean package
```
This command will compile the source code, run tests, and package the application into a JAR file with its dependencies included.


## Run the Application using JAR file 

Once you have successfully built the JAR file, you can run the restaurant management console application using the following steps:

1. Navigate to the `target` directory within current project.
```shell
cd target/
```

2. Run the JAR file using the `java -jar` command:
```shell
java -jar restaurant_management_console-1-jar-with-dependencies.jar
```
The application will start, and you will be presented with the console interface to interact with the restaurant management features.
