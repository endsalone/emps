
# Eligibility API

This project provides an API for validating whether a given CNPJ (Brazilian company registration number) is eligible for account creation. It follows a clean architecture structure with separation of concerns between the core business logic, data access, and presentation layers.

## Features

- **RESTful API**: Exposes endpoints to check the eligibility of a CNPJ.
- **Clean Architecture**: Follows the Clean Architecture principle to separate core business logic from infrastructure concerns.
- **HATEOAS Support**: Implements HATEOAS (Hypermedia as the Engine of Application State) to provide links for API navigation.
- **PostgreSQL Database**: Stores eligibility information.
- **Spring Boot**: A robust Java framework used for building the API.
- **Validation**: Custom CNPJ validation and error handling with `HandlerMethodValidationException`.
- **Docker**: Containerized deployment using Docker and Docker Compose.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running the Project with Docker](#running-the-project-with-docker)
- [Running Tests](#running-tests)
- [API Endpoints](#api-endpoints)

## Architecture Overview

The project is structured following Clean Architecture, splitting responsibilities across different layers:

```
├── core
│   ├── entities          # Core domain entities
│   └── usecases          # Business rules and use cases
├── data
│   ├── datasources       # Data access layer (Repositories)
│   └── repositories      # Repository interfaces and implementation
├── domain
│   ├── dtos              # Data Transfer Objects
│   └── interfaces        # Service interfaces
└── presentation
    ├── controllers       # REST controllers
    ├── validators        # Input validation classes
    └── exceptions        # Global exception handler
```

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL 10**
- **HATEOAS**
- **Docker & Docker Compose**
- **JUnit 5**
- **Mockito**

## Prerequisites

To run this project, you need the following installed on your machine:

- **Docker**: Ensure you have Docker installed.
- **Docker Compose**: Installed as part of Docker Desktop or manually.

## Setup Instructions

### 1. Clone the repository:

```bash
git clone https://github.com/yourusername/eligibility-api.git
cd eligibility-api
```

### 2. Build the project with Maven:

You can skip this if you plan to use Docker.

```bash
mvn clean install
```

### 3. Set up environment variables:

If using Docker Compose, the environment variables will be handled automatically. For local development, create an `.env` file with the following content:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/emps_db
SPRING_DATASOURCE_USERNAME=api_emps
SPRING_DATASOURCE_PASSWORD=api_emps
```

## Running the Project with Docker

To run the project, simply use Docker Compose. It will automatically set up the application along with a PostgreSQL database.

```bash
docker-compose up --build
```

This command will:

1. Build the application.
2. Spin up a PostgreSQL container and link it with the application.
3. The API will be available at `http://localhost:8080`.

### Accessing the Database:

PostgreSQL will be exposed on port `5433` locally, and you can connect using any PostgreSQL client.

```
Host: localhost
Port: 5433
Username: api_emps
Password: api_emps
Database: emps_db
```

## Running Tests

This project includes unit tests for various components. To run the tests, use:

```bash
mvn test
```

The tests are written using **JUnit 5** and **Mockito** to ensure high coverage and testability across the project.

## API Endpoints

### Check CNPJ Eligibility

- **URL**: `/api/v1/eligibility/{cnpj}`
- **Method**: `GET`
- **Description**: Returns whether the provided CNPJ is eligible for account creation.
- **Response Example**:
  
```json
{
  "cnpj": "12345678000195",
  "eligible": true,
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v1/eligibility/12345678000195"
    }
  }
}
```

