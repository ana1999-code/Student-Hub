# Student and Course Management App
This is a web application for managing students and courses. It consists of a Java backend, a React frontend, and uses Docker, JDBC, Flyway, and a PostgreSQL database.

## Prerequisites
Before you begin, make sure you have the following installed on your system:

- Docker
- Node.js

## Getting Started
### Backend Setup
1. Clone this repository to your local machine.

2. Navigate to the project folder containing the docker-compose.yml file.

3. Start the PostgreSQL database using Docker:

```bash
docker-compose up -d
```
This command will start the PostgreSQL container and run Flyway migrations to create the necessary database schema.

4. Build and run the Java backend application. You can use your preferred Java IDE or build and run it using command-line tools.

- Example with Maven:

```bash
cd backend
mvn clean install
java -jar target/student-hub-0.0.1-SNAPSHOT.jar
```

The Java application should now be running at http://localhost:8080.

### Frontend Setup
1. Navigate to the project folder containing the package.json file.

2. Install the required Node.js packages:

```bash
npm install
```
3. Start the React frontend:

```bash
npm start
```
The frontend should be accessible at http://localhost:3000.

### Usage
Access the application by opening your web browser and navigating to http://localhost:3000.
