# ADSS_Group_AT - Management System

## Submitted by:

### Transport Module
- **Yehonatan Segal: 209359801** - Developer
- **Omer Onn: 318910759** - Developer

### HR Module
- **Asaf Zenou: 209263367** - Developer
- **Hagai Astrin: 211627690** - Developer

## Overview

This is a Java-based Management System comprising two main modules:
1. **Transport Management System**: Managing transport operations, including assigning drivers and trucks to deliveries, tracking delivery statuses, and managing drivers, trucks, and delivery sites.
2. **HR Management System**: Managing employees, including adding, removing, updating employee details, managing shifts, and printing employee information.

## Table of Contents
- [Installation](#installation)
- [Features](#features)
- [System Requirements](#system-requirements)

## Installation

### Download the JAR file:
- Download the JAR file called `adss2024_v03.jar` for the application.

### Download the Database file:
- Download the `TransportatioDataBase` file.

### Setup Directories:
1. Create a directory named `dev`.
2. Inside the `dev` directory, create another directory named `Data`.
3. Place the `TransportatioDataBase` file inside the `Data` directory.
4. Your directory structure should look like this: `dev/Data/TransportatioDataBase`.
5. Put the JAR file and `dev` folder inside of a new folder.

### Run the JAR file:
1. Open a terminal or command prompt.
2. Navigate to the new folder, containing the JAR file and `dev` folder with the Database.
3. Run the JAR file using the following command:
   ```sh
   java -jar adss2024_v03.jar


Welcome to the "Super-Lee" system.
Choose an option:
1. Employee Management
2. Transport Management


## Transport System

### Main Menu
- Press "1" if you are a manager.
- Press "2" if you are a driver.
- Press "3" if you are an employee.
- Press "9" to exit.

### Manager Menu
To access the manager menu, enter the password: `123456789`. The manager menu includes the following options:
1. Add Driver
2. Add Truck
3. Add Store
4. Add Supplier
5. Create Transportation
6. Print Transportations

#### Add Driver
- Enter the driver's details – name, license level (A, B, C), and an 8-character password.
- If the details are valid, the driver is added to the driver list (Drivers Repository and DB).

#### Add Truck
- Enter the truck's details – license plate (8 characters in the format "000-00-000"), license level (A, B, C), net weight, and max weight.
- If the details are valid, the truck is added to the truck list (Trucks Repository and DB).

#### Add Site (Supplier/Store)
- Enter the site details – type (Store/Supplier), name, address, phone number, contact person, and shipping area.
- If the details are valid, the site is added to the site list (Sites Repository and DB).
- If the distribution area does not exist, it will be added to the list of shipping areas.

#### Create Transportation
- Enter the transportation details – leaving time, date, and source.
- Select a truck from the available trucks.
- Select a driver based on the selected truck.
- Choose a shipping area for the delivery.
- Choose whether to pick up products from a supplier or deliver products to a store.
- Select the sites in the chosen shipping area based on the type (store/supplier).
- Add products to the transportation from the supplier or remove products at the store.
- Confirm the transportation details and finalize the delivery.

  - If the total weight of the delivery is within the truck's weight capacity, the system will confirm that the delivery has been added.
  - If the total weight exceeds the truck's capacity, a window will open with options for the manager to adjust the delivery:
    - Change Site
    - Replace Truck
    - Remove Sites
    - Remove Items

#### Print Transportations
- View all deliveries that have ever been made and their statuses (departed, returned, pending).

### Driver Menu
To access the driver menu, enter your name and password. After verification, you will see:
1. A list of assigned deliveries.
2. A message wishing you a safe trip if you are currently on a delivery.
3. A message stating that you are waiting for an assignment if you have no current deliveries.

#### Driver Options
1. Report Leaving
2. Report Back
3. Update Work preferences
4. Return to Main Menu

##### Report Leaving
- The status of the delivery changes to "Out for Delivery..", and the status of the driver and truck changes to "On the road". The driver and truck cannot be assigned to a new delivery until they return.

##### Report Back
- The status of the delivery changes to "Delivered!", and the status of the driver and truck changes to "available". The driver and truck are now available for new deliveries.

##### Update Work preferences
- Change the driver's shifts.

### Employee System

#### Employee Menu
To access the employee menu, enter your name and password. After verification, you will see:
1. View personal details
2. View preferences
3. Update preferences

#### Administrator Menu
To access the administrator menu, enter the password: `123456789`. The administrator menu includes the following options:
1. Add new employee
2. Remove employee
3. Update employee details
4. Manage Shifts
5. Print all employees

## Features
- **User Roles**: Separate menus and functionalities for managers, drivers, and employees.
- **Driver Management**: Add and manage drivers with specific license levels.
- **Truck Management**: Add and manage trucks with specific weight capacities.
- **Site Management**: Add and manage delivery sites (suppliers and branches).
- **Delivery Management**: Create, assign, and track deliveries.
- **Status Tracking**: Track the status of deliveries, drivers, and trucks.
- **Employee Management**: Add, remove, update employee details, manage shifts, and print employee information.
- **Validation**: Ensure data integrity with proper validation for all inputs.
- **Shift Coordination**: Ensure that transports to specific stores are only scheduled if a Stock Keeper is present in the shift list for that day and shift.

## System Requirements
- Java Runtime Environment (JRE) 8 or higher
- The `TransportatioDataBase` file


├── DAL
│   ├── DALTransport
│   │   ├── DB_Connector.java
│   │   ├── IDAO.java
│   │   ├── DriversDAO.java
│   │   ├── SitesDAO.java
│   │   ├── TransportsDAO.java
│   │   ├── TrucksDAO.java
│       ├── DB_Creator.java
├── Data
│   ├── Afula.db
│   ├── Be'erSheva.db
│   ├── Beit Shemesh.db
│   ├── Drivers.db
│   ├── Efrat.db
│   ├── empty.db
│   ├── Empty Store.db
│   ├── Haiffa.db
│   ├── Hertseliya.db
│   ├── Hulon.db
│   ├── Jerusalem.db
│   ├── Kiriyat-Haiim.db
│   ├── Kiryat-Gat.db
│   ├── Ma'ale Edumim.db
│   ├── Mevaseret-Zion.db
│   ├── Nahariya.db
│   ├── Ofackim.db
│   ├── Omer.db
│   ├── Petah' Tikva.db
│   ├── Rahat.db
│   ├── Tel-Aviv.db
│   ├── Test_Data.db
│   ├── Tiberias.db
│   ├── Transportation.db
│   ├── Trucks.db
│   └── Yavne.db
├── Domain
│   ├── DomainEmployee
│   │   ├── Controller
│   │   │   ├── AdminController.java
│   │   │   ├── DataController.java
│   │   │   ├── EmployeeController.java
│   │   │   ├── SystemController.java
│   │   ├── Constants.java
│   │   ├── Contract.java
│   │   ├── dayTypeEnum.java
│   │   ├── Employee.java
│   │   ├── IO_Data.java
│   │   ├── JobTypeEnum.java
│   │   ├── JobWeeklyShift.java
│   │   ├── SHA_256_Hasher.java
│   │   ├── ShiftTypeEnum.java
│   │   ├── WeeklyShift.java
├── DomainTransport
│   ├── Controllers
│   │   ├── DataController.java
│   │   ├── DriverController.java
│   │   ├── SolutionsController.java
│   │   ├── TransportationController.java
│   ├── Objects
│   │   ├── Document.java
│   │   ├── Driver.java
│   │   ├── Item.java
│   │   ├── Site.java
│   │   ├── Transportation.java
│   │   ├── TransportDocument.java
│   │   ├── Truck.java
│   ├── Repositories
│   │   ├── DriverRepository.java
│   │   ├── IRepository.java
│   │   ├── SitesRepository.java
│   │   ├── TransportsRepository.java
│   │   ├── TrucksRepository.java
├── Presentation
│   ├── PresentationEmployee
│   │   ├── AdminMenu.java
│   │   ├── Menu.java
│   │   ├── Printer.java
│   │   ├── Program.java
│   │   ├── ProgressBar.java
│   │   ├── Sender.java
│   │   ├── UserMenu.java
│   ├── PresentationTransport
│   │   ├── ChooseSolution.java
│   │   ├── CreateTransportation.java
│   │   ├── DataConnector.java
│   │   ├── DriverMenu.java
│   │   ├── Main_Transport.java
│   │   ├── TransportationManagerMenu.java
│   ├── main.java
├── Test
│   ├── TestEmployee
│   │   ├── PrinterTest.java
│   │   ├── SenderTest.java
│   │   ├── Test_DB.java
│   │   ├── Test_Employees.java
│   │   ├── Test_JobWeekly_Shift.java
│   │   ├── Test_SHA256_Hasher.java
│   ├── TestTransport
│   │   ├── TransportationManagerTests.java
