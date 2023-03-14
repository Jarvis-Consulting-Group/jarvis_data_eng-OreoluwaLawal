# Introduction

# SQL Queries

###### Table Setup (DDL)
###### Members Table
```sql
CREATE TABLE cd.members (
memid Integer NOT NULL,
surname CHARACTER VARYING(200) NOT NULL,
firstname CHARACTER VARYING(200) NOT NULL,
address CHARACTER VARYING(300) NOT NULL,
zipcode Integer NOT NULL,
telephone CHARACTER VARYING(20) NOT NULL,
recommendedby Integer,
joindate timestamp NOT NULL,
CONSTRAINT PK_members_memid PRIMARY KEY(memid),
CONSTRAINT FK_members_recommendedby FOREIGN KEY(recommendedby)
REFERENCES cd.members(memid) ON
DELETE
SET
NULL
);
```

###### Bookings Table
```sql 
CREATE TABLE cd.bookings (
faceid Integer NOT NULL,
memid Integer NOT NULL,
starttime timestamp NOT NULL,
slots Integer NOT NULL,
CONSTRAINT PK_bookings_faceid PRIMARY KEY(faceid),
CONSTRAINT FK_bookings_memid FOREIGN KEY(memid)
REFERENCES cd.members(memid)
);
```

###### Facilities Table
```sql
CREATE TABLE cd.facilities (
faceid Integer NOT NULL,
name CHARACTER VARYING(100) NOT NULL,
membercost NUMERIC NOT NULL,
guestcost NUMERIC NOT NULL,
initialoutlay NUMERIC NOT NULL,
monthlymaintenance NUMERIC NOT NULL,
CONSTRAINT PK_facilities_faceid PRIMARY KEY(faceid)
);
```


###### Question 1: Show all members 



###### Questions 2: Lorem ipsum...



