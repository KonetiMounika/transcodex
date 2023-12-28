# Car Parking System

## Overview
This is about Car Parking System Spring boot project implements a simple Car Parking System with Restful API endpoints. Here we are using an H2 in-memory database to store information about cars and parking spots.

### Prerequisities
- Java 17
- Maven
- Eclipse
- Spring Boot
- H2 Database
- Swagger
- Junit with Mockito

  ### Dependencies
- Spring Boot Starter Parent
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring boot starter web
- H2
- Spring Doc
- Lombok
- Spring Boot starter test
- Junit
- Mockito-all

### Installation
1. Clone the repository
   By using the git bash clone the repository using below command
   git clone https://github.com/KonetiMounika/transcodex.git
   
2. Open the project in IDE i.e STS/Eclipse/Intellij
3. Configure the database connection in 'src/main/resources/application.properties'
    # Database Configuration
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password

    # H2 console configuration (optional)
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console

    # Hibernate configuration
    spring.jpa.hibernate.ddl-auto=update
4. We can able to access H2 console at 'http://localhost:8080/h2-console' (Here I am using default port 8080)
5. Table are created automatically inside H2 database, Just we need to insert data int it for our testing purpose using below SQL Queries.
    INSERT into car(id, brand, license_plate, color) values
    (500, 'Audi', 'ABC123', 'Green'),
    (1001, 'Toyata', 'AP10234', 'Blue');

    INSERT into parking_spot (id, occupied, spot_number, car_id) values
    (1, false, 1, 500),
    (2, true, 2, 1001),
    (3, false, 3, null),
    (4, true, 4, null);
  # Running application
6. Finally run the spring boot application by right clicking on carparking application & run as 'Java application'
7. So finally by using provided Restful endpoints we can able to interact with the Car Parking System project.
   # API Endpoints
8. API Endpoints informatin is availble inside swagger document. We can able to access swagger document at 'http://localhost:8080/swagger-ui/index.html'
   Get car details:
     'GET "/api/cars/{carId}" ' --> Retrieve details of a specific car by using ID
   Park Car:
     'POST "/api/cars/park/{carId}/{spotNumber}" ' --> Park a car in a specific parking spot
   Find Available Parking Spot:
     'GET "/api/cars/findAvailbleSpot" ' --> Find and reserve the availble parking spot.
# Testing
For Testing purpose we are using here Junit with Mockito, Test case also we can run by right clicking on carparking project & **coverage as** --> **Junit Test** Then we will get coverage for test cases.
Here we covered both controller classes with 100% coverage & Service classes with 94% coverage.
