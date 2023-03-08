# Linux Cluster Monitoring Agent
# Introduction
The Linux Cluster Monitoring Agent is developed to gather a server hardware specifications and resource usage data and store the data in a database. This application is designed to permit the Linux Cluster Administrator (LCA) to have a knowledge of a Linux server hardware and keep track of an ongoing Linux server resource usage data. 
The application uses bash scripts executed continuously with crontab command to collect usage data and store the data in PostgresSQL instance in docker container.
# Quick Start

Start a psql instance using psql_docker.sh 
````bash
./scripts/psql_docker.sh start
````
Create tables using ddl.sql
````bash
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
````
Insert hardware specs data into the DB using host_info.sh
````bash
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
````
Insert hardware usage data into the DB using host_usage.sh
````bash
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
````
Crontab setup
````bash
crontab -e

#add this line to crontab
* * * * * bash /path/to/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
````
# Implementation
- A bash script psql_docker.sh was written to create/start/stop a psql instance using docker.
- Then I wrote ddl.sql script that creates 2 tables in the host_agent database, one which is host_info to store hardware specifications and the second being host_usage to store resource usage data.
- Using bash scripts host_info.sh to collect hardware specification data and host_usage.sh to collect resource usage then persists the data into the database tables created.
- Finally, using crontab the usage data is collected every minute and the monitoring app is deployed.

## Architecture
![my image](./assets/cluster.jpg)

## Scripts
### psql_docker.sh
This script is used to create, start or stop a psql instance on docker.
````bash
./scripts/psql_docker.sh start|stop|create [db_username][db_password]

# create an instance
./scripts/psql_docker.sh create db_username db_password

#start the instance
./scripts/psql_docker.sh start

#stop the instance
./scripts/psql_docker.sh stop
````

### host_info.sh
This script gathers the hardware specification data and then the data is stored in the psql database. This script is executed once.
````bash
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
````

### host_usage.sh
This scripts gathers the server usage data and stores it to the psql database.
````bash
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
````

### crontab
The crontab script is used to execute the host_usage script every minute.
````bash
# edit crontab job
crontab -e

# add this line to crontab, create a crontab job
* * * * * bash /path/to/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

#list crontab jobs
crontab -l
````

## Database Modelling
### host_info schema
|       Field Name | Data Type      | Constraints |
|-----------------:|---------------:|-------------|
|               id | serial      | PK NOT NULL |
|         hostname | varchar  | UNIQUE NOT NULL |
|       cpu_number | int2           | NOT NULL |
| cpu_architecture | varchar        | NOT NULL |
|        cpu_model | varchar        | NOT NULL |
|          cpu_mhz | float8         | NOT NULL |
|         l2_cache | int4           | NOT NULL |
|      "timestamp" | timestamp      | NULL |
|        total_mem | int4           | NULL |


### host_usage schema
|       Field Name | Data Type | Constraints |
|-----------------:|----------:|-------------|
|       "timestamp" | timestamp | NOT NULL    |
|         host_id |    serial | FK NOT NULL |
|       memory_free |      int4 | NOT NULL    |
| cpu_idel |      int2 | NOT NULL    |
|        cpu_kernel|      int2 | NOT NULL    |
|          disk_io |      int4 | NOT NULL    |
|         disk_available |      int4 | NOT NULL    |

# Test
- The bash scripts were tested manually, host_info.sh and host_usage.sh was tested by checking the psql tables after execution, 
while psql_docker.sh was tested on the cli by checking docker container status.
- The ddl.sql was tested by querying the database tables after connecting to psql instance.

# Deployment
Containerization of the database was done in docker, then I created a crontab job. Source code control was done using GitHub.

# Improvements
- Provide the ability to detect any host failure.
