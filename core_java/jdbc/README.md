# Introduction

The JDBC application was developed to access the database and perform CRUD operations. I implemented a data access object (DAO) to perform several queries on objects (DTO).
Built the project using IntelliJ IDE and utilized Maven to manage the project packages and dependencies, the database used is PostgreSQL RDBMS which runs in a Docker container and permits the data to
be persisted into the database instance.

# Implementation
I executed several SQL scripts to create a new database on the psql instance and also create and populate data on the tables in the database.
```bash
# Create a database
psql -h localhost -U postgres -f database.sql

# Access the jdbc_db database
psql -h localhost -U postgres -d jdbc_db

# Create tables and populate tables with data
psql -h localhost -U postgres -d jdbc_db -f customer.sql
psql -h localhost -U postgres -d jdbc_db -f product.sql
psql -h localhost -U postgres -d jdbc_db -f salesperson.sql
psql -h localhost -U postgres -d jdbc_db -f orders.sql
```
## ER Diagram
![ER Diagram](./assets/jdbc_db%20ER%20diagram.png)


## Design Patterns
### DAO Design Pattern
DAO pattern was implemented in the application to provide a persistence layer to access the database, this isolates the logic from the business logic.
I created DataAccessObject abstract class which had implementation methods and was extended by two classes; CustomerDAO and OrderDAO, these classes are called to perform CRUD
operations related to the Customer and Order model (DTO) which is an entity in the database.

### Repository Design Pattern
A repository is also another pattern that can be utilized, it's a collection-like interface that is used to access domain objects.
It's a layer between the data and the domain objects and is closer to the business logic of the application.
A repository can be used to fetch data using DAO from the database and populate it to the domain object, also it can be used to prepare the data
from a domain object and send it to the database using DAO for persistence.

# Test
For this application, manual testing was done. Firstly, I executed a bash script (psql_docker.sh) to check the status of the docker container and start my psql instance.
```bash 
./scripts/psql_docker.sh start
```
Used DBeaver to check the database if the SQL scripts and the CRUD operations on the JDBC were executed successfully. Also, I manually tested the implemented methods in the JDBCExecuter class and compared the results on DBeaver.