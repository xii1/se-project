# SE Project App

SE Project App is a REST API application which written by Java (using Spring Boot).

## Getting Started

These instructions will get you building and running the project on your local machine for development and testing purposes. See usage and supported commands for notes on how to use the application.

## Prerequisites

- Apache Maven v3+
- JDK 1.8+
- Docker

## Setup DB
```bash
./bin/start_db
```

## Building & Setup

On Unix:
```bash
./bin/setup
```
Or others:
```bash
mvn clean package
```

## Usage

On Unix:
```bash
./bin/run
```
Or others:
```bash
java -jar target/se-project-1.0.0.jar
```

## Documents
Including design documents, use case diagram, class diagram, sequence diagram in **docs/** folder.

## License
This project is licensed under the MIT License - see the LICENSE file for details
