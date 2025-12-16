# Bill Splitting Application

A Spring Boot/Kotlin application for splitting bills among friends.

## Features
- Create bills with participants
- Track payments and balances
- Money transfer between accounts
- Bill history search

## Requirements
- JDK 17+
- Maven 3.6+
- PostgreSQL

## Build
```bash
mvn clean package
```

## Configuration
Edit `src/main/resources/application.yaml`:
- Database connection
- Server port (default: 8083)
- Logging format

## API Documentation
Endpoints available via Postman collection in `/postman` directory
