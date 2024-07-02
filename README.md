# ADSS_Group_AT - HR Module
## Submitted by:
Asaf Zenou    209263367

Hagai Astrin  211627690

## Overview
To activate it, please ensure you have downloaded the RELEASE folder and that it contains ```adss2024_v02.jar```, 
and also that the DEV folder exists.

Inside the folder, run the command: ```java -jar adss2024_v02.jar```

First, we will choose which supermarket branch we selected - the main branch is the ```Be'erSheva``` branch.
Then, we will load the data from the DB.

Now we are in the login menu, and we will choose whether we are the administrator or a regular supermarket employee.

If we are in the administrator menu, we can choose from:
* Add new employee
* Remove employee
* Update employee details
* Manage Shifts (The acutal shift assignment)
* Print all employees

If we are in the employee menu, we can choose from:
* View personal details
* View preferences
* Update preferences


## Project Structure
```
├── Data
│   ├── Be'erSheva.db
│   ├── Haifa.db
│   └── Test_Data.db
├── Domain
│   ├── Constants.java
│   ├── Contract.java
│   ├── dayTypeEnum.java
│   ├── Employee.java
│   ├── IO_Data.java
│   ├── JobTypeEnum.java
│   ├── JobWeeklyShift.java
│   ├── SHA_256_Hasher.java
│   ├── ShiftType.java
│   ├── WeeklyShift.java
│   └── Controller
│       ├── AdminController.java
│       ├── DataController.java
│       ├── EmployeeController.java
│       └── SystemController.java
├── Presentation
│   ├── AdminMenu.java
│   ├── Menu.java
│   ├── Printer.java
│   ├── Program.java
│   ├── ProgressBar.java
│   ├── Sender.java
│   └── UserMenu.java
└── Test
    ├── PrinterTest.java
    ├── SenderTest.java
    ├── Test_DB.java
    ├── Test_Employees.java
    ├── Test_JobWeekly_Shift.java
    └── Test_SHA256_Hasher.java
```
#### Dependencies
List the main tools and libraries used in the project.
```
Java: JDK version X.X
JUnit: for testing
SQLite: for database management
Gson: for JSON parsing (com.google.gson.JsonArray, com.google.gson.JsonObject)
java.io: for input and output operations (java.io.*, java.io.BufferedWriter, java.io.ByteArrayOutputStream, java.io.FileWriter, java.io.IOException, java.io.PrintStream)
java.security: for security operations (java.security.MessageDigest, java.security.NoSuchAlgorithmException)
java.sql: for SQL operations (java.sql.*)
java.time: for date and time operations (java.time.LocalDate, java.time.format.DateTimeFormatter, java.time.format.DateTimeParseException)
java.util: for utility classes (java.util.*, java.util.ArrayList, java.util.Arrays, java.util.HashMap, java.util.List, java.util.Map, java.util.Objects, java.util.Scanner)
```

## Illustration
Asaf and Hagai coding all night on their HR management system project, surrounded by computers and coffee:
![Asaf and hagai](https://github.com/HagaiAstrin/ADSS_Group_AT/assets/68349855/53cdabf3-aaef-4f2a-b645-93a10716ff9a)
