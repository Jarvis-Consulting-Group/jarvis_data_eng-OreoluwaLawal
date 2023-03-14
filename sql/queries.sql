--The club is adding a new facility - a spa.
insert
	into
	cd.facilities
values
(9,
'Spa',
20,
30,
100000,
800);
--Automatically generate the value for the next facid, rather than specifying it as a constant.
insert
	into
	cd.facilities
    (facid,
	name,
	membercost,
	guestcost,
	initialoutlay,
	monthlymaintenance)
    select
	(
	select
		max(facid)
	from
		cd.facilities)+ 1,
	'Spa',
	20,
	30,
	100000,
	800;
--Alter the data to fix an error.
update
	cd.facilities
set
	initialoutlay = 10000
where
	facid = 1;
--alter the price of the second tennis court so that it costs 10% more than the first one.
update
	cd.facilities
set
	membercost = (
	select
		(membercost * 0.1) + membercost
	from
		cd.facilities
	where
		facid = 0),
	guestcost = (
	select
		(guestcost * 0.1) + guestcost
	from
		cd.facilities
	where
		facid = 0)
where
	facid = 1;
--delete all bookings from the cd.bookings table.
delete
from
	cd.bookings;
--remove member 37, who has never made a booking, from our database. How can we
delete
from
	cd.members
where
	memid = 37;
--produce a list of facilities that charge a fee to members, and that fee is less than 1/50th
--of the monthly maintenance cost? Return the facid, facility name, member cost,
--and monthly maintenance of the facilities in question.
select
	facid,
	name,
	membercost,
	monthlymaintenance
from
	cd.facilities
where
	membercost < (0.02 * monthlymaintenance)
	and membercost != 0;
--produce a list of all facilities with the word 'Tennis' in their name?
select
	*
from
	cd.facilities
where
	name like '%Tennis%';
-- retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator.
select
	*
from
	cd.facilities
where
	facid in (1, 5);
--produce a list of members who joined after the start of September 2012? Return the memid, surname,
--firstname, and joindate of the members in question.
select
	memid,
	surname,
	firstname,
	joindate
from
	cd.members
where
	joindate > '2012-09-01';
-- combined list of all surnames and all facility names. Yes, this is a contrived example :-). Produce that list!
select
	surname
from
	cd.members
union
select
	name
from
	cd.facilities;
--produce a list of the start times for bookings by members named 'David Farrell'?
select
	starttime
from
	cd.bookings b
join cd.members m on
	b.memid = m.memid
where
	firstname = 'David'
	and surname = 'Farrell';
--produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time.
select
	b.starttime as start,
	f.name
from
	cd.bookings b
join cd.facilities f
on
	b.facid = f.facid
where
	starttime >= '2012-09-21'
	and starttime <= '2012-09-22'
	and f.name like '%Tennis Court%'
order by
	starttime;
--output a list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).
select
	m.firstname as memfname,
	m.surname as memsname,
	r.firstname as recfname,
	r.surname as recsname
from
	cd.members m
left outer join cd.members r
on
	r.memid = m.recommendedby
order by
	memsname asc,
	memfname asc;
--output a list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname).
select
	distinct r.firstname as firstname,
	r.surname as surname
from
	cd.members m
inner join cd.members r
on
	r.memid = m.recommendedby
order by
	surname asc,
	firstname asc;
--output a list of all members, including the individual who recommended them (if any), without using any joins?
select
	distinct m.firstname || ' ' || m.surname as member,
	(
	select
		r.firstname || ' ' || r.surname as recommender
	from
		cd.members r
	where
		r.memid = m.recommendedby)
from
	cd.members m
order by
	member;
--Produce a count of the number of recommendations each member has made. Order by member ID.
 select
	recommendedby,
	count(*)
from
	cd.members
where
	recommendedby is not null
group by
	recommendedby
order by
	recommendedby;
--Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.
 select
	facid,
	SUM(slots)
from
	cd.bookings
group by
	facid
order by
	facid;
--Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.
 select
	facid,
	SUM(slots) as "Total Slots"
from
	cd.bookings
where
	starttime >= '2012-09-01'
	and starttime < '2012-10-01'
group by
	facid
order by
	SUM(slots);
--Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month.
 select
	facid,
	extract(month
from
	starttime) as month,
	SUM(slots) as "Total Slots"
from
	cd.bookings
where
	extract(year
from
	starttime) = 2012
group by
	facid,
	month
order by
	facid,
	month;
--Find the total number of members (including guests) who have made at least one booking.
 select
	COUNT(distinct memid)
from
	cd.bookings;
--Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.
 select
	m.surname,
	m.firstname,
	m.memid,
	MIN(b.starttime)
from
	cd.bookings b
join cd.members m
 on
	b.memid = m.memid
where
	b.starttime >= '2012-09-01'
group by
	m.surname,
	m.firstname,
	m.memid
order by
	m.memid;
--Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.
 select
	COUNT(*) over(),
	firstname,
	surname
from
	cd.members;
--Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.
 select
	row_number() over(),
	firstname,
	surname
from
	cd.members
order by
	joindate;
--Output the facility id that has the highest number of slots booked. Ensure that in the event of a tie, all tieing results get output.
select
	facid,
	total
from
	(
	select
		facid,
		sum(slots) total,
		rank() over (
		order by sum(slots) desc) rank
	from
		cd.bookings
	group by
		facid
	) as ranked
where
	rank = 1
	--Output the names of all members, formatted as 'Surname, Firstname'
 select
	surname || ', ' || firstname as name
from
	cd.members;
--find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.
 select
	memid,
	telephone
from
	cd.members
where
	telephone like '(%)%';
--produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.
 select
	substr (m.surname,
	1,
	1) as letter,
	count(*)
from
	cd.members m
group by
	letter
order by
	letter;

