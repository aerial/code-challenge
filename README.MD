### Summary

A code challenge project which goal is to enable sending notifications using templates and user data.
This project features microservices architecture and consists of 3 services:
* User service
* Template service
* Notification service

### Services communication

Services locate each other with Eureka discovery service and communicate using REST API. Notification service fetch data from the other two and process it asynchronously.

### Storage

Template and User service store their data (templates and user list) in embedded H2 SQL database and access it using JPA 2.

### Testing
Integration testing is done using Spring Cloud Contract library. Sample contracts located in user-service/test/resource/contracts and in template-service/test/resource/contracts.

### Building and deployment
* Navigate to project root
* Execute `gradlew build`
* Once Gradle build is complete run `docker-compose up -d`
* Wait until services start and execute `http://{host}:8081/notification/sendNewsletter` to see the list of notifications to be sent to users. 

### Notification Service

#### Technologies

Spring WebFlux, Spring Reactor, Apache Commons Lang 

#### Description

Notification service need to fetch data from User service and Template service on each request to create a valid notification. 
To improve potential availability this service is designed to work in reactive\non-blocking manner using Spring WebFlux.

#### Endpoints

* **POST** http://{host}:8081/notification/sendWelcome?userId={userId} - Send a welcome notification with specified user data
* **POST** http://{host}:8081/notification/sendNewsletter - Send newsletters to all subscribed users

### User Service 

#### Technologies

Spring Data JPA, Hibernate, Spring Web

#### Endpoints

* **GET** http://{host}:8082/users - Fetch all users
* **GET** http://{host}:8082/user/{id} - Fetch a user by id

### Template Service

#### Technologies
Spring Data JPA, Hibernate, Spring Web

#### Endpoints

* **GET** http://{host}:8083/templates/{id} - Fetch a template by id
* **GET** http://{host}:8083/templates/filter?name={name} - Fetch a template by name





