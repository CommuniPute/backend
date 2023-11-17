# CommuniPute

Distributed compute sharing platform!

## Overview

Allows for compute sharing. Connects users with under utilized compute power with users that require more compute. Users
that need compute power can submit a job to the platform by selecting where they want their job to be run. The app will 
then distribute the compute to the selected compute nodes. The compute nodes will then run the job via containerization 
and return the results. The results will then be returned to the user that submitted the job.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 17
- Maven
- Docker 4.x

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installation

1. **Clone the Repository**


2. **Running the Spring Boot Application**

   Navigate to the root directory of the Spring Boot application and run:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
    This will start the Spring Boot application on port 8080 (via application.properties).
### Setting up the PostgresDB with Docker

Navigate to the postgres-local directory and run:

```bash
docker-compose up -d
```

This will pull the PostgreSQL image, run it in detached mode, and create a new database with the defined username and password called "communiputedb".
This allows for seamless integration with the Spring Boot application and properties defined within application.properties file.

### Usage

Please see postman docs for API usage and definition.

### Configuration

Application configuration can be done via the application.properties file. Please do not push breaking changes to the
main repo.

### Contributing

Contribute away! Just don't push to main without a PR.

### Deployment

Under consideration

### License
- I said I can