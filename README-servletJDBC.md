# Servlet-JDBC Maven Webapp (servletJDBC)

## Overview

A classic Java Servlet + JDBC web application (no Spring). It demonstrates servlet request handling, session management, request forwarding versus redirecting, and a DAO-based approach to querying a PostgreSQL database. The project bundles two independent demonstrations: a static keyword-search example, and a login-then-search-users example backed by the database.

## Technology Stack

- Java 17
- Java Servlet API 4.0.1 (`javax.servlet`, provided scope — this is the Java EE namespace, not Jakarta EE)
- PostgreSQL JDBC Driver 42.7.8
- Maven, packaged as a WAR file
- JUnit 4.13.1 (test scope only; no tests are currently implemented)

Because the project uses the `javax.servlet` namespace rather than `jakarta.servlet`, it must be deployed to Apache Tomcat 9.x (or another Java EE 8-compatible container). It is not compatible with Tomcat 10 or later without code changes, since those versions use the `jakarta.servlet` namespace.

## Project Structure

```
servletJDBC
├── pom.xml
└── src/main
    ├── java/com/vit
    │   ├── dao
    │   │   ├── DBConnection.java     Holds a single shared JDBC Connection
    │   │   ├── TestConnection.java   Opens a connection and logs the result
    │   │   └── UserDAO.java          Login validation and keyword search queries
    │   ├── model/User.java           User data model
    │   └── example
    │       ├── HelloServlet.java        Static response, ignores request parameters
    │       ├── AuthServlet.java         Validates login credentials against the database
    │       ├── SearchServlet.java       Checks a keyword against a fixed in-memory word list
    │       ├── ResultServlet.java       Displays the result of SearchServlet
    │       ├── ListUserServlet.java     Runs a database keyword search, stores results in session
    │       └── UserListServlet.java     Renders the search results stored in session as an HTML table
    └── webapp
        ├── WEB-INF/web.xml           Servlet and URL-mapping declarations
        ├── index.html                Form posting to HelloServlet
        ├── login.html                Form posting to AuthServlet
        ├── search.html               Form posting to SearchServlet
        └── searchuser.html           Form posting to ListUserServlet
```

## Prerequisites

- JDK 17 or later
- Apache Maven
- Apache Tomcat 9.x installed separately (this project has no embedded server and no Maven Tomcat plugin configured)
- A running PostgreSQL server

## Database Setup

`DBConnection.java` and `UserDAO.java` connect to the database with the following hardcoded values:

```
URL:      jdbc:postgresql://localhost:5432/academics
User:     postgres
Password: admin
```

The repository does not include a database schema. Based on the column order used in `UserDAO.executeSelect` (`SELECT * FROM public.userdata`, mapped to `new User(username, email, password, age, userid)`), the required table is:

```sql
CREATE TABLE public.userdata (
    username  VARCHAR(50),
    email     VARCHAR(100),
    password  VARCHAR(50),
    age       INTEGER,
    userid    VARCHAR(20) PRIMARY KEY
);
```

Note the column order here differs from the `userdata` table used by the `mvc-springbootdemo` repository; the two projects are independent and do not need to share the same database.

## Cloning and Running

```
git clone https://github.com/senthilmuruganb/servletJDBC.git
cd servletJDBC
mvn clean package
```

This produces `target/ServletJDBCApp.war`. Deploy it to Tomcat 9 in one of the following ways:

- Copy `target/ServletJDBCApp.war` into the Tomcat `webapps` directory and start Tomcat, or
- In Eclipse/STS, add a Tomcat 9 server runtime, right-click the project, and choose Run As > Run on Server.

Once deployed, the application is available at:

```
http://localhost:8080/ServletJDBCApp/index.html
```

(Adjust the port if Tomcat is configured differently.)

## Application Walkthrough

There are two independent flows in this project:

**Static keyword search** — `search.html` submits a keyword to `SearchServlet`, which checks it against a fixed array (`"Hello","from","world","systems"`), stores a success/failure message in the session, and forwards the request to `ResultServlet`, which displays that message.

**Login and database user search** — `login.html` submits credentials to `AuthServlet`, which validates them against the `userdata` table via `UserDAO.validateLogin`. On success, it includes `searchuser.html`; on failure, it includes `login.html` again. From `searchuser.html`, a search pattern is posted to `ListUserServlet`, which runs a `LIKE` query via `UserDAO.executeSelect`, stores the results in the session under the key `searchResult`, and redirects to `UserListServlet`, which reads that session attribute and renders it as an HTML table.

`index.html` and `HelloServlet` are a separate, minimal example: the form collects a name but `HelloServlet` does not read it and always returns the same static message.

## Configuration

All servlets are registered explicitly in `WEB-INF/web.xml` with their URL mappings; annotation-based mapping (`@WebServlet`) is imported in some classes but not actually used.
